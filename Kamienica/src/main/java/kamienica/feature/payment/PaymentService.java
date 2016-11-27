package kamienica.feature.payment;

import java.util.List;

import kamienica.core.enums.Media;
import kamienica.feature.invoice.Invoice;
import kamienica.feature.tenant.Tenant;

public interface PaymentService {

	List<? extends Payment> getPaymentForTenant(Tenant tenant, Media media);

	List<? extends Payment> getPaymentByInvoice(Invoice invoice, Media media);

	List<? extends Payment> getPaymentList(Media media);

}
