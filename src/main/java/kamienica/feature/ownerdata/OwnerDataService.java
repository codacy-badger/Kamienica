package kamienica.feature.ownerdata;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.invoice.IInvoiceDao;
import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.feature.tenant.ITenantDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


@Service
@Transactional
public class OwnerDataService implements IOwnerDataService {

    private final IReadingDetailsDao readingDetailsDao;
    private final IInvoiceDao invoiceDao;
    private final IApartmentDao apartmentDao;
    private final ITenantDao tenantDao;

    @Autowired
    public OwnerDataService(IReadingDetailsDao readingDetailsDao, IInvoiceDao invoiceDao,
                            IApartmentDao apartmentDao, ITenantDao tenantDao) {
        this.readingDetailsDao = readingDetailsDao;
        this.invoiceDao = invoiceDao;
        this.apartmentDao = apartmentDao;
        this.tenantDao = tenantDao;
    }

    @Override
    public OwnerData getMainData() {
        final List<Residence> ownerResidences = SecurityDetails.getResidencesForOwner();
        if (ownerResidences.isEmpty()) {
            return new OwnerData();
        }
        final int emptyApartments = countEmptyApartments(ownerResidences);
        final int numOfResidences = ownerResidences.size();
        final ReadingDetails oldestReading = findOldestReading(ownerResidences);
        final Invoice oldestInvoice = findOldestInvoice(ownerResidences);

        return new OwnerData(oldestInvoice, oldestReading, emptyApartments, numOfResidences);
    }

    private int countEmptyApartments(final List<Residence> ownerResidences) {
        final List<Apartment> apartments = apartmentDao.findByCriteria(Restrictions.in("residence", ownerResidences));
        for (int i = 0; i < apartments.size(); i++) {
            if (apartments.get(i).getApartmentNumber() == 0) {
                apartments.remove(i);
                i--;
            }
        }
        final List<Tenant> activeTenants = tenantDao.getActiveTenants(apartments);
        int numOfEmptyApartments = apartments.size();
        for (final Apartment a : apartments) {
            final Long appId = a.getId();
            for (final Tenant t : activeTenants) {
                if (Objects.equals(t.getRentContract().getApartment().getId(), appId)) {
                    numOfEmptyApartments--;
                }
            }
        }
        return numOfEmptyApartments;
    }

    private Invoice findOldestInvoice(final List<Residence> ownerResidences) {
        final List<Invoice> invoiceList = new ArrayList<>();

        for (final Media media : Media.values()) {
            ownerResidences.forEach(residence -> {
                final Invoice invoice = invoiceDao.getLatest(residence, media);
                invoiceList.add(invoice);
            });
        }
        return invoiceList.stream()
            .min(Comparator.comparing(Invoice::getInvoiceDate))
            .get();
    }

    private ReadingDetails findOldestReading(final List<Residence> ownerResidences) {
        final List<ReadingDetails> readingDetailsList = new ArrayList<>();
        for (final Residence r : ownerResidences) {
            for (final Media media : Media.values()) {
                final ReadingDetails details = readingDetailsDao.getLatest(r, media);
                readingDetailsList.add(details);
            }
        }
        return readingDetailsList.stream().min(Comparator.comparing(ReadingDetails::getReadingDate)).get();
    }
}
