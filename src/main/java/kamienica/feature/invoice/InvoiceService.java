package kamienica.feature.invoice;

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

import java.util.List;

@Service
@Transactional
public class InvoiceService implements IInvoiceService {

    private final IInvoiceDao invoiceDao;
    private final IReadingDetailsDao readingDetailsDao;
    private final IPaymentService paymentService;

    @Autowired
    public InvoiceService(final IInvoiceDao invoiceDao, final IReadingDetailsDao readingDetailsDao, final IPaymentService paymentService) {
        this.invoiceDao = invoiceDao;
        this.readingDetailsDao = readingDetailsDao;
        this.paymentService = paymentService;
    }

    @Override
    public void save(final Invoice invoice) {
        updateReadingDetails(invoice, Resolvement.RESOLVED);
        paymentService.savePayments(invoice);
        invoiceDao.save(invoice);
    }

    @Override
    public void delete(final Invoice invoice) {
        updateReadingDetails(invoice, Resolvement.UNRESOLVED);

        paymentService.deleteForInvoice(invoice);
        invoiceDao.delete(invoice);
    }

    @Override
    public Invoice getByID(final Long id) {
        return invoiceDao.getById(id);
    }

    @Override
    public List<Invoice> list(final Media media, final Long residenceId) {
        final Residence residence = SecurityDetails.getResidenceForOwner(residenceId);
        final Criterion forTheseResidences = Restrictions.eq("residence", residence);
        final Criterion forMedia = Restrictions.eq("media", media);
        return invoiceDao.findByCriteria(forTheseResidences, forMedia);
    }

    private void updateReadingDetails(final Invoice invoice, final Resolvement resolved) {
        final ReadingDetails rd = invoice.getReadingDetails();
        rd.setResolvement(resolved);
        readingDetailsDao.update(rd);
    }
}
