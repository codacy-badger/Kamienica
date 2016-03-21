package kamienica.dao;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.PaymentAbstract;
import kamienica.model.ReadingAbstract;
import kamienica.model.Tenant;

public interface PaymentDao<P extends PaymentAbstract> extends DaoInterface<P> {

	public void deleteByDate(String date);

	public List<P> getByInvoice(Invoice invoice);

	public List<P> getByReading(ReadingAbstract reading);

	public List<P> getPaymentForTenant(Tenant tenant);

	public P getLatestPayment();
 
}
