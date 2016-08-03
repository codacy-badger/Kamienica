package kamienica.feature.invoice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.ManagerEnergy;
import kamienica.core.ManagerPayment;
import kamienica.core.Media;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.division.Division;
import kamienica.feature.division.DivisionDao;
import kamienica.feature.division.DivisionValidator;
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
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantDao;
import kamienica.feature.usagevalue.UsageValue;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	TenantDao tenantDao;
	@Autowired
	DivisionDao divisionDao;
	@Autowired
	ApartmentDao apartmentDao;
	@Autowired
	ReadingService readingService;
	@Autowired
	MeterService meterService;
	@Autowired
	InvoiceAbstractDao<InvoiceEnergy> invoiceEnergyDao;
	@Autowired
	InvoiceAbstractDao<InvoiceGas> invoiceGasDao;
	@Autowired
	InvoiceAbstractDao<InvoiceWater> invoiceWaterDao;
	@Autowired
	ReadingEnergyDao readingEnergyDao;
	@Autowired
	ReadingGasDao readingGasDao;
	@Autowired
	ReadingWaterDao readingWaterDao;
	@Autowired
	private PaymentDao<PaymentGas, ReadingGas> paymentGasDao;
	@Autowired
	private PaymentDao<PaymentEnergy, ReadingEnergy> paymentEnergyDao;
	@Autowired
	private PaymentDao<PaymentWater, ReadingWater> paymentWaterDao;

	@Override
	public <T extends Invoice> void save(T invoice, Media media) {
		switch (media) {
		case ENERGY:

			List<Tenant> tenants = tenantDao.getActiveTenants();
			List<Division> division = divisionDao.getList();
			List<Apartment> apartments = apartmentDao.getList();

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

			break;
		case WATER:

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
	public void deleteEnergyByID(Long id) {
		readingEnergyDao.changeResolvmentState(invoiceEnergyDao.getById(id), false);
		invoiceEnergyDao.deleteById(id);
	}

	@Override
	public void updateEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payments) {
		for (PaymentEnergy payment : payments) {
			paymentEnergyDao.update(payment);
		}
		invoiceEnergyDao.update(invoice);

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

		if (!DivisionValidator.validateDivision(apartmentDao.getList(), divisionDao.getList(),
				tenantDao.getActiveTenants())) {
			throw new InvalidDivisionException();
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

}
