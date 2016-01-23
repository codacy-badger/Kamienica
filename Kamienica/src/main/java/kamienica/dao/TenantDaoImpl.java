package kamienica.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.model.Tenant;
import kamienica.model.UserStatus;

@Repository("tenantDao")
public class TenantDaoImpl extends AbstractDao<Integer, Tenant> implements TenantDao {

	@Override
	public void save(Tenant tenant) {
	
//		Query query = session.createSQLQuery(
//				"update kamienica.tenant set apartment_id= null where apartment_id =:param"
//
//		).setParameter("param", tenant.getApartment().getId());
//		query.executeUpdate();
		super.save(tenant);
	}

	@SuppressWarnings("unchecked")
	public List<Tenant> getList() {
		List<Tenant> list = getSession().createCriteria(Tenant.class).addOrder(Order.desc("apartment"))
				.list();
		return list;
	}

	public void delete(int id) {
		Query query = getSession().createSQLQuery("delete from tenant where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();

	}

	//
	// public Tenant getById(int id) {
	// return getById(id);
	// }

	@SuppressWarnings("unchecked")
	public List<Tenant> getActiveTenants() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status", "ACTIVE"));
		return (List<Tenant>) criteria.list();
		//
		// Query query = getSession().createSQLQuery("Select * from
		// kamienica.tenant where apartment_id is not null");
		// return (List<Tenant>) query.list();

		// Criteria criteria = createEntityCriteria();
		// criteria.add(Restrictions.eq("ssn", ssn));
		// return (Employee) criteria.uniqueResult();
	}

	public Tenant loadByMail(String mail) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("email", mail));
		return (Tenant) criteria.uniqueResult();
	}

	@Override
	public void deactivate(Tenant tenant) {
		Query query = getSession().createSQLQuery("update tenant set status =:status where apartment_id =:id")
				.setParameter("id", tenant.getApartment().getId())
				.setParameter("status", UserStatus.INACTIVE.getUserStatus());
		query.executeUpdate();
		
	}

}
