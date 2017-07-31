package kamienica.feature.apartment;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import kamienica.model.jpa.dao.BasicDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("apartmentDao")
public class ApartmentDao extends BasicDao<Apartment> implements IApartmentDao {


    //TODO investigate this method
    @Override
    public int getNumOfEmptyApartment() {
        final String sql = "SELECT COUNT(id) from Apartment join";
        Query query = getSession().createSQLQuery(sql);
        return ((Number) query.uniqueResult()).intValue();
    }

    @Override
    public List<Apartment> getListForOwner(List<Residence> residences) {
        return findForResidence(residences);
    }


}
