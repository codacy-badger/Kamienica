package kamienica.core.calculator;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Reading;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;

import java.util.List;

public interface IPaymentCalculator {

    List<Payment> createPaymentList(final Invoice invoice, final List<Reading> readings) throws UsageCalculationException, NegativeConsumptionValue;
}
