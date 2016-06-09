package kamienica.feature.division;

import kamienica.dao.DaoInterface;

public interface DivisionDao extends DaoInterface<Division> {

//	public void save(Division division);
	
//	public void saveList(List<Division> division);

//	public List<Division> getList();

//	public void deleteByID(int id);

//	public void update(Division division);

	public void deleteAll();

}
