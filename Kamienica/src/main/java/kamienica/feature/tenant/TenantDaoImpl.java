package kamienica.feature.tenant;

import kamienica.core.dao.AbstractDao;
import kamienica.core.enums.Status;
import kamienica.model.Apartment;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tenantDao")
public class TenantDaoImpl extends AbstractDao<Tenant> implements TenantDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Tenant> getActiveTenants() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("status", Status.ACTIVE));
        return criteria.list();
    }

    @Override
    public Tenant loadByMail(String mail) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", mail));
        return (Tenant) criteria.uniqueResult();
    }

    @Override
    public Tenant getTenantForApartment(Apartment ap) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("apartment", ap))
                .add(Restrictions.eq("status", Status.ACTIVE));

        return (Tenant) criteria.uniqueResult();
    }

}
