package kamienica.feature.payment.calculator;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;

import java.util.List;

public interface IPaymentCalculator {
    List<Payment> createPaymentList(Invoice invoice);
}
