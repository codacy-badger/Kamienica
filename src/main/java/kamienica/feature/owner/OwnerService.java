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

    private static final String EMPTY_APPS = "SELECT * FROM APARTMENT WHERE id in" +
            "(select A.id from apartment A JOIN TENANT ON tenant.APARTMENT_ID = APARTMENT.ID  where APARTMENT.residence_id in(%s) and apartmentNumber > 0 AND tenant.status='ACTIVE')";
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
        return apartmentDao.getBySQLQuery(String.format(EMPTY_APPS, idList));
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
