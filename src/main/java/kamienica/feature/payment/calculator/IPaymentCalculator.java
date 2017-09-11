package kamienica.feature.payment.calculator;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;

import java.util.List;

public interface IPaymentCalculator {
    List<Payment> createPaymentList(Invoice invoice) throws UsageCalculationException, NegativeConsumptionValue;
}
