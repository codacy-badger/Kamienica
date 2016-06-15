package kamienica.feature.division;

import java.util.List;

import org.joda.time.LocalDate;

public interface DivisionService {


	public void saveList(List<Division> division, LocalDate date);
	
	public List<Division> getList();

	public void deleteByID(Long id);

	public void update(Division division);

	public void deleteAll();
}
