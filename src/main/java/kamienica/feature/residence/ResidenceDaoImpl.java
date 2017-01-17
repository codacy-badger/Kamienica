package kamienica.feature.residence;

import kamienica.core.dao.AbstractDao;
import kamienica.model.Residence;
import kamienica.model.Tenant;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("residenceDao")
public class ResidenceDaoImpl extends AbstractDao<Residence> implements ResidenceDao {
    @Override
    public List<Residence> listForOwner(Tenant t) {

       return createEntityCriteria()
                .setFetchMode("RESIDENCE_OWNERSHIP", FetchMode.JOIN)
                .add(Restrictions.eq("owner", t))
                .list();
    }
}
