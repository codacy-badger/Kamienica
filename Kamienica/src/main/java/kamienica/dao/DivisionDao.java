package kamienica.dao;

import java.util.List;

import kamienica.model.Division;

public interface DivisionDao {

	public void save(Division division);
	
	public void saveList(List<Division> division);

	public List<Division> getList();

	public void deleteByID(int id);

	public void update(Division division);

	public void deleteAll();

}
