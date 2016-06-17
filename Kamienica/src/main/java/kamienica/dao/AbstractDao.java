package kamienica.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

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
		Query query = getSession().createSQLQuery("delete from " + persistentClass.getSimpleName() + " where id = :id");
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
	public Set<PK> getIdList() {
		Criteria criteria = createEntityCriteria().setProjection(Projections.property("id"));
		return new HashSet<PK>(criteria.list());

	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

}
