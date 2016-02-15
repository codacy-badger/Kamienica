package kamienica.dao.invoice;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.InvoiceEnergy;
import kamienica.model.PaymentEnergy;

public interface InvoiceEnergyDAO {

	public void save(InvoiceEnergy invoice);

	public List<InvoiceEnergy> getList();

	public void update(InvoiceEnergy invoice);

	public void deleteByID(int id);

	public InvoiceEnergy getById(int id);

	public InvoiceEnergy getLatest();

	public List<InvoiceEnergy> getInvoicesForPayment(PaymentEnergy payment);

	public List<InvoiceEnergy> getInvoicesForCalulation(Invoice invoice);
	
	public List<InvoiceEnergy> getUnpaidInvoices();
	
	public InvoiceEnergy getLastResolved();
	
	public void resolveInvoice(InvoiceEnergy invoice);
	
}
