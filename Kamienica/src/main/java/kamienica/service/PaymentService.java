package kamienica.service;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentWater;
import kamienica.model.ReadingEnergy;
import kamienica.model.ReadingGas;
import kamienica.model.ReadingWater;
import kamienica.model.Tenant;

public interface PaymentService {

	// public void savePayment(PaymentAbstract payment);

	public void saveGas(List<PaymentGas> payment);

	public void saveWater(List<PaymentWater> payment);

	public void saveEnergy(List<PaymentEnergy> payment);

	public List<PaymentEnergy> getPaymentEnergyForTenant(Tenant tenant);

	public List<PaymentGas> getPaymentGasForTenant(Tenant tenant);

	public List<PaymentWater> getPaymentWaterForTenant(Tenant tenant);

	// public PaymentAbstract getPaymentByInvoice(Invoice invoice);

//	public List<PaymentEnergy> getEnergyByInvoice(Invoice invoice);

//	public List<PaymentEnergy> getEnergyPaymentByDate(ReadingEnergy reading);

	public List<PaymentEnergy> getPaymentEnergyList();

//	public List<PaymentGas> getPaymentGasByInvoice(Invoice invoice);

//	public List<PaymentGas> getPaymentGasByReadingDate(ReadingGas reading);

	public List<PaymentGas> getPaymentGasList();

//	public List<PaymentWater> getPaymentWaterByInvoice(Invoice invoice);

//	public List<PaymentWater> getPaymentWaterByReadingDate(ReadingWater reading);

	public List<PaymentWater> getPaymentWaterList();

//	public PaymentEnergy getLatestPaymentEnergy();
//
//	public PaymentWater getLatestPaymentWater();
//
//	public PaymentGas getLatestPaymentGas();

	public void deleteEnergyByDate(String date, int id);

	public void deleteWaterByDate(String date, int id);

	public void deleteGasByDate(String date, int id);

}
