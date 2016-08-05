package kamienica.feature.payment;

import java.util.List;

import kamienica.core.Media;
import kamienica.feature.invoice.Invoice;
import kamienica.feature.tenant.Tenant;

public interface PaymentService {

	// public void saveGasList(List<PaymentGas> payment);
	//
	// public void saveWaterList(List<PaymentWater> payment);
	//
	// public void saveEnergyList(List<PaymentEnergy> payment);
	
	//public  List<PaymentAbstract> list(Media media);
	
	public List<PaymentEnergy> getPaymentEnergyForTenant(Tenant tenant);

	public List<PaymentGas> getPaymentGasForTenant(Tenant tenant);

	public List<PaymentWater> getPaymentWaterForTenant(Tenant tenant);

	public List<PaymentEnergy> getEnergyByInvoice(Invoice invoice);

	public List<PaymentEnergy> getPaymentEnergyList();

	public List<PaymentGas> getPaymentGasByInvoice(Invoice invoice);

	public List<PaymentGas> getPaymentGasList();

	public List<PaymentWater> getPaymentWaterByInvoice(Invoice invoice);

	public List<PaymentWater> getPaymentWaterList();

	// public void deleteEnergyByDate(Long id);
	//
	// public void deleteWaterByDate(Long id);
	//
	// public void deleteGasByDate(Long id);

}
