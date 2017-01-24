package kamienica.core.dao;

import kamienica.model.Residence;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Set;

public interface DaoInterface<T> {

    void save(final T object);

    List<T> getList();

    List<T> paginatedList(final Integer firstResult, final Integer maxResults);

    void deleteById(final Long id);

    void delete(final Long id);

    void update(final T object);

    T getById(final Long id);

    Set<Long> getIdList();

    long countByCriteria(final Criterion... criterion);

    List<T> findByCriteria(final Criterion... criterion);

    List<T> getBySQLQuery(final String queryString);

    List<T> findByCriteria(final int firstResult, final int maxResults, final Order order,
                           final Criterion... criterion);

    List<T> findForResidence(List<Residence> res);
}
