package kamienica.feature.tenant;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.feature.apartment.Apartment;

@Repository("tenantDao")
public class TenantDaoImpl extends AbstractDao<Long, Tenant> implements TenantDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Tenant> getActiveTenants() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status", UserStatus.ACTIVE.getUserStatus()));
		return criteria.list();
	}

	@Override
	public Tenant loadByMail(String mail) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("email", mail));
		return (Tenant) criteria.uniqueResult();
	}

	@Override
	public void deactivateByApparmentId(Long id) {

//		Query query2 = getSession().createSQLQuery("select * from tenant  where apartment_id =:id").setLong("id", id);
//		System.out.println("65666666666666666666666666");
//		System.out.println(query2.list().get(0));
//		Query query3 = getSession().createQuery("update Tenant set status =:status where Apartment_id = :id")
//				.setLong("id", id).setString("status", UserStatus.INACTIVE.getUserStatus());
//
		Query query = getSession().createSQLQuery("update tenant set status =:status where apartment_id =:id")
				.setLong("id", id).setString("status", UserStatus.INACTIVE.getUserStatus());
		query.executeUpdate();

	}

	@Override
	public Tenant getTenantForApartment(Apartment ap) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("apartment", ap))
				.add(Restrictions.eq("status", UserStatus.ACTIVE.getUserStatus()));

		return (Tenant) criteria.uniqueResult();
	}

}
