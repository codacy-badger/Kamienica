package kamienica.feature.owner;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.payment.IPaymentDao;
import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Resolvement;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.StringJoiner;

@Service
@Transactional
public class OwnerService implements IOwnerService {

    private final IApartmentDao apartmentDao;
    private final IReadingDetailsDao readingDetailsDao;
    private final IPaymentDao paymentDao;
    private static final String COUNT_EMPTY_APARTMENTS =
            "SELECT * FROM apartment WHERE id in (SELECT apartment_id FROM tenant WHERE apartment.id = tenant.apartment_id AND tenant.status='INACTIVE' and residence_id in (%s))";
    private static final String COUNT_EMPTY_APARTMENTS2 =
            "SELECT * FROM apartment WHERE NOT EXISTS (SELECT * FROM tenant WHERE apartment.id = tenant.apartment_id AND tenant.status='ACTIVE') and residence_id in (%s)";


    private static final String COUNT_EMPTY_APARTMENTS3= "SELECT * FROM apartment WHERE NOT EXISTS (SELECT * FROM tenant WHERE apartment.id = tenant.apartment_id ) and apartmentNumber > 0 and residence_id in(%s)";
    @Autowired
    public OwnerService(IApartmentDao apartmentDao, IReadingDetailsDao readingDetailsDao, IPaymentDao paymentDao) {
        this.apartmentDao = apartmentDao;
        this.readingDetailsDao = readingDetailsDao;
        this.paymentDao = paymentDao;
    }

    @Override
    public List<Apartment> getEmptyApartments() {
        final List<Residence> residences = SecurityDetails.getResidencesForOwner();
        StringBuilder sb = new StringBuilder();
        for(Residence r:residences) {
            sb.append(r.getId());
            sb.append(",");
        }
        String idList = sb.toString();
        idList = idList.substring(0, idList.length()-1);
//        List<Residence> residences = SecurityDetails.getResidencesForOwner();
//        return apartmentDao.findByCriteria(Restrictions.in("residence", residences), Restrictions.gt("apartmentNumber", 0) );
        return apartmentDao.getBySQLQuery(String.format(COUNT_EMPTY_APARTMENTS3, idList));
    }

    @Override
    public List<ReadingDetails> getUnresolvedReadings() {
        final List<Residence> residences = SecurityDetails.getResidencesForOwner();
        return readingDetailsDao.findByCriteria(Restrictions.in("residence", residences), Restrictions.eq("resolvement", Resolvement.UNRESOLVED));
    }

    @Override
    public ReadingDetails getOldestReading() {
        return null;
    }

    @Override
    public Payment getOldestPayment() {
        return null;
    }
}
