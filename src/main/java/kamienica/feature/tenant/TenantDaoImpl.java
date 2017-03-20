package kamienica.feature.tenant;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Status;
import kamienica.model.jpa.dao.BasicDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("tenantDao")
public class TenantDaoImpl extends BasicDao<Tenant> implements ITenantDao {


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
