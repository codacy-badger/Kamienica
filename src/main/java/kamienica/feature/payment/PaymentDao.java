package kamienica.feature.payment;

import kamienica.core.daoservice.BasicDao;
import kamienica.model.Invoice;
import kamienica.model.Payment;
import kamienica.model.Reading;
import kamienica.model.Tenant;

import java.util.List;

public interface PaymentDao<P extends Payment> extends BasicDao<P> {

	void deleteByDate(String date);

	void deleteForInvoice(Invoice invoice);

	List<P> getByInvoice(Invoice invoice);

	List<P> getByReading(Reading reading);

	List<P> getPaymentForTenant(Tenant tenant);

	P getLatestPayment();

}
