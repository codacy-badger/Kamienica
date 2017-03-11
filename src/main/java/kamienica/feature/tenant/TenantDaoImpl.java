package kamienica.feature.tenant;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Status;
import kamienica.model.jpa.dao.BasicDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tenantDao")
public class TenantDaoImpl extends BasicDaoImpl<Tenant> implements ITenantDao {

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
