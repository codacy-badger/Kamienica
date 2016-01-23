package kamienica.service;

import java.util.List;

import kamienica.model.Division;

public interface DivisionService {

	public void save(Division division);

	public void saveList(List<Division> division);
	
	public List<Division> getList();

	public void deleteByID(int id);

	public void update(Division division);

	public void deleteAll();
}
