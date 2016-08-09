package kamienica.feature.invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.h2.util.TempFileDeleter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.ManagerEnergy;
import kamienica.core.ManagerGas;
import kamienica.core.ManagerPayment;
import kamienica.core.ManagerWater;
import kamienica.core.Media;
import kamienica.core.WaterHeatingSystem;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.division.Division;
import kamienica.feature.division.DivisionDao;
import kamienica.feature.meter.MeterService;
import kamienica.feature.payment.PaymentDao;
import kamienica.feature.payment.PaymentEnergy;
import kamienica.feature.payment.PaymentGas;
import kamienica.feature.payment.PaymentWater;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingEnergyDao;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingGasDao;
import kamienica.feature.reading.ReadingService;
import kamienica.feature.reading.ReadingWater;
import kamienica.feature.reading.ReadingWaterDao;
import kamienica.feature.settings.Settings;
import kamienica.feature.settings.SettingsDao;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantDao;
import kamienica.feature.usagevalue.UsageValue;

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

	@Override
	public <T extends Invoice> void save(T invoice, Media media) {

		List<Tenant> tenants = tenantDao.getActiveTenants();
		List<Division> division = divisionDao.getList();
		List<Apartment> apartments = apartmentDao.getList();
		Settings settinggs = settingsDao.getList().get(0);

		switch (media) {
		case ENERGY:
			List<ReadingEnergy> readingEnergyOld = readingService.getPreviousReadingEnergy(
					invoice.getBaseReading().getReadingDate(), meterService.getIdList(Media.ENERGY));

			List<ReadingEnergy> readingEnergyNew = readingService.getByDate(invoice.getBaseReading().getReadingDate(),
					Media.ENERGY);

			List<UsageValue> usageEnergy = ManagerEnergy.countConsupmtion(apartments, readingEnergyOld,
					readingEnergyNew);
			List<PaymentEnergy> paymentEnergy = ManagerPayment.createPaymentEnergyList(tenants, (InvoiceEnergy) invoice,
					division, usageEnergy);

			invoiceEnergyDao.save((InvoiceEnergy) invoice);
			readingEnergyDao.changeResolvmentState(invoice, true);
			for (PaymentEnergy payment : paymentEnergy) {
				paymentEnergyDao.save(payment);
			}
			break;

		case GAS:
			List<ReadingGas> readingGasOld = readingService.getPreviousReadingGas(invoice.getReadingDate(),
					meterService.getIdList(Media.GAS));
			List<ReadingGas> readingGasNew = readingService.getByDate(invoice.getReadingDate(), Media.GAS);
			ArrayList<UsageValue> usageGas;
			if (settinggs.getWaterHeatingSystem().equals(WaterHeatingSystem.SHARED_GAS)) {
				List<ReadingWater> waterNew = readingWaterDao
						.getWaterReadingForGasConsumption2(invoice.getReadingDate());

				List<ReadingWater> waterOld = readingWaterDao
						.getWaterReadingForGasConsumption2(waterNew.get(0).getReadingDate());

				usageGas = ManagerGas.countConsumption(apartments, readingGasOld, readingGasNew, waterOld, waterNew);
			} else {
				usageGas = ManagerGas.countConsumption(apartments, readingGasOld, readingGasNew);
			}

			List<PaymentGas> paymentGas = ManagerPayment.createPaymentGasList(tenants, (InvoiceGas) invoice, division,
					usageGas);

			invoiceGasDao.save((InvoiceGas) invoice);
			readingGasDao.changeResolvmentState(invoice, true);

			for (PaymentGas payment : paymentGas) {
				paymentGasDao.save(payment);
			}

			break;

		case WATER:
			List<ReadingWater> readingWaterOld = readingService.getPreviousReadingWater(
					invoice.getBaseReading().getReadingDate(), meterService.getIdList(Media.WATER));

			List<ReadingWater> readingWaterNew = readingService.getByDate(invoice.getBaseReading().getReadingDate(),
					Media.WATER);

			List<UsageValue> usageWater = ManagerWater.countConsumption(apartments, readingWaterOld, readingWaterNew);
			List<PaymentWater> paymentWater = ManagerPayment.createPaymentWaterList(tenants, (InvoiceWater) invoice,
					division, usageWater);

			invoiceWaterDao.save((InvoiceWater) invoice);
			readingWaterDao.changeResolvmentState(invoice, true);
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
		readingEnergyDao.changeResolvmentState(invoice, true);
		for (PaymentEnergy paymentEnergy : payment) {
			paymentEnergyDao.save(paymentEnergy);
		}

	}

	@Override
	public void saveGas(InvoiceGas invoice, List<PaymentGas> payment) {
		invoiceGasDao.save(invoice);
		readingGasDao.resolveReadings(invoice);

		for (PaymentGas paymentGas : payment) {
			paymentGasDao.save(paymentGas);
		}
	}

	@Override
	public void saveWater(InvoiceWater invoice, List<PaymentWater> payment) {
		invoiceWaterDao.save(invoice);
		readingWaterDao.resolveReadings(invoice);

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
			readingEnergyDao.changeResolvmentState(invoice, false);
			invoiceEnergyDao.deleteById(id);
			break;
		case GAS:
			invoice = invoiceGasDao.getById(id);
			paymentGasDao.deleteForInvoice(invoice);
			readingGasDao.unresolveReadings(invoiceGasDao.getById(id));
			invoiceGasDao.deleteById(id);
			break;
		case WATER:
			invoice = invoiceWaterDao.getById(id);
			paymentWaterDao.deleteForInvoice(invoice);
			readingWaterDao.unresolveReadings(invoiceWaterDao.getById(id));
			invoiceWaterDao.deleteById(id);

			break;
		default:
			break;
		}
	}

	@Override
	public void deleteEnergyByID(Long id) {
		readingEnergyDao.changeResolvmentState(invoiceEnergyDao.getById(id), false);
		invoiceEnergyDao.deleteById(id);
	}

	@Override
	public void deleteGasByID(Long id) {
		readingGasDao.unresolveReadings(invoiceGasDao.getById(id));
		invoiceGasDao.deleteById(id);

	}

	@Override
	public void deleteWaterByID(Long id) {
		// temporaryFix...
		readingWaterDao.unresolveReadings(invoiceWaterDao.getById(id));
		invoiceWaterDao.deleteById(id);

	}

	@Override
	public InvoiceEnergy getEnergyByID(Long id) {
		return invoiceEnergyDao.getById(id);
	}

	@Override
	public List<InvoiceEnergy> getEnergyInvoiceList() {
		return invoiceEnergyDao.getList();
	}

	@Override
	public <T extends Invoice> void update(T invoice, Media media) {
		double invFactor;
		Invoice oldInv;
		Long id = invoice.getId();
		switch (media) {
		case ENERGY:
			System.out.println(invoiceEnergyDao.getList());
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
	public List<InvoiceWater> getWaterInvoiceList() {
		return invoiceWaterDao.getList();
	}

	@Override
	public List<InvoiceGas> getGasInvoiceList() {
		return invoiceGasDao.getList();
	}

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
	public <T extends ReadingAbstract> List<T> prepareForRegistration(Media media) throws InvalidDivisionException {

		// if (!DivisionValidator.validateDivision(apartmentDao.getList(),
		// divisionDao.getList(),
		// tenantDao.getActiveTenants()))
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
