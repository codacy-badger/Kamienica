package kamienica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.PaymentEergyDAO;
import kamienica.dao.PaymentGasDAO;
import kamienica.dao.PaymentWaterDAO;
import kamienica.model.Invoice;
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

	@Override
	public List<PaymentGas> getPaymentGasByInvoice(Invoice invoice) {

		return gas.getGasByInvoice(invoice);
	}

	@Override
	public List<PaymentWater> getPaymentWaterByInvoice(Invoice invoice) {

		return water.getWaterByInvoice(invoice);
	}

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

	@Override
	public void saveGas(List<PaymentGas> payment) {
		gas.saveGas(payment);
	}

	@Override
	public void saveWater(List<PaymentWater> payment) {
		water.saveWater(payment);
	}

	@Override
	public void saveEnergy(List<PaymentEnergy> payment) {
		energy.saveEnergy(payment);
	}

	@Override
	public List<PaymentEnergy> getEnergyByInvoice(Invoice invoice) {

		return energy.getEnergyByInvoice(invoice);
	}

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
	public void deleteEnergyByDate(int id) {
		PaymentEnergy forDeletion = energy.getById(id);
		energy.deleteByDate(forDeletion.getPaymentDate().toString());
	}

	@Override
	public void deleteWaterByDate(int id) {
		PaymentWater forDeletion = water.getById(id);
		water.deleteByDate(forDeletion.getPaymentDate().toString());

	}

	@Override
	public void deleteGasByDate(int id) {
		PaymentGas forDeletion = gas.getById(id);
		gas.deleteByDate(forDeletion.getPaymentDate().toString());
	}

}
