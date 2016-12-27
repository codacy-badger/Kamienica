package kamienica.feature.payment;

import kamienica.core.enums.Media;
import kamienica.model.Invoice;
import kamienica.model.Payment;
import kamienica.model.Tenant;

import java.util.List;

public interface PaymentService {

	List<? extends Payment> getPaymentForTenant(Tenant tenant, Media media);

	List<? extends Payment> getPaymentByInvoice(Invoice invoice, Media media);

	List<? extends Payment> getPaymentList(Media media);

}
