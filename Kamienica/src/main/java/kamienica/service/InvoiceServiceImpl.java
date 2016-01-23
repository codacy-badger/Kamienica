package kamienica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.invoice.InvoiceDao;
import kamienica.dao.invoice.InvoiceEnergyDAO;
import kamienica.dao.invoice.InvoiceGasDAO;
import kamienica.dao.invoice.InvoiceWaterDAO;
import kamienica.model.Invoice;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentWater;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceDao invoiceDAO;
	@Autowired
	InvoiceEnergyDAO energy;
	@Autowired
	InvoiceGasDAO gas;
	@Autowired
	InvoiceWaterDAO water;

	@Override
	public void saveEnergy(InvoiceEnergy invoice) {
		energy.save(invoice);
	}
	@Override
	public void saveGas(InvoiceGas invoice) {
		gas.save(invoice);
	}
	@Override
	public void saveWater(InvoiceWater invoice) {
		water.save(invoice);
	}
	@Override
	public void deleteEnergyByID(int id) {
		energy.deleteByID(id);

	}

	@Override
	public void updateEnergy(InvoiceEnergy invoice) {
		energy.update(invoice);

	}

	@Override
	public InvoiceEnergy getEnergyByID(int id) {
		return energy.getById(id);
	}

	@Override
	public InvoiceEnergy getLatestEnergy() {
		return energy.getLatest();
	}

	@Override
	public List<InvoiceEnergy> getInvoicesEnergyForPayment(PaymentEnergy payment) {
		List<InvoiceEnergy> output = energy.getInvoicesForPayment(payment);
		if (output.isEmpty()) {
			output = energy.getList();
		}
		return output;
	}

	@Override
	public List<InvoiceEnergy> getEnergyInvoiceList() {
		return energy.getList();
	}

	@Override
	public List<Invoice> getInvoicesEnergyForCalulation(Invoice first, Invoice second) {

		return energy.getInvoicesForCalulation(first, second);
	}

	// Gas
	@Override
	public void deleteGasByID(int id) {
		gas.deleteByID(id);

	}

	@Override
	public void deleteWaterByID(int id) {
		water.deleteByID(id);

	}

	@Override
	public void updateGas(InvoiceGas invoice) {
		gas.update(invoice);

	}

	@Override
	public void updateWater(InvoiceWater invoice) {
		water.update(invoice);

	}

	@Override
	public InvoiceGas getGasByID(int id) {
		return gas.getById(id);
	}

	@Override
	public InvoiceWater getWaterByID(int id) {
		return water.getById(id);
	}

	public InvoiceGas getLatestGas() {
		return gas.getLatest();
	}

	public InvoiceWater getLatestWater() {
		return water.getLatest();
	}

	@Override
	public List<InvoiceWater> getInvoicesWaterForPayment(PaymentWater payment) {
		List<InvoiceWater> output = water.getInvoicesForPayment(payment);
		if (output.isEmpty()) {
			output = water.getList();
		}
		return output;
	}

	@Override
	public List<InvoiceGas> getInvoicesGasForPayment(PaymentGas payment) {
		List<InvoiceGas> output = gas.getInvoicesForPayment(payment);
		if (output.isEmpty()) {
			output = gas.getList();
		}
		return output;
	}

	@Override
	public List<InvoiceWater> getWaterInvoiceList() {
		return water.getList();
	}

	@Override
	public List<InvoiceGas> getGasInvoiceList() {
		return gas.getList();
	}

	@Override
	public List<Invoice> getInvoicesWaterForCalulation(Invoice first, Invoice second) {
		return water.getInvoicesForCalulation(first, second);
	}

	@Override
	public List<Invoice> getInvoicesGasForCalulation(Invoice first, Invoice second) {
		return gas.getInvoicesForCalulation(first, second);
	}
}
