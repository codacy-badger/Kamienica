package kamienica.feature.payment;

import kamienica.model.enums.Media;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Tenant;

import java.util.List;

public interface IPaymentService {

	List<Payment> getPaymentForTenant(Tenant tenant, Media media);

	List<Payment> getPaymentList(Media media);

}
