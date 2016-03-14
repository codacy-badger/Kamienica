package kamienica.dao.invoice;

import java.util.List;

import kamienica.dao.DaoInterface;
import kamienica.model.Invoice;
import kamienica.model.InvoiceWater;

public interface InvoiceWaterDAO extends DaoInterface<InvoiceWater> {

	public InvoiceWater getLatest();

	public List<InvoiceWater> getInvoicesForCalulation(Invoice invoice);

	List<InvoiceWater> getUnpaidInvoices();

	public InvoiceWater getLastResolved();

	public void resolveInvoice(InvoiceWater invoice);

	public void unresolveInvoice(int id);
}
