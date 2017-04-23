package kamienica.feature.user;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.meter.IMeterDao;
import kamienica.feature.payment.IPaymentDao;
import kamienica.feature.reading.IReadingDao;
import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService implements IUserService {

    private final IApartmentDao apartmentDao;
    private final IReadingDao readingDao;
    private final IPaymentDao paymentDao;
    private final IReadingDetailsDao readingDetailsDao;
    private final IMeterDao meterDao;


    @Autowired
    public UserService(IApartmentDao apartmentDao, IReadingDao readingDao, IPaymentDao paymentDao, IReadingDetailsDao readingDetailsDao, IMeterDao meterDao) {
        this.apartmentDao = apartmentDao;
        this.readingDao = readingDao;
        this.paymentDao = paymentDao;
        this.readingDetailsDao = readingDetailsDao;
        this.meterDao = meterDao;
    }

    @Override
    public Map<String, List<Reading>> getMapOfReadings() {
        final Tenant loggedTenant = SecurityDetails.getLoggedTenant();

        final Residence r = loggedTenant.fetchApartment().getResidence();
        final Apartment sharedPart = apartmentDao.findOneByCriteria(Restrictions.eq("residence", r), Restrictions.eq("apartmentNumber", 0));

        Map<String, List<Reading>> map = new HashMap<>();
        for (Media m : Media.values()) {
            final List<Meter> meters = meterDao.findByCriteria(Restrictions.in("apartment", Arrays.asList(sharedPart, loggedTenant.fetchApartment())), Restrictions.eq("media", m));
            final List<Reading> readings = readingDao.findByCriteria(Order.desc("readingDetails"), Restrictions.in("meter", meters));
            map.put(m.toString(), readings);
        }
        return map;
    }

    @Override
    public Map<String, List<Payment>> getMapOfPayments() {
        final Map<String, List<Payment>> map = new HashMap<>();
        final Tenant tenant = SecurityDetails.getLoggedTenant();
        for (final Media m : Media.values()) {
            final List<Payment> p = paymentDao.findByCriteria(Restrictions.eq("tenant", tenant));
            map.put(m.toString(), p);
        }
        return map;
    }

    @Override
    public Tenant getTenantInfo() {
        return SecurityDetails.getLoggedTenant();
    }

    @Override
    public Map<String, ReadingDetails> getLatestReadingDates() {
        final Map<String, ReadingDetails> map = new HashMap<>();
        final Residence residence = SecurityDetails.getApartmentForLoggedTenant().getResidence();
        for (final Media m : Media.values()) {
            final ReadingDetails rd = readingDetailsDao.getLatest(residence, m);
            map.put(m.toString(), rd);
        }
        return map;
    }
}
