package kamienica.feature.division;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.tenant.Tenant;

public interface DivisionService {


	public void saveList(List<Division> division, LocalDate date);
	
	public List<Division> getList();

	public void deleteByID(Long id);

	public void update(Division division);

	public void deleteAll();
	
	DivisionForm prepareForm();
	
	List<Division> prepareDivisionListForRegistration(List<Tenant> tenantList, List<Apartment> apartmentList);
}
