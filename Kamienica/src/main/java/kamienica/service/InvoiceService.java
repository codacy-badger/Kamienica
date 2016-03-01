package kamienica.service;

import java.util.List;

import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentWater;

public interface InvoiceService {

	public void saveEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payment);

	public void saveGas(InvoiceGas invoice, List<PaymentGas> payment);

	public void saveWater(InvoiceWater invoice, List<PaymentWater> payment);

	public List<InvoiceWater> getWaterInvoiceList();

	public List<InvoiceGas> getGasInvoiceList();

	public List<InvoiceEnergy> getEnergyInvoiceList();

	public void deleteGasByID(int id);

	public void deleteWaterByID(int id);

	public void deleteEnergyByID(int id);

	public void updateGas(InvoiceGas invoice, List<PaymentGas> payments);

	public void updateWater(InvoiceWater invoice, List<PaymentWater> payments);

	public void updateEnergy(InvoiceEnergy invoice, List<PaymentEnergy> payments);

	public InvoiceGas getGasByID(int id);

	public InvoiceWater getWaterByID(int id);

	public InvoiceEnergy getEnergyByID(int id);

	public List<InvoiceEnergy> getUnpaidInvoiceEnergy();

	public List<InvoiceGas> getUnpaidInvoiceGas();

	public List<InvoiceWater> getUnpaidInvoiceWater();

	public InvoiceEnergy getLatestPaidEnergy();

	public InvoiceWater getLatestPaidWater();

	public InvoiceGas getLatestPaidGas();
}
