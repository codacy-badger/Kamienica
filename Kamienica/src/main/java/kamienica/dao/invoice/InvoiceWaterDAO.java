package kamienica.dao.invoice;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentWater;

public interface InvoiceWaterDAO {

	public void save(InvoiceWater invoice);

	public List<InvoiceWater> getList();

	public void update(InvoiceWater invoice);

	public void deleteByID(int id);

	public InvoiceWater getById(int id);

	public InvoiceWater getLatest();

	public List<InvoiceWater> getInvoicesForPayment(PaymentWater payment);

	public List<InvoiceWater> getInvoicesForCalulation(Invoice invoice);

	List<InvoiceWater> getUnpaidInvoices();

	public InvoiceWater getLastResolved();

	public void resolveInvoice(InvoiceWater invoice);
}
