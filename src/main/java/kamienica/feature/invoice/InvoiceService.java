package kamienica.feature.invoice;

import kamienica.core.calculator.*;
import kamienica.core.util.SecurityDetails;
import kamienica.feature.division.IDivisionService;
import kamienica.feature.meter.IMeterService;
import kamienica.feature.payment.IPaymentDao;
import kamienica.feature.payment.IPaymentService;
import kamienica.feature.reading.IReadingDao;
import kamienica.feature.reading.IReadingService;
import kamienica.feature.readingdetails.ReadingDetailsDao;
import kamienica.feature.settings.ISettingsDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
import kamienica.model.enums.WaterHeatingSystem;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceService implements IInvoiceService {

    private final IInvoiceDao invoiceDao;
    private final IPaymentDao paymentDao;
    private final ReadingDetailsDao readingDetailsDao;
    private final IPaymentService paymentService;

    @Autowired
    public InvoiceService(IInvoiceDao invoiceDao, IPaymentDao paymentDao, ReadingDetailsDao readingDetailsDao, IPaymentService paymentService) {
        this.invoiceDao = invoiceDao;
        this.paymentDao = paymentDao;
        this.readingDetailsDao = readingDetailsDao;
        this.paymentService = paymentService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void save(final Invoice invoice) throws UsageCalculationException, NegativeConsumptionValue {
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
    public Invoice getByID(Long id) {
        return invoiceDao.getById(id);
    }

    @Override
    public List<Invoice> list(final Media media) {
        final List<Residence> residences = SecurityDetails.getResidencesForOwner();
        final Criterion forTheseResidences = Restrictions.in("residence", residences);
        final Criterion forMedia = Restrictions.eq("media", media);
        return invoiceDao.findByCriteria(forTheseResidences, forMedia);
    }
}
