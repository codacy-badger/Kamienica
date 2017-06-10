package kamienica.feature.ownerdata;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.invoice.IInvoiceDao;
import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.OwnerData;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
@Transactional
public class OwnerDataService implements IOwnerDataService {

    private final IReadingDetailsDao readingDetailsDao;
    private final IInvoiceDao invoiceDao;
    private final IApartmentDao apartmentDao;


    @Autowired
    public OwnerDataService(IReadingDetailsDao readingDetailsDao, IInvoiceDao invoiceDao,
                            IApartmentDao apartmentDao) {
        this.readingDetailsDao = readingDetailsDao;
        this.invoiceDao = invoiceDao;
        this.apartmentDao = apartmentDao;

    }


    @Override
    public OwnerData getMainData() {
        final List<Residence> ownerResidences = SecurityDetails.getResidencesForOwner();
        final int emptyApartments = apartmentDao.getNumOfEmptyApartment();
        final int numOfResidences = ownerResidences.size();
        final ReadingDetails oldestReading = findOldestReading(ownerResidences);
        final Invoice oldestInvoice = findOldestInvoice(ownerResidences);
        return new OwnerData(oldestInvoice, oldestReading, emptyApartments, numOfResidences);
    }

    private Invoice findOldestInvoice(final List<Residence> ownerResidences) {
        final List<Invoice> invoiceList = new ArrayList<>();

        final Criterion residences = Restrictions.in("residence", ownerResidences);

        for (Media media : Media.values()) {
            final Criterion mediaCrit = Restrictions.eq("media", media);
            final Invoice invoice = invoiceDao.findOneByCriteria(residences, mediaCrit);
            invoiceList.add(invoice);
        }
        return invoiceList.stream().min(Comparator.comparing(Invoice::getInvoiceDate)).get();
    }

    private ReadingDetails findOldestReading(final List<Residence> ownerResidences) {
        final List<ReadingDetails> readingDetailsList = new ArrayList<>();
        for (Residence r : ownerResidences) {
            for (Media media : Media.values()) {
                final ReadingDetails details = readingDetailsDao.getLatest(r, media);
                readingDetailsList.add(details);
            }
        }
        return readingDetailsList.stream().min(Comparator.comparing(ReadingDetails::getReadingDate)).get();
    }
}
