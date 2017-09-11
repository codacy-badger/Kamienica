package kamienica.feature.payment;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.IBasicDao;

import java.util.List;

public interface IPaymentDao extends IBasicDao<Payment> {

	void deleteForInvoice(Invoice invoice);

	List<Payment> getPaymentForTenant(Tenant tenant, Media media);
}
