package kamienica.model.jpa.dao;

import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Set;

public interface IBasicDao<T> {

    void save(final T object);

    void deleteById(final Long id);

    void delete(T entity);

    void delete(final Long id);

    void update(final T object);

    List<T> getList();

    List<T> getList(Media media);

    List<T> findForResidence(List<Residence> res);

    List<T> paginatedList(final Integer firstResult, final Integer maxResults);

    List<T> findByCriteria(Order order, Criterion... criterion);

    List<T> findByCriteria(final Criterion... criterion);

    T findOneByCriteria(final Criterion... criterion);

    List<T> getBySQLQuery(final String queryString);

    T getOneBySQLQuery(final String queryString);

    List<T> findByCriteria(final int firstResult, final int maxResults, final Order order,
                           final Criterion... criterion);

    T getById(final Long id);

    //TODO investigate whether it;s used anymore
    Set<Long> getIdList(final Residence r, final Media media);

    long countByCriteria(final Criterion... criterion);


}
