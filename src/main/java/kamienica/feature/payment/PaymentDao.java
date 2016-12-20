package kamienica.feature.payment;

import kamienica.core.dao.DaoInterface;
import kamienica.feature.reading.Reading;
import kamienica.model.Invoice;
import kamienica.model.Tenant;

import java.util.List;

public interface PaymentDao<P extends Payment> extends DaoInterface<P> {

	void deleteByDate(String date);

	void deleteForInvoice(Invoice invoice);

	List<P> getByInvoice(Invoice invoice);

	List<P> getByReading(Reading reading);

	List<P> getPaymentForTenant(Tenant tenant);

	P getLatestPayment();

}
