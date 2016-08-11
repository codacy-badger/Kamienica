package kamienica.core.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface DaoInterface<T extends Object> {

	public void save(T object);

	public List<T> getList();

	public void deleteById(Long id);

	public void update(T object);

	public T getById(Long id);

	public Set<Long> getIdList();

	public List<T> findByCriteria(final int firstResult, final int maxResults, final Order order,
			final Criterion... criterion);
}
