package kamienica.feature.payment;

import java.util.List;

import kamienica.core.dao.DaoInterface;
import kamienica.model.Invoice;
import kamienica.feature.reading.Reading;
import kamienica.model.Tenant;

public interface PaymentDao<P extends Payment> extends DaoInterface<P> {

	void deleteByDate(String date);

	void deleteForInvoice(Invoice invoice);

	List<P> getByInvoice(Invoice invoice);

	List<P> getByReading(Reading reading);

	List<P> getPaymentForTenant(Tenant tenant);

	P getLatestPayment();

}
