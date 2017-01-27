package kamienica.feature.tenant;

import kamienica.core.daoservice.BasicDaoImpl;
import kamienica.core.enums.Status;
import kamienica.model.Apartment;
import kamienica.model.Tenant;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tenantDao")
public class TenantDaoImpl extends BasicDaoImpl<Tenant> implements TenantDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Tenant> getActiveTenants() {
        final Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("status", Status.ACTIVE));
        return criteria.list();
    }

    @Override
    public Tenant loadByMail(final String mail) {
        final Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", mail));
        return (Tenant) criteria.uniqueResult();
    }


    @Override
    public Tenant getTenantForApartment(final Apartment ap) {
        final Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("apartment", ap))
                .add(Restrictions.eq("status", Status.ACTIVE));

        return (Tenant) criteria.uniqueResult();
    }

}
