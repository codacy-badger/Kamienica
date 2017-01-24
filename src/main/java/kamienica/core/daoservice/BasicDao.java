package kamienica.core.daoservice;

import kamienica.model.Residence;
import kamienica.model.Tenant;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Set;

public interface BasicDao<T> {

    void save(final T object);

    void deleteById(final Long id);

    void delete(final Long id);

    void update(final T object);

    List<T> getList();

    List<T> findForResidence(List<Residence> res);

    List<T> findForOwner(final Tenant t);

    List<T> paginatedList(final Integer firstResult, final Integer maxResults);

    List<T> findByCriteria(final Criterion... criterion);

    List<T> getBySQLQuery(final String queryString);

    List<T> findByCriteria(final int firstResult, final int maxResults, final Order order,
                           final Criterion... criterion);

    T getById(final Long id);

    Set<Long> getIdList();

    long countByCriteria(final Criterion... criterion);


}
