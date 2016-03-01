package kamienica.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.model.Tenant;
import kamienica.model.UserStatus;

@Repository("tenantDao")
public class TenantDaoImpl extends AbstractDao<Integer, Tenant> implements TenantDao {

	public void delete(int id) {
		Query query = getSession().createSQLQuery("delete from tenant where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	public List<Tenant> getActiveTenants() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status", UserStatus.ACTIVE.getUserStatus()));
		return (List<Tenant>) criteria.list();
	}

	public Tenant loadByMail(String mail) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("email", mail));
		return (Tenant) criteria.uniqueResult();
	}

	@Override
	public void deactivateByApparmentId(int id) {
		Query query = getSession().createSQLQuery("update tenant set status =:status where apartment_id =:id")
				.setParameter("id", id).setParameter("status", UserStatus.INACTIVE.getUserStatus());
		query.executeUpdate();

	}

}
