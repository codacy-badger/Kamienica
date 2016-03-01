package kamienica.service;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentWater;
import kamienica.model.Tenant;

public interface PaymentService {

	public void saveGas(List<PaymentGas> payment);

	public void saveWater(List<PaymentWater> payment);

	public void saveEnergy(List<PaymentEnergy> payment);

	public List<PaymentEnergy> getPaymentEnergyForTenant(Tenant tenant);

	public List<PaymentGas> getPaymentGasForTenant(Tenant tenant);

	public List<PaymentWater> getPaymentWaterForTenant(Tenant tenant);

	public List<PaymentEnergy> getEnergyByInvoice(Invoice invoice);

	public List<PaymentEnergy> getPaymentEnergyList();

	public List<PaymentGas> getPaymentGasByInvoice(Invoice invoice);

	public List<PaymentGas> getPaymentGasList();

	public List<PaymentWater> getPaymentWaterByInvoice(Invoice invoice);

	public List<PaymentWater> getPaymentWaterList();

	public void deleteEnergyByDate(int id);

	public void deleteWaterByDate(int id);

	public void deleteGasByDate(int id);

}
