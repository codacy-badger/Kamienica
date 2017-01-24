package kamienica.feature.invoice;

import kamienica.core.daoservice.BasicDao;
import kamienica.core.enums.PaymentStatus;
import kamienica.model.Invoice;

import java.util.List;

public interface InvoiceAbstractDao<I extends Invoice> extends BasicDao<I> {

	List<I> getUnpaidInvoices();

	I getLastResolved();
	
	void setResolvement(I invoice, PaymentStatus status);

	int getDaysOfLastInvoice();
}
