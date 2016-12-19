package kamienica.feature.invoice;

import kamienica.core.dao.DaoInterface;
import kamienica.core.enums.PaymentStatus;
import kamienica.model.Invoice;

import java.util.List;

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
