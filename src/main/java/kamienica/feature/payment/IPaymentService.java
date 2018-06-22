package kamienica.feature.payment;

import java.util.List;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;

public interface IPaymentService {

	List<Payment> getPaymentForTenant(Tenant tenant, Media media);

	List<Payment> getPaymentList(Media media);

	List<Payment> getPaymentList(Media media, Long residenceId);

	void savePayments(Invoice invoice);

	void deleteForInvoice(Invoice invoice);
}
