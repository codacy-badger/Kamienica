package kamienica.feature.invoice;

import java.util.List;

import kamienica.core.dao.DaoInterface;
import kamienica.feature.payment.PaymentStatus;
import kamienica.model.Invoice;

public interface InvoiceAbstractDao<I extends Invoice> extends DaoInterface<I> {

//	public I getLatest();


//	public List<I> getInvoicesForCalulation(Invoice invoice);

	List<I> getUnpaidInvoices();

	I getLastResolved();
	
	void setResolvement(I invoice, PaymentStatus status);
//
//	public void resolveInvoice(I invoice);
//
//	public void unresolveInvoice(int id);
	
	int getDaysOfLastInvoice();
}
