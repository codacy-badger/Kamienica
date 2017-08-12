package kamienica.feature.payment;

import kamienica.feature.payment.calculator.IPaymentCalculator;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private final IPaymentDao paymentDao;
    private final IPaymentCalculator paymentCalculator;

    @Autowired
    public PaymentService(final IPaymentDao paymentDao, IPaymentCalculator paymentCalculator) {
        this.paymentDao = paymentDao;
        this.paymentCalculator = paymentCalculator;
    }

    @Override
    public List<Payment> getPaymentList(final Media media) {
        return paymentDao.getList(media);
    }

    @Override
    public List<Payment> getPaymentForTenant(final Tenant tenant, final Media media) {
        return paymentDao.getPaymentForTenant(tenant, media);
    }

    @Override
    public void savePayments(final Invoice invoice) throws UsageCalculationException, NegativeConsumptionValue {
        final List<Payment> payments = paymentCalculator.createPaymentList(invoice);

        for (Payment p : payments) {
            paymentDao.save(p);
        }
    }
}
