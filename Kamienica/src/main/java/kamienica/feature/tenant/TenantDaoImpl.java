package kamienica.feature.tenant;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;

@Repository("tenantDao")
public class TenantDaoImpl extends AbstractDao<Integer, Tenant> implements TenantDao {

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
		Query query = getSession().createSQLQuery("update tenant set status =:status where apartment_id =:id")
				.setLong("id", id).setParameter("status", UserStatus.INACTIVE.getUserStatus());
		query.executeUpdate();

	}

}
