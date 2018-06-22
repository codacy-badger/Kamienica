package kamienica.feature.payment;

import kamienica.feature.invoice.InvoiceDao;
import kamienica.feature.payment.calculator.IPaymentCalculator;
import kamienica.feature.residence.IResidenceDao;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private final IPaymentDao paymentDao;
    private final IPaymentCalculator paymentCalculator;
    private final IResidenceDao residenceDao;
    private final InvoiceDao invoiceDao;

    @Autowired
    public PaymentService(final IPaymentDao paymentDao, final IPaymentCalculator paymentCalculator, final IResidenceDao residenceDao, final InvoiceDao invoiceDao) {
        this.paymentDao = paymentDao;
        this.paymentCalculator = paymentCalculator;
        this.residenceDao = residenceDao;
        this.invoiceDao = invoiceDao;
    }

    @Override
    public List<Payment> getPaymentList(final Media media) {
        return paymentDao.getList(media);
    }

    @Override
    public List<Payment> getPaymentList(final Media media, final Long residenceId) {
        //TODO baad code. The whole service layer (or at least dao) should be redesigned
        final Residence r = residenceDao.getById(residenceId);
        final List<Invoice> invoice = invoiceDao.getList(r, media);
        if(invoice.isEmpty()) {
            return new ArrayList<>();
        }
        final Criterion c = Restrictions.in("invoice", invoice);

        return paymentDao.findByCriteria(c);
    }

    @Override
    public List<Payment> getPaymentForTenant(final Tenant tenant, final Media media) {
        return paymentDao.getPaymentForTenant(tenant, media);
    }

    @Override
    public void savePayments(final Invoice invoice) {
        final List<Payment> payments = paymentCalculator.createPaymentList(invoice);

        for (final Payment p : payments) {
            paymentDao.save(p);
        }
    }

    @Override
    public void deleteForInvoice(final Invoice invoice) {
        paymentDao.deleteForInvoice(invoice);
    }
}
