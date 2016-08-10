package kamienica.feature.invoice;

import java.util.List;

import kamienica.core.dao.DaoInterface;
import kamienica.feature.payment.PaymentStatus;

public interface InvoiceAbstractDao<I extends Invoice> extends DaoInterface<I> {

//	public I getLatest();


//	public List<I> getInvoicesForCalulation(Invoice invoice);

	public List<I> getUnpaidInvoices();

	public I getLastResolved();
	
	public void setResolvement(I invoice, PaymentStatus status);
//
//	public void resolveInvoice(I invoice);
//
//	public void unresolveInvoice(int id);
	
	public int getDaysOfLastInvoice();
}
