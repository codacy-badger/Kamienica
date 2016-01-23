package kamienica.dao.invoice;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentWater;

public interface InvoiceDao {

	// shared

	public void save(Invoice invoice);

	public List<InvoiceWater> getWaterInvoiceList();

	public List<InvoiceEnergy> getEnergyInvoiceList();

	public List<InvoiceGas> getGasInvoiceList();

	// update

	public void update(Invoice invoice);

	public void deleteGasByID(int id);

	public void deleteEnergyByID(int id);

	public void deleteWaterByID(int id);

	// get by ID

	public Invoice getGasByID(int id);

	public Invoice getWaterByID(int id);

	public Invoice getEnergyByID(int id);

	// get latest

	public InvoiceGas getLatestGas();

	public InvoiceWater getLatestWater();

	public InvoiceEnergy getLatestEnergy();
	
	//invoices for payment

	public List<InvoiceWater> getInvoicesWaterForPayment(PaymentWater payment);

	public List<InvoiceGas> getInvoicesGasForPayment(PaymentGas payment);

	public List<InvoiceEnergy> getInvoicesEnergyForPayment(PaymentEnergy payment);
	
	//invoice for calculation
	
	public List<Invoice> getInvoicesWaterForCalulation(Invoice first, Invoice second );

	public List<Invoice> getInvoicesGasForCalulation(Invoice first, Invoice second );
	
	public List<Invoice> getInvoicesEnergyForCalulation(Invoice first, Invoice second );
}
