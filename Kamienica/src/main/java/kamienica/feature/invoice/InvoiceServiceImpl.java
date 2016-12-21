package kamienica.feature.invoice;

import kamienica.core.calculator.EnergyConsumptionCalculator;
import kamienica.core.calculator.GasConsumptionCalculator;
import kamienica.core.calculator.PaymentCalculator;
import kamienica.core.calculator.WaterConsumptionCalculator;
import kamienica.core.enums.Media;
import kamienica.core.enums.WaterHeatingSystem;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.division.DivisionDao;
import kamienica.feature.meter.MeterService;
import kamienica.feature.payment.PaymentDao;
import kamienica.feature.payment.PaymentEnergy;
import kamienica.feature.payment.PaymentGas;
import kamienica.feature.payment.PaymentWater;
import kamienica.feature.reading.*;
import kamienica.feature.settings.SettingsDao;
import kamienica.feature.tenant.TenantDao;
import kamienica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private TenantDao tenantDao;
	@Autowired
	private DivisionDao divisionDao;
	@Autowired
	private ApartmentDao apartmentDao;
	@Autowired
	private ReadingService readingService;
	@Autowired
	private MeterService meterService;
	@Autowired
	private InvoiceAbstractDao<InvoiceEnergy> invoiceEnergyDao;
	@Autowired
	private InvoiceAbstractDao<InvoiceGas> invoiceGasDao;
	@Autowired
	private InvoiceAbstractDao<InvoiceWater> invoiceWaterDao;
	@Autowired
	private ReadingEnergyDao readingEnergyDao;
	@Autowired
	private ReadingGasDao readingGasDao;
	@Autowired
	private ReadingWaterDao readingWaterDao;
	@Autowired
	private PaymentDao<PaymentGas> paymentGasDao;
	@Autowired
	private PaymentDao<PaymentEnergy> paymentEnergyDao;
	@Autowired
	private PaymentDao<PaymentWater> paymentWaterDao;
	@Autowired
	private SettingsDao settingsDao;

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Invoice> void save(T invoice, Media media) throws InvalidDivisionException {

		List<Tenant> tenants = tenantDao.getActiveTenants();
		List<Division> division = divisionDao.getList();
		List<Apartment> apartments = apartmentDao.getList();
		Settings settinggs = settingsDao.getList().get(0);

		switch (media) {
		case ENERGY:
			List<ReadingEnergy> readingEnergyOld = readingService.getPreviousReadingEnergy(
					invoice.getBaseReading().getReadingDate(), meterService.getIdList(Media.ENERGY));

			List<ReadingEnergy> readingEnergyNew = (List<ReadingEnergy>) readingService
					.getByDate(invoice.getBaseReading().getReadingDate(), Media.ENERGY);

			List<MediaUsage> usageEnergy = EnergyConsumptionCalculator.countConsumption(apartments, readingEnergyOld,
					readingEnergyNew);
			List<PaymentEnergy> paymentEnergy = PaymentCalculator.createPaymentEnergyList(tenants,
					(InvoiceEnergy) invoice, division, usageEnergy);

			invoiceEnergyDao.save((InvoiceEnergy) invoice);
			readingEnergyDao.changeResolvementState(invoice, true);
			for (PaymentEnergy payment : paymentEnergy) {
				paymentEnergyDao.save(payment);
			}
			break;

		case GAS:
			List<ReadingGas> readingGasOld = readingService.getPreviousReadingGas(invoice.getReadingDate(),
					meterService.getIdList(Media.GAS));
			List<ReadingGas> readingGasNew = (List<ReadingGas>) readingService.getByDate(invoice.getReadingDate(),
					Media.GAS);
			List<MediaUsage> usageGas;
			if (settinggs.getWaterHeatingSystem().equals(WaterHeatingSystem.SHARED_GAS)) {
				List<ReadingWater> waterNew = readingWaterDao
						.getWaterReadingForGasConsumption2(invoice.getReadingDate());

				List<ReadingWater> waterOld = readingWaterDao
						.getWaterReadingForGasConsumption2(waterNew.get(0).getReadingDate());

				usageGas = GasConsumptionCalculator.countConsumption(apartments, readingGasOld, readingGasNew, waterOld,
						waterNew);
			} else {
				usageGas = GasConsumptionCalculator.countConsumption(apartments, readingGasOld, readingGasNew);
			}

			List<PaymentGas> paymentGas = PaymentCalculator.createPaymentGasList(tenants, (InvoiceGas) invoice,
					division, usageGas);

			invoiceGasDao.save((InvoiceGas) invoice);
			readingGasDao.changeResolvementState(invoice, true);

			for (PaymentGas payment : paymentGas) {
				paymentGasDao.save(payment);
			}

			break;

		case WATER:
			List<ReadingWater> readingWaterOld = readingService.getPreviousReadingWater(
					invoice.getBaseReading().getReadingDate(), meterService.getIdList(Media.WATER));

			List<ReadingWater> readingWaterNew = (List<ReadingWater>) readingService
					.getByDate(invoice.getBaseReading().getReadingDate(), Media.WATER);

			List<MediaUsage> usageWater = WaterConsumptionCalculator.countConsumption(apartments, readingWaterOld,
					readingWaterNew);
			List<PaymentWater> paymentWater = PaymentCalculator.createPaymentWaterList(tenants, (InvoiceWater) invoice,
					division, usageWater);

			invoiceWaterDao.save((InvoiceWater) invoice);
			readingWaterDao.changeResolvementState(invoice, true);
			for (PaymentWater payment : paymentWater) {
				paymentWaterDao.save(payment);
			}
			break;
		default:
			break;
		}

	}

	@Override
	public void saveEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payment) {
		invoiceEnergyDao.save(invoice);
		readingEnergyDao.changeResolvementState(invoice, true);
		for (PaymentEnergy paymentEnergy : payment) {
			paymentEnergyDao.save(paymentEnergy);
		}

	}

	@Override
	public void saveGas(InvoiceGas invoice, List<PaymentGas> payment) {
		invoiceGasDao.save(invoice);
		readingGasDao.changeResolvementState(invoice, true);

		for (PaymentGas paymentGas : payment) {
			paymentGasDao.save(paymentGas);
		}
	}

	@Override
	public void saveWater(InvoiceWater invoice, List<PaymentWater> payment) {
		invoiceWaterDao.save(invoice);
		readingWaterDao.changeResolvementState(invoice, true);

		for (PaymentWater paymentWater : payment) {
			paymentWaterDao.save(paymentWater);
		}

	}

	@Override
	public void updateEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payments) {
		for (PaymentEnergy payment : payments) {
			paymentEnergyDao.update(payment);
		}
		invoiceEnergyDao.update(invoice);

	}

	@Override
	public void delete(Long id, Media media) {
		Invoice invoice;
		switch (media) {
		case ENERGY:
			invoice = invoiceEnergyDao.getById(id);
			paymentEnergyDao.deleteForInvoice(invoice);
			readingEnergyDao.changeResolvementState(invoice, false);
			invoiceEnergyDao.deleteById(id);
			break;
		case GAS:
			invoice = invoiceGasDao.getById(id);
			paymentGasDao.deleteForInvoice(invoice);
			readingGasDao.changeResolvementState(invoice, false);
			invoiceGasDao.deleteById(id);
			break;
		case WATER:
			invoice = invoiceWaterDao.getById(id);
			paymentWaterDao.deleteForInvoice(invoice);
			readingWaterDao.changeResolvementState(invoice, false);
			invoiceWaterDao.deleteById(id);

			break;
		default:
			break;
		}
	}

	@Override
	public void deleteEnergyByID(Long id) {
		readingEnergyDao.changeResolvementState(invoiceEnergyDao.getById(id), false);
		invoiceEnergyDao.deleteById(id);
	}

	@Override
	public void deleteGasByID(Long id) {
		readingGasDao.changeResolvementState(invoiceEnergyDao.getById(id), false);
		invoiceGasDao.deleteById(id);

	}

	@Override
	public void deleteWaterByID(Long id) {
		// temporaryFix...
		readingWaterDao.changeResolvementState(invoiceEnergyDao.getById(id), false);
		invoiceWaterDao.deleteById(id);

	}

	@Override
	public InvoiceEnergy getEnergyByID(Long id) {
		return invoiceEnergyDao.getById(id);
	}

	// @Override
	// public List<InvoiceEnergy> getEnergyInvoiceList() {
	// return invoiceEnergyDao.getList();
	// }

	@Override
	public <T extends Invoice> void update(T invoice, Media media) {
		double invFactor;
		Invoice oldInv;
		Long id = invoice.getId();
		switch (media) {
		case ENERGY:
			oldInv = invoiceEnergyDao.getById(id);

			invFactor = (invoice.getTotalAmount() / oldInv.getTotalAmount());
			List<PaymentEnergy> paymentsEnergy = paymentEnergyDao.getByInvoice(invoice);
			for (int i = 0; i < paymentsEnergy.size(); i++) {
				paymentsEnergy.get(i).setPaymentAmount(paymentsEnergy.get(i).getPaymentAmount() * invFactor);
			}

			for (PaymentEnergy payment : paymentsEnergy) {
				paymentEnergyDao.update(payment);
			}
			invoiceEnergyDao.update((InvoiceEnergy) invoice);
			break;
		case GAS:

			oldInv = invoiceGasDao.getById(invoice.getId());

			invFactor = (invoice.getTotalAmount() / oldInv.getTotalAmount());
			List<PaymentGas> paymentsGas = paymentGasDao.getByInvoice(invoice);
			for (int i = 0; i < paymentsGas.size(); i++) {
				paymentsGas.get(i).setPaymentAmount(paymentsGas.get(i).getPaymentAmount() * invFactor);
			}

			for (PaymentGas payment : paymentsGas) {
				paymentGasDao.update(payment);
			}
			invoiceGasDao.update((InvoiceGas) invoice);
			break;
		case WATER:

			oldInv = invoiceWaterDao.getById(invoice.getId());
			invFactor = (invoice.getTotalAmount() / oldInv.getTotalAmount());
			List<PaymentWater> paymentWater = paymentWaterDao.getByInvoice(invoice);
			for (int i = 0; i < paymentWater.size(); i++) {
				paymentWater.get(i).setPaymentAmount(paymentWater.get(i).getPaymentAmount() * invFactor);
			}
			for (PaymentWater payment : paymentWater) {
				paymentWaterDao.update(payment);
			}
			invoiceWaterDao.update((InvoiceWater) invoice);
			break;

		default:
			break;
		}
	}

	@Override
	public void updateGas(InvoiceGas invoice, List<PaymentGas> payments) {
		for (PaymentGas payment : payments) {
			paymentGasDao.update(payment);
		}
		invoiceGasDao.update(invoice);

	}

	@Override
	public void updateWater(InvoiceWater invoice, List<PaymentWater> payments) {
		for (PaymentWater payment : payments) {
			paymentWaterDao.update(payment);
		}

		invoiceWaterDao.update(invoice);

	}

	@Override
	public InvoiceGas getGasByID(Long id) {
		return invoiceGasDao.getById(id);
	}

	@Override
	public InvoiceWater getWaterByID(Long id) {
		return invoiceWaterDao.getById(id);
	}

	// @Override
	// public List<InvoiceWater> getInvoicesWaterForPayment(PaymentWater
	// payment) {
	// List<InvoiceWater> output = water.getInvoicesForPayment(payment);
	// if (output.isEmpty()) {
	// output = water.getList();
	// }
	// return output;
	// }
	//
	// @Override
	// public List<InvoiceGas> getInvoicesGasForPayment(PaymentGas payment) {
	// List<InvoiceGas> output = gas.getInvoicesForPayment(payment);
	// if (output.isEmpty()) {
	// output = gas.getList();
	// }
	// return output;
	// }

	@Override
	public List<? extends Invoice> getList(Media media) {
		switch (media) {
		case ENERGY:
			return invoiceEnergyDao.getList();
		case GAS:
			return invoiceGasDao.getList();
		case WATER:
			return invoiceWaterDao.getList();
		default:
			return null;
		}
	}

	// @Override
	// public List<InvoiceWater> getWaterInvoiceList() {
	// return invoiceWaterDao.getList();
	// }
	//
	// @Override
	// public List<InvoiceGas> getGasInvoiceList() {
	// return invoiceGasDao.getList();
	// }

	@Override
	public List<InvoiceEnergy> getUnpaidInvoiceEnergy() {

		return invoiceEnergyDao.getUnpaidInvoices();
	}

	@Override
	public List<InvoiceGas> getUnpaidInvoiceGas() {
		return invoiceGasDao.getUnpaidInvoices();
	}

	@Override
	public List<InvoiceWater> getUnpaidInvoiceWater() {
		return invoiceWaterDao.getUnpaidInvoices();
	}

	@Override
	public InvoiceEnergy getLatestPaidEnergy() {

		return invoiceEnergyDao.getLastResolved();
	}

	@Override
	public InvoiceWater getLatestPaidWater() {
		return invoiceWaterDao.getLastResolved();
	}

	@Override
	public InvoiceGas getLatestPaidGas() {
		return invoiceGasDao.getLastResolved();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Reading> List<T> getUnpaidReadingForNewIncvoice(Media media) throws InvalidDivisionException {

		if (!settingsDao.isDivisionCorrect())

		{
			throw new InvalidDivisionException(
					"Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału");
		}
		switch (media) {
		case ENERGY:
			return (List<T>) readingEnergyDao.getUnresolvedReadings();

		case GAS:

			return (List<T>) readingGasDao.getUnresolvedReadings();

		case WATER:

			return (List<T>) readingWaterDao.getUnresolvedReadings();

		default:
			return null;
		}

	}

	@Override
	public void list(Map<String, Object> model, Media media) {
		switch (media) {
		case GAS:
			model.put("invoice", invoiceEnergyDao.getList());
			model.put("editlUrl", "/Admin/Invoice/invoiceGasEdit.html?id=");
			model.put("delUrl", "/Admin/Invoice/invoiceGasDelete.html?id=");
			model.put("media", "Gaz");
			break;
		case WATER:
			model.put("invoice", invoiceGasDao.getList());
			model.put("editlUrl", "/Admin/Invoice/invoiceWaterEdit.html?id=");
			model.put("delUrl", "/Admin/Invoice/invoiceWaterDelete.html?id=");
			model.put("media", "Woda");
			break;
		case ENERGY:
			model.put("invoice", invoiceWaterDao.getList());
			model.put("editlUrl", "/Admin/Invoice/invoiceEnergyEdit.html?id=");
			model.put("delUrl", "/Admin/Invoice/invoiceEnergyDelete.html?id=");
			model.put("media", "Energia");
			break;
		default:
			break;
		}

	}

}