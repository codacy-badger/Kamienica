package kamienica.feature.owner;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.meter.MeterDao;
import kamienica.feature.reading.ReadingDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class OwnerUserDataService implements IOwnerUserDataService {

    private final ApartmentDao apartmentDao;
    private final MeterDao meterDao;
    private final ReadingDao readingDao;

    @Autowired
    public OwnerUserDataService(ApartmentDao apartmentDao, MeterDao meterDao, ReadingDao readingDao) {
        this.apartmentDao = apartmentDao;
        this.meterDao = meterDao;
        this.readingDao = readingDao;
    }

    @Override
    public List<Reading> getReadingsForTenant(Apartment apartment, Media media) {
        final Tenant loggedTenant = SecurityDetails.getLoggedTenant();

        final Residence r = loggedTenant.fetchApartment().getResidence();
        final Apartment sharedPart = apartmentDao.findOneByCriteria(Restrictions.eq("residence", r), Restrictions.eq("apartmentNumber", 0));

        final List<Meter> meters = meterDao.findByCriteria(Restrictions.in("apartment", Arrays.asList(sharedPart, loggedTenant.fetchApartment())), Restrictions.eq("media", media));
        return readingDao.findByCriteria(Order.desc("readingDetails"), Restrictions.in("meter", meters));
    }

    public Tenant getLoggedTenant() {
        SecurityUser su = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return su.getTenant();
    }
}
