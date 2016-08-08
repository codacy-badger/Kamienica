package kamienica.feature.payment;

import java.util.List;

import kamienica.core.dao.DaoInterface;
import kamienica.feature.invoice.Invoice;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.tenant.Tenant;

public interface PaymentDao<P extends PaymentAbstract> extends DaoInterface<P> {

	public void deleteByDate(String date);

	public void deleteForInvoice(Invoice invoice);

	public List<P> getByInvoice(Invoice invoice);

	public List<P> getByReading(ReadingAbstract reading);

	public List<P> getPaymentForTenant(Tenant tenant);

	public P getLatestPayment();

}
