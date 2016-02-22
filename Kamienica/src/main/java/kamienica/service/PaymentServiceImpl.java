package kamienica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.PaymentEergyDAO;
import kamienica.dao.PaymentGasDAO;
import kamienica.dao.PaymentWaterDAO;
import kamienica.dao.invoice.InvoiceEnergyDAO;
import kamienica.dao.invoice.InvoiceGasDAO;
import kamienica.dao.invoice.InvoiceWaterDAO;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentWater;
import kamienica.model.Tenant;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentGasDAO gas;
	@Autowired
	private PaymentEergyDAO energy;
	@Autowired
	private PaymentWaterDAO water;
	@Autowired
	private InvoiceEnergyDAO invoiceEnergy;
	@Autowired
	private InvoiceWaterDAO invoiceWater;
	@Autowired
	private InvoiceGasDAO invoiceGas;

//	@Override
//	public List<PaymentEnergy> getEnergyPaymentByDate(ReadingEnergy reading) {
//
//		return energy.getEnergyByReading(reading);
//	}
//
//	@Override
//	public List<PaymentGas> getPaymentGasByInvoice(Invoice invoice) {
//
//		return gas.getGasByInvoice(invoice);
//	}
//
//	@Override
//	public List<PaymentGas> getPaymentGasByReadingDate(ReadingGas reading) {
//
//		return gas.getGasByReading(reading);
//	}
//
//	@Override
//	public List<PaymentWater> getPaymentWaterByInvoice(Invoice invoice) {
//
//		return water.getWaterByInvoice(invoice);
//	}
//
//	@Override
//	public List<PaymentWater> getPaymentWaterByReadingDate(ReadingWater reading) {
//
//		return water.getWaterByReading(reading);
//	}

	@Override
	public List<PaymentEnergy> getPaymentEnergyList() {
		return energy.getPaymentEnergy();
	}

	@Override
	public List<PaymentGas> getPaymentGasList() {
		return gas.getPaymentGas();
	}

	@Override
	public List<PaymentWater> getPaymentWaterList() {
		return water.getPaymentWater();
	}

//	@Override
//	public PaymentEnergy getLatestPaymentEnergy() {
//		return energy.getLatestPaymentEnergy();
//	}
//
//	@Override
//	public PaymentWater getLatestPaymentWater() {
//		return water.getLatestPaymentWater();
//	}
//
//	@Override
//	public PaymentGas getLatestPaymentGas() {
//		return gas.getLatestPaymentGas();
//	}

	@Override
	public void saveGas(List<PaymentGas> payment) {
		gas.saveGas(payment);
		List<InvoiceGas> invoice = payment.get(0).getInvoice();
		for (InvoiceGas i : invoice) {
			invoiceGas.resolveInvoice(i);
		}
	}

	@Override
	public void saveWater(List<PaymentWater> payment) {
		water.saveWater(payment);
		List<InvoiceWater> invoice = payment.get(0).getInvoice();
		for (InvoiceWater i : invoice) {
			invoiceWater.resolveInvoice(i);
		}
	}

	@Override
	public void saveEnergy(List<PaymentEnergy> payment) {
		energy.saveEnergy(payment);
		List<InvoiceEnergy> invoice = payment.get(0).getInvoice();
		for (InvoiceEnergy i : invoice) {
			invoiceEnergy.resolveInvoice(i);
		}

	}

//	@Override
//	public List<PaymentEnergy> getEnergyByInvoice(Invoice invoice) {
//
//		return energy.getEnergyByInvoice(invoice);
//	}

	@Override
	public List<PaymentEnergy> getPaymentEnergyForTenant(Tenant tenant) {
		return energy.getPaymentEnergyForTenant(tenant);
	}

	@Override
	public List<PaymentGas> getPaymentGasForTenant(Tenant tenant) {
		return gas.getPaymentGasForTenant(tenant);
	}

	@Override
	public List<PaymentWater> getPaymentWaterForTenant(Tenant tenant) {
		return water.getPaymentWaterForTenant(tenant);
	}

	@Override
	public void deleteEnergyByDate(String date, int id) {
		invoiceEnergy.unresolveInvoice(id);
		energy.deleteByDate(date);

	}

	@Override
	public void deleteWaterByDate(String date, int id) {
		invoiceWater.unresolveInvoice(id);
		water.deleteByDate(date);

	}

	@Override
	public void deleteGasByDate(String date, int id) {
		invoiceGas.unresolveInvoice(id);
		gas.deleteByDate(date);

	}
}
