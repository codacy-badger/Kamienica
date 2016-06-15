package kamienica.dao;

import java.util.List;

public interface DaoInterface<T extends Object> {

	
	public void save(T object);

	public List<T> getList();

	public void deleteById(Long id);

	public void update(T object);

	public T getById(Long id);
	

}
