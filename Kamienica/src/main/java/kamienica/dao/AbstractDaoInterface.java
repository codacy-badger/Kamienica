package kamienica.dao;

import java.util.List;

public interface AbstractDaoInterface<O extends Object> {

	
	public void save(Object object);

	public List<Object> getList();

	public void deleteByID(int id);

	public void update(Object object);

	public Object getById(int id);
}
