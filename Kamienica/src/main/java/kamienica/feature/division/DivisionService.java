package kamienica.feature.division;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.exception.WrongDivisionInputException;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.tenant.Tenant;

public interface DivisionService {

	public void saveList(List<Division> division, LocalDate date);

	public void saveList(DivisionForm form) throws InvalidDivisionException;

	public List<Division> getList();
	
	public Map<Tenant, List<Division>> getMappedList();

	public void deleteByID(Long id);

	public void update(Division division);

	public void deleteAll();

	public void prepareForm(DivisionForm form) throws WrongDivisionInputException;

	List<Division> prepareDivisionListForRegistration(List<Tenant> tenantList, List<Apartment> apartmentList);

	public boolean isDivisionCorrect();
}
