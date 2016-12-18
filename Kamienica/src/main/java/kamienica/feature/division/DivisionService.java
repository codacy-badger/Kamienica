package kamienica.feature.division;

import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.exception.WrongDivisionInputException;
import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.Tenant;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public interface DivisionService {

	void saveList(List<Division> division, LocalDate date);

	void saveList(DivisionForm form) throws InvalidDivisionException;

	List<Division> getList();
	
	Map<Tenant, List<Division>> getMappedList();

//	void update(Division division);

	void deleteAll();

	void prepareForm(DivisionForm form) throws WrongDivisionInputException;

	List<Division> prepareDivisionListForRegistration(List<Tenant> tenantList, List<Apartment> apartmentList);

	boolean isDivisionCorrect();
}
