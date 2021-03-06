package kamienica.feature.owner;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OwnerService implements IOwnerService {

    private final IApartmentDao apartmentDao;

    private static final String EMPTY_APPS = "SELECT * FROM APARTMENT WHERE id in" +
            "(select A.id from apartment A JOIN TENANT ON tenant.APARTMENT_ID = APARTMENT.ID  where APARTMENT.residence_id in(%s) and apartmentNumber > 0 AND tenant.status='ACTIVE')";
    @Autowired
    public OwnerService(IApartmentDao apartmentDao) {
        this.apartmentDao = apartmentDao;
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
        return apartmentDao.getBySQLQuery(String.format(EMPTY_APPS, idList));
    }

}
