package kamienica.feature.apartment;

import kamienica.core.dao.AbstractDao;
import kamienica.model.Apartment;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("apatmentDao")
public class ApartmentDaoImpl extends AbstractDao<Apartment> implements ApartmentDao {

    private static final String COUNT_EMPTY_APARTMENTS = "SELECT count(*) FROM apartment WHERE NOT EXISTS (SELECT * FROM tenant WHERE apartment.id = tenant.apartment_id ) and apartmentNumber > 0";

    @Override
    public int getNumOfEmptyApartment() {
        Query query = getSession().createSQLQuery(COUNT_EMPTY_APARTMENTS);
        return ((Number) query.uniqueResult()).intValue();
    }


}
