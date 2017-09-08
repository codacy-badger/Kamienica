package kamienica.model.jpa.dao;

import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
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

public abstract class BasicDao<T> implements IBasicDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;
    protected final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public BasicDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(Long id) {
        return (T) getSession().get(persistentClass, id);
    }

    @Override
    public void save(T entity) {
        getSession().persist(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);

    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }


    //TODO there are three delete methods...
    @Override
    public void delete(Long id) {
        Query query = getSession()
                .createSQLQuery("delete from " + persistentClass.getSimpleName().toLowerCase() + " where id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public void delete(List<T> entities) {
        for (T object : entities) {
            getSession().delete(object);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getList() {
        Criteria criteria = createEntityCriteria();
        return criteria.list();
    }

    @Override
    public List<T> getList(final Media media) {
        final Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("media", media));
        //noinspection unchecked
        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> paginatedList(Integer page, Integer maxResult) {
        Criteria criteria = createEntityCriteria();
        criteria.setFirstResult((page * maxResult) - maxResult);
        criteria.setMaxResults(maxResult);
        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Long> getIdList(final Residence r, final Media media) {
        final Criteria c = createEntityCriteria();
        c.add(Restrictions.eq("media", media));
        c.add(Restrictions.eq("residence", r));
        c.setProjection(Projections.property("id"));
        return new HashSet<>(c.list());
    }

    @Override
    public long countByCriteria(final Criterion... criterion) {
        Criteria criteria = createEntityCriteria();
        criteria.setProjection(Projections.rowCount());
        for (final Criterion c : criterion) {
            criteria.add(c);
        }
        return (Long) criteria.list().get(0);
    }

    @Override
    public List<T> findByCriteria(final Criterion... criterion) {
        Criteria criteria = createEntityCriteria();
        for (final Criterion c : criterion) {
            criteria.add(c);
        }
        //noinspection unchecked
        return criteria.list();
    }

    @Override
    public T findOneByCriteria(Criterion... criterion) {
        Criteria criteria = createEntityCriteria();
        for (final Criterion c : criterion) {
            criteria.add(c);
        }
        //noinspection unchecked
        return (T) criteria.uniqueResult();
    }

    @Override
    public List<T> getBySQLQuery(final String queryString) {
        Query query = getSession().createSQLQuery(queryString);
        //noinspection unchecked
        return query.list();
    }

    //TODO done only for readfing details. switch to criteria when possible
    @Override
    public T getOneBySQLQuery(final String queryString) {
        Query query = getSession().createSQLQuery(queryString);
        //noinspection unchecked
        return (T) query.uniqueResult();
    }

    @Override
    public List<T> findForResidence(final List<Residence> residences) {
        final Criterion forResidence = Restrictions.in("residence", residences);
        return findByCriteria(forResidence);
    }

    @Override
    public List<T> findByCriteria(final Order order, final Criterion... criterion) {
        Session session = getSession();
        Criteria crit = session.createCriteria(persistentClass);
        for (final Criterion c : criterion) {
            crit.add(c);
        }

        if (order != null) {
            crit.addOrder(order);
        }

        //noinspection unchecked
        return crit.list();
    }

    @Override
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

    protected String getTabName() {
        return persistentClass.getSimpleName().toLowerCase();
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
