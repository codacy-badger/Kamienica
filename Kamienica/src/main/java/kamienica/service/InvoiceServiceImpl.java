package kamienica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.InvoiceDao;
import kamienica.dao.PaymentDao;
import kamienica.dao.ReadingDao;
import kamienica.dao.ReadingWaterDAO;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentWater;
import kamienica.model.ReadingEnergy;
import kamienica.model.ReadingGas;
import kamienica.model.ReadingWater;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceDao<InvoiceEnergy> invoiceEnergy;
	@Autowired
	InvoiceDao<InvoiceGas> invoiceGas;
	@Autowired
	InvoiceDao<InvoiceWater> invoiceWater;
	@Autowired
	ReadingDao<ReadingEnergy, InvoiceEnergy> readingEnergy;
	@Autowired
	ReadingDao<ReadingGas, InvoiceGas>  readingGas;
	@Autowired
	ReadingWaterDAO  readingWater;
	@Autowired
	private PaymentDao<PaymentGas, ReadingGas> paymentGasDao;
	@Autowired
	private PaymentDao<PaymentEnergy, ReadingEnergy> paymentEnergyDao;
	@Autowired
	private PaymentDao<PaymentWater, ReadingWater> paymentWaterDao;

	@Override
	public void saveEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payment) {
		invoiceEnergy.save(invoice);
		readingEnergy.ResolveReadings(invoice);
		for (PaymentEnergy paymentEnergy : payment) {
			paymentEnergyDao.save(paymentEnergy);
		}

	}

	@Override
	public void saveGas(InvoiceGas invoice, List<PaymentGas> payment) {
		invoiceGas.save(invoice);
		readingGas.ResolveReadings(invoice);

		for (PaymentGas paymentGas : payment) {
			paymentGasDao.save(paymentGas);
		}
	}

	@Override
	public void saveWater(InvoiceWater invoice, List<PaymentWater> payment) {
		invoiceWater.save(invoice);
		readingWater.ResolveReadings(invoice);

		for (PaymentWater paymentWater : payment) {
			paymentWaterDao.save(paymentWater);
		}

	}

	@Override
	public void deleteEnergyByID(int id) {
		readingEnergy.UnresolveReadings(invoiceEnergy.getById(id));
		invoiceEnergy.deleteById(id);
	}

	@Override
	public void updateEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payments) {
		for (PaymentEnergy payment : payments) {
			paymentEnergyDao.update(payment);
		}
		invoiceEnergy.update(invoice);

	}

	@Override
	public InvoiceEnergy getEnergyByID(int id) {
		return invoiceEnergy.getById(id);
	}

	@Override
	public List<InvoiceEnergy> getEnergyInvoiceList() {
		return invoiceEnergy.getList();
	}

	@Override
	public void deleteGasByID(int id) {
		readingGas.UnresolveReadings(invoiceGas.getById(id));
		invoiceGas.deleteById(id);

	}

	@Override
	public void deleteWaterByID(int id) {
		// temporaryFix...
		readingWater.UnresolveReadings(invoiceWater.getById(id));
		invoiceWater.deleteById(id);

	}

	@Override
	public void updateGas(InvoiceGas invoice, List<PaymentGas> payments) {
		for (PaymentGas payment : payments) {
			paymentGasDao.update(payment);
		}
		invoiceGas.update(invoice);

	}

	@Override
	public void updateWater(InvoiceWater invoice, List<PaymentWater> payments) {
		for (PaymentWater payment : payments) {
			paymentWaterDao.update(payment);
		}

		invoiceWater.update(invoice);

	}

	@Override
	public InvoiceGas getGasByID(int id) {
		return invoiceGas.getById(id);
	}

	@Override
	public InvoiceWater getWaterByID(int id) {
		return invoiceWater.getById(id);
	}

	public InvoiceGas getLatestGas() {
		return invoiceGas.getLatest();
	}

	public InvoiceWater getLatestWater() {
		return invoiceWater.getLatest();
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
		return invoiceWater.getList();
	}

	@Override
	public List<InvoiceGas> getGasInvoiceList() {
		return invoiceGas.getList();
	}

	@Override
	public List<InvoiceEnergy> getUnpaidInvoiceEnergy() {

		return invoiceEnergy.getUnpaidInvoices();
	}

	@Override
	public List<InvoiceGas> getUnpaidInvoiceGas() {
		return invoiceGas.getUnpaidInvoices();
	}

	@Override
	public List<InvoiceWater> getUnpaidInvoiceWater() {
		return invoiceWater.getUnpaidInvoices();
	}

	@Override
	public InvoiceEnergy getLatestPaidEnergy() {

		return invoiceEnergy.getLastResolved();
	}

	@Override
	public InvoiceWater getLatestPaidWater() {
		return invoiceWater.getLastResolved();
	}

	@Override
	public InvoiceGas getLatestPaidGas() {
		return invoiceGas.getLastResolved();
	}
}
