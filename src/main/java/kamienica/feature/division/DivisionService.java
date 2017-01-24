package kamienica.feature.division;

import kamienica.core.exception.WrongDivisionInputException;
import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.Residence;
import kamienica.model.Tenant;
import org.joda.time.LocalDate;

import java.util.List;

public interface DivisionService {

	void saveList(List<Division> division, LocalDate date);

	List<Division> getList();

	List<Division> createDivisionForResidence(Residence res);

	void deleteAll();

	void prepareForm(DivisionForm form) throws WrongDivisionInputException;

	List<Division> prepareDivisionList(List<Tenant> tenantList, List<Apartment> apartmentList);

	boolean isDivisionCorrect();
}
