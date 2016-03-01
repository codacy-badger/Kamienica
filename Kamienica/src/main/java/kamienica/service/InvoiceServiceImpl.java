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
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentWater;

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
	private PaymentGasDAO paymentGas;
	@Autowired
	private PaymentEergyDAO paymentEnergy;
	@Autowired
	private PaymentWaterDAO paymentWater;

	@Override
	public void saveEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payment) {
		invoiceEnergy.save(invoice);
		readingEnergy.ResolveReadings(invoice);
		paymentEnergy.saveEnergy(payment);

	}

	@Override
	public void saveGas(InvoiceGas invoice, List<PaymentGas> payment) {
		invoiceGas.save(invoice);
		readingGas.ResolveReadings(invoice);
		paymentGas.saveGas(payment);
	}

	@Override
	public void saveWater(InvoiceWater invoice, List<PaymentWater> payment) {
		invoiceWater.save(invoice);
		readingWater.ResolveReadings(invoice);
		paymentWater.saveWater(payment);
	}

	@Override
	public void deleteEnergyByID(int id) {
		readingEnergy.UnresolveReadings(invoiceEnergy.getById(id));
		invoiceEnergy.deleteByID(id);
	}

	@Override
	public void updateEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payments) {
		for (PaymentEnergy payment : payments) {
			paymentEnergy.update(payment);
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
		invoiceGas.deleteByID(id);

	}

	@Override
	public void deleteWaterByID(int id) {
		// temporaryFix...
		readingWater.UnresolveReadings(invoiceWater.getById(id));
		invoiceWater.deleteByID(id);

	}

	@Override
	public void updateGas(InvoiceGas invoice, List<PaymentGas> payments) {
		for (PaymentGas payment : payments) {
			paymentGas.update(payment);
		}
		invoiceGas.update(invoice);

	}

	@Override
	public void updateWater(InvoiceWater invoice, List<PaymentWater> payments) {
		for (PaymentWater payment : payments) {
			paymentWater.update(payment);
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
