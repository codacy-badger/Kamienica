package kamienica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

//	@Autowired
//	InvoiceDao invoiceDAO;
	@Autowired
	InvoiceEnergyDAO energy;
	@Autowired
	InvoiceGasDAO gas;
	@Autowired
	InvoiceWaterDAO water;
	@Autowired
	ReadingEnergyDAO readingEnergy;
	@Autowired
	ReadingGasDAO readingGas;
	@Autowired
	ReadingWaterDAO readingWater;

	@Override
	public void saveEnergy(InvoiceEnergy invoice) {
		energy.save(invoice);
		readingEnergy.ResolveReadings(invoice);
	}

	@Override
	public void saveGas(InvoiceGas invoice) {
		gas.save(invoice);
		readingGas.ResolveReadings(invoice);
	}

	@Override
	public void saveWater(InvoiceWater invoice) {
		water.save(invoice);
		readingWater.ResolveReadings(invoice);
	}

	@Override
	public void deleteEnergyByID(int id) {
		// temporaryFix...
		readingEnergy.UnresolveReadings(energy.getById(id));
		energy.deleteByID(id);

	}

	@Override
	public void updateEnergy(InvoiceEnergy invoice) {

		// temp fix
		InvoiceEnergy test = energy.getById(invoice.getId());
//		if (invoice.getBaseReading().getId() != test.getBaseReading().getId()) {
//			readingEnergy.UnresolveReadings(test);
//		}
		energy.update(invoice);

	}

	@Override
	public InvoiceEnergy getEnergyByID(int id) {
		return energy.getById(id);
	}
//
//	@Override
//	public InvoiceEnergy getLatestEnergy() {
//		return energy.getLatest();
//	}
//
//	@Override
//	public List<InvoiceEnergy> getInvoicesEnergyForPayment(PaymentEnergy payment) {
//		List<InvoiceEnergy> output = energy.getInvoicesForPayment(payment);
//		if (output.isEmpty()) {
//			output = energy.getList();
//		}
//		return output;
//	}

	@Override
	public List<InvoiceEnergy> getEnergyInvoiceList() {
		return energy.getList();
	}

	@Override
	public List<InvoiceEnergy> getInvoicesEnergyForCalulation(Invoice incoice) {

		return energy.getInvoicesForCalulation(incoice);
	}

	// Gas
	@Override
	public void deleteGasByID(int id) {
		readingGas.UnresolveReadings(gas.getById(id));
		gas.deleteByID(id);

	}

	@Override
	public void deleteWaterByID(int id) {
		// temporaryFix...
		readingWater.UnresolveReadings(water.getById(id));
		water.deleteByID(id);

	}

	@Override
	public void updateGas(InvoiceGas invoice) {
		// temp fix
		InvoiceGas test = gas.getById(invoice.getId());
//		if (invoice.getBaseReading().getId() != test.getBaseReading().getId()) {
//			readingGas.UnresolveReadings(test);
//		}
		gas.update(invoice);

	}

	@Override
	public void updateWater(InvoiceWater invoice) {
		// temp fix
		InvoiceWater test = water.getById(invoice.getId());
//		if (invoice.getBaseReading().getId() != test.getBaseReading().getId()) {
//			readingWater.UnresolveReadings(test);
//		}
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

//	@Override
//	public List<InvoiceWater> getInvoicesWaterForPayment(PaymentWater payment) {
//		List<InvoiceWater> output = water.getInvoicesForPayment(payment);
//		if (output.isEmpty()) {
//			output = water.getList();
//		}
//		return output;
//	}
//
//	@Override
//	public List<InvoiceGas> getInvoicesGasForPayment(PaymentGas payment) {
//		List<InvoiceGas> output = gas.getInvoicesForPayment(payment);
//		if (output.isEmpty()) {
//			output = gas.getList();
//		}
//		return output;
//	}

	@Override
	public List<InvoiceWater> getWaterInvoiceList() {
		return water.getList();
	}

	@Override
	public List<InvoiceGas> getGasInvoiceList() {
		return gas.getList();
	}

	@Override
	public List<InvoiceWater> getInvoicesWaterForCalulation(Invoice invoice) {
		return water.getInvoicesForCalulation(invoice);
	}

	@Override
	public List<InvoiceGas> getInvoicesGasForCalulation(Invoice invoice) {
		return gas.getInvoicesForCalulation(invoice);
	}

	@Override
	public List<InvoiceEnergy> getUnpaidInvoiceEnergy() {

		return energy.getUnpaidInvoices();
	}

	@Override
	public List<InvoiceGas> getUnpaidInvoiceGas() {
		return gas.getUnpaidInvoices();
	}

	@Override
	public List<InvoiceWater> getUnpaidInvoiceWater() {
		return water.getUnpaidInvoices();
	}

	@Override
	public InvoiceEnergy getLatestPaidEnergy() {

		return energy.getLastResolved();
	}

	@Override
	public InvoiceWater getLatestPaidWater() {
		return water.getLastResolved();
	}

	@Override
	public InvoiceGas getLatestPaidGas() {
		return gas.getLastResolved();
	}
}
