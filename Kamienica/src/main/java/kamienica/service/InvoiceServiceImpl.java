package kamienica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.PaymentEergyDAO;
import kamienica.dao.PaymentGasDAO;
import kamienica.dao.PaymentWaterDAO;
import kamienica.dao.ReadingEnergyDAO;
import kamienica.dao.ReadingGasDAO;
import kamienica.dao.ReadingWaterDAO;
import kamienica.dao.invoice.InvoiceEnergyDAO;
import kamienica.dao.invoice.InvoiceGasDAO;
import kamienica.dao.invoice.InvoiceWaterDAO;
import kamienica.model.Invoice;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentEnergy;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

	// @Autowired
	// InvoiceDao invoiceDAO;
	@Autowired
	InvoiceEnergyDAO invoiceEnergy;
	@Autowired
	InvoiceGasDAO invoiceGas;
	@Autowired
	InvoiceWaterDAO invoiceWater;
	@Autowired
	ReadingEnergyDAO readingEnergy;
	@Autowired
	ReadingGasDAO readingGas;
	@Autowired
	ReadingWaterDAO readingWater;
	@Autowired
	private PaymentGasDAO paymetGas;
	@Autowired
	private PaymentEergyDAO paymentEnergy;
	@Autowired
	private PaymentWaterDAO paymentWater;

	@Override
	public void saveEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payment) {
		invoiceEnergy.save(invoice);
		readingEnergy.ResolveReadings(invoice);
		paymentEnergy.saveEnergy(payment);
		invoiceEnergy.resolveInvoice(invoice);

	}

	@Override
	public void saveGas(InvoiceGas invoice) {
		invoiceGas.save(invoice);
		readingGas.ResolveReadings(invoice);
	}

	@Override
	public void saveWater(InvoiceWater invoice) {
		invoiceWater.save(invoice);
		readingWater.ResolveReadings(invoice);
	}

	@Override
	public void deleteEnergyByID(int id) {
		// temporaryFix...
		readingEnergy.UnresolveReadings(invoiceEnergy.getById(id));
		invoiceEnergy.deleteByID(id);

	}

	@Override
	public void updateEnergy(InvoiceEnergy invoice) {

		// temp fix
		InvoiceEnergy test = invoiceEnergy.getById(invoice.getId());
		// if (invoice.getBaseReading().getId() !=
		// test.getBaseReading().getId()) {
		// readingEnergy.UnresolveReadings(test);
		// }
		invoiceEnergy.update(invoice);

	}

	@Override
	public InvoiceEnergy getEnergyByID(int id) {
		return invoiceEnergy.getById(id);
	}
	//
	// @Override
	// public InvoiceEnergy getLatestEnergy() {
	// return energy.getLatest();
	// }
	//
	// @Override
	// public List<InvoiceEnergy> getInvoicesEnergyForPayment(PaymentEnergy
	// payment) {
	// List<InvoiceEnergy> output = energy.getInvoicesForPayment(payment);
	// if (output.isEmpty()) {
	// output = energy.getList();
	// }
	// return output;
	// }

	@Override
	public List<InvoiceEnergy> getEnergyInvoiceList() {
		return invoiceEnergy.getList();
	}

	@Override
	public List<InvoiceEnergy> getInvoicesEnergyForCalulation(Invoice incoice) {

		return invoiceEnergy.getInvoicesForCalulation(incoice);
	}

	// Gas
	@Override
	public void deleteGasByID(int id) {
		readingGas.UnresolveReadings(invoiceGas.getById(id));
		invoiceGas.deleteByID(id);

	}

	@Override
	public void deleteWaterByID(int id) {
		// temporaryFix...
		readingWater.UnresolveReadings(invoiceWater.getById(id));
		invoiceWater.deleteByID(id);

	}

	@Override
	public void updateGas(InvoiceGas invoice) {
		// temp fix
		InvoiceGas test = invoiceGas.getById(invoice.getId());
		// if (invoice.getBaseReading().getId() !=
		// test.getBaseReading().getId()) {
		// readingGas.UnresolveReadings(test);
		// }
		invoiceGas.update(invoice);

	}

	@Override
	public void updateWater(InvoiceWater invoice) {
		// temp fix
		InvoiceWater test = invoiceWater.getById(invoice.getId());
		// if (invoice.getBaseReading().getId() !=
		// test.getBaseReading().getId()) {
		// readingWater.UnresolveReadings(test);
		// }
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
	public List<InvoiceWater> getInvoicesWaterForCalulation(Invoice invoice) {
		return invoiceWater.getInvoicesForCalulation(invoice);
	}

	@Override
	public List<InvoiceGas> getInvoicesGasForCalulation(Invoice invoice) {
		return invoiceGas.getInvoicesForCalulation(invoice);
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
