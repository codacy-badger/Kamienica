package kamienica.feature.apartment;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import kamienica.model.jpa.dao.BasicDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("apartmentDao")
public class ApartmentDao extends BasicDao<Apartment> implements IApartmentDao {

    //TODO change the method - add owner as the main parameter
    private static final String COUNT_EMPTY_APARTMENTS = "SELECT count(*) FROM apartment WHERE NOT EXISTS (SELECT * FROM tenant WHERE apartment.id = tenant.apartment_id AND tenant.status='ACTIVE' ) and apartmentNumber > 0";

    @Override
    public int getNumOfEmptyApartment() {
        Query query = getSession().createSQLQuery(COUNT_EMPTY_APARTMENTS);
        return ((Number) query.uniqueResult()).intValue();
    }

    @Override
    public List<Apartment> getListForOwner(List<Residence> residences) {
        return findForResidence(residences);
    }

}
