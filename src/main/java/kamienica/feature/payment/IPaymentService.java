package kamienica.feature.payment;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;

import java.util.List;

public interface IPaymentService {

	List<Payment> getPaymentForTenant(Tenant tenant, Media media);

	List<Payment> getPaymentList(Media media);

	void savePayments(Invoice invoice) throws UsageCalculationException, NegativeConsumptionValue;
}
