package kamienica.feature.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.meter.IMeterDao;
import kamienica.feature.payment.IPaymentDao;
import kamienica.feature.reading.IReadingDao;
import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Reading> getReadings() {
        final Tenant loggedTenant = SecurityDetails.getLoggedTenant();
        final Residence residence = loggedTenant.fetchApartment().getResidence();
        final Apartment sharedPart = apartmentDao.findOneByCriteria(Restrictions.eq("residence", residence), Restrictions.eq("apartmentNumber", 0));

        final List<Reading> readings = new ArrayList<>();
        for (final Media m : Media.values()) {
            final List<Meter> meters = meterDao.findByCriteria(Restrictions.in("apartment", Arrays.asList(sharedPart, loggedTenant.fetchApartment())), Restrictions.eq("media", m));
            final List<Reading> r = readingDao.findByCriteria(Order.desc("readingDetails"), Restrictions.in("meter", meters));
            readings.addAll(r);
        }
        return readings;
    }

    @Override
    public List<Payment> getPayments() {
        final Tenant tenant = SecurityDetails.getLoggedTenant();
        return paymentDao.findByCriteria(Restrictions.eq("tenant", tenant));
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
