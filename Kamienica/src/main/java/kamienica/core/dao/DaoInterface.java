package kamienica.core.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface DaoInterface<T> {

	void save(T object);

	List<T> getList();
	
	List<T> paginatedList(Integer firstResult, Integer maxResults);

	void deleteById(Long id);

	void update(T object);

	T getById(Long id);

	Set<Long> getIdList();
	
	

	List<T> findByCriteria(final int firstResult, final int maxResults, final Order order,
                           final Criterion... criterion);
}
