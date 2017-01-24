package kamienica.core.dao;

import kamienica.core.util.SecurityDetails;
import kamienica.model.Apartment;
import kamienica.model.Residence;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractDao<T> {

    @Autowired
    private SessionFactory sessionFactory;
    protected final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

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

    public void delete(Long id) {
        Object o = getById(id);
        getSession().delete(o);
    }

    @SuppressWarnings("unchecked")
    public List<T> getList() {
        Criteria criteria = createEntityCriteria();
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> paginatedList(Integer page, Integer maxResult) {
        Criteria criteria = createEntityCriteria();
        criteria.setFirstResult((page * maxResult) - maxResult);
        criteria.setMaxResults(maxResult);
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public Set<Long> getIdList() {
        Criteria criteria = createEntityCriteria().setProjection(Projections.property("id"));
        return new HashSet<>(criteria.list());
    }

    public long countByCriteria(final Criterion... criterion) {
        Criteria criteria = createEntityCriteria();
        criteria.setProjection(Projections.rowCount());

        for (final Criterion c : criterion) {
            criteria.add(c);
        }
        return (Long) criteria.list().get(0);
    }

    public List<T> findByCriteria(final Criterion... criterion) {
        Criteria criteria = createEntityCriteria();
        for (final Criterion c : criterion) {
            criteria.add(c);
        }
        return criteria.list();
    }


    public List<T> getBySQLQuery(final String queryString) {
        Query query = getSession().createSQLQuery(queryString);
        return query.list();
    }

    public List<T> findForResidence(final List<Residence> residences) {
        final Criterion forResidence = Restrictions.in("residence", residences);
        return findByCriteria(forResidence);
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

   public List<T> listForOwner() {
        final List<Residence> res = SecurityDetails.getResidencesForOwner();
        return findByCriteria(Restrictions.in("residence", res));
    }

    public List<T> listForTenant() {
        final Apartment ap = SecurityDetails.getApartmentForLoggedTenant();
        return findByCriteria(Restrictions.eq("apartment", ap));
    }


    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }

    protected String getTabName() {
        return persistentClass.getSimpleName().toLowerCase();
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
