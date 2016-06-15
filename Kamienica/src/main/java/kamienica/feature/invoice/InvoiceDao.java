package kamienica.feature.invoice;

import java.util.List;

import kamienica.dao.DaoInterface;

public interface InvoiceDao<I extends Invoice> extends DaoInterface<I> {

	public I getLatest();

	public List<I> getInvoicesForCalulation(Invoice invoice);

	public List<I> getUnpaidInvoices();

	public I getLastResolved();

	public void resolveInvoice(I invoice);

	public void unresolveInvoice(int id);
	
	public int getDaysOfLastInvoice();
}
