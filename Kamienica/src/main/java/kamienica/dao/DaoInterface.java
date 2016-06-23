package kamienica.dao;

import java.util.List;
import java.util.Set;

public interface DaoInterface<T extends Object> {

	
	public void save(T object);

	public List<T> getList();

	public void deleteById(Long id);

	public void update(T object);

	public T getById(Long id);
	
	public Set<Long> getIdList();
}
