package kamienica.feature.residence;

import kamienica.model.jpa.dao.BasicDaoImpl;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("residenceDao")
public class ResidenceDaoImpl extends BasicDaoImpl<Residence> implements ResidenceDao {
    @Override
    public List<Residence> listForOwner(Tenant t) {

       return createEntityCriteria()
                .setFetchMode("RESIDENCE_OWNERSHIP", FetchMode.JOIN)
                .add(Restrictions.eq("owner", t))
                .list();
    }
}
