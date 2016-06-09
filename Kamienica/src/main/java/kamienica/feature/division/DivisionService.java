package kamienica.feature.division;

import java.util.List;

public interface DivisionService {


	public void saveList(List<Division> division);
	
	public List<Division> getList();

	public void deleteByID(int id);

	public void update(Division division);

	public void deleteAll();
}
