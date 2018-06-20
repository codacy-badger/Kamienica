package kamienica.feature.invoice;

import java.util.List;
import kamienica.core.util.SecurityDetails;
import kamienica.feature.payment.IPaymentDao;
import kamienica.feature.payment.IPaymentService;
import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvoiceService implements IInvoiceService {

    private final IInvoiceDao invoiceDao;
    private final IPaymentDao paymentDao;
    private final IReadingDetailsDao readingDetailsDao;
    private final IPaymentService paymentService;

    @Autowired
    public InvoiceService(IInvoiceDao invoiceDao, IPaymentDao paymentDao, IReadingDetailsDao readingDetailsDao, IPaymentService paymentService) {
        this.invoiceDao = invoiceDao;
        this.paymentDao = paymentDao;
        this.readingDetailsDao = readingDetailsDao;
        this.paymentService = paymentService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void save(final Invoice invoice) {
        paymentService.savePayments(invoice);
        invoiceDao.save(invoice);
        final ReadingDetails rd = invoice.getReadingDetails();
        rd.setResolvement(Resolvement.RESOLVED);
        readingDetailsDao.update(rd);
    }

    @Override
    public void delete(final Long id) {
        final Invoice invoice = invoiceDao.getById(id);
        final ReadingDetails details = invoice.getReadingDetails();
        details.setResolvement(Resolvement.UNRESOLVED);

        readingDetailsDao.update(details);
        paymentDao.deleteForInvoice(invoice);
        invoiceDao.delete(id);
    }

    @Override
    public void delete(Invoice invoice) {
        invoiceDao.delete(invoice);
    }

    @Override
    public Invoice getByID(Long id) {
        return invoiceDao.getById(id);
    }

    @Override
    public List<Invoice> list(final Media media, final Long residenceId) {
        final Residence residence = SecurityDetails.getResidenceForOwner(residenceId);
        final Criterion forTheseResidences = Restrictions.eq("residence", residence);
        final Criterion forMedia = Restrictions.eq("media", media);
        return invoiceDao.findByCriteria(forTheseResidences, forMedia);
    }
}
