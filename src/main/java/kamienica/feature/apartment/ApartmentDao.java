package kamienica.feature.apartment;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import kamienica.model.jpa.dao.BasicDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("apartmentDao")
public class ApartmentDao extends BasicDao<Apartment> implements IApartmentDao {

    @Override
    public List<Apartment> getListForOwner(List<Residence> residences) {
        return findForResidence(residences);
    }


}
