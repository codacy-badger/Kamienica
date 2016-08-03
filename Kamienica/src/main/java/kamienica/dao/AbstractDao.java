package kamienica.dao;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> {

	protected final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	public String getTabName() {
		return this.persistentClass.getSimpleName().toString();
	}
	// @SuppressWarnings("unchecked")
	// public AbstractDao() {
	// // TODO Auto-generated constructor stub
	//
	// this.persistentClass = (Class<T>) this.getClass();
	// }

	@Autowired
	protected SessionFactory sessionFactory;

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// common methods shared by all entities
	// getByID
	// getById
	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		return (T) getSession().get(persistentClass, id);
	}

	public void save(T entity) {
		getSession().persist(entity);

	}

	public void update(T entity) {
		getSession().update(entity);

	}

	public void deleteById(Long id) {
		Query query = getSession()
				.createSQLQuery("delete from " + persistentClass.getSimpleName().toLowerCase() + " where id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> getList() {
		Criteria criteria = createEntityCriteria();
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public Set<Long> getIdList() {
		Criteria criteria = createEntityCriteria().setProjection(Projections.property("id"));
		return new HashSet<Long>(criteria.list());

	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final int firstResult, final int maxResults, final Order order,
			final Criterion... criterion) {
		Session session = getSession();
		Criteria crit = session.createCriteria(persistentClass);

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		if (order != null) {
			crit.addOrder(order);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}

		return crit.list();
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

}
