package kamienica.feature.tenant;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.RentContract;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Status;
import kamienica.model.jpa.dao.BasicDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Tenant> getActiveTenants(List<Apartment> apartmentList) {
        final LocalDate now = new LocalDate();
        final DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RentContract.class);
        detachedCriteria.add(Restrictions.in("apartment", apartmentList));
        detachedCriteria.add(Restrictions.le("contractStart", now));
        detachedCriteria.add(Restrictions.ge("contractEnd", now));
        detachedCriteria.setProjection(Projections.property("id"));

        final Criteria c = createEntityCriteria();
        c.add(Property.forName("rentContract").in(detachedCriteria));
        return c.list();
    }

}
