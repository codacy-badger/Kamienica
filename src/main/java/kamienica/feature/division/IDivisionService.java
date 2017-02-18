package kamienica.feature.division;

import kamienica.model.exception.WrongDivisionInputException;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Division;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import org.joda.time.LocalDate;

import java.util.List;

public interface IDivisionService {

	void saveList(List<Division> division, LocalDate date);

	List<Division> getList();

	List<Division> createDivisionForResidence(Residence res);

	void deleteAll();

	void prepareForm(DivisionForm form) throws WrongDivisionInputException;

	List<Division> prepareDivisionList(List<Tenant> tenantList, List<Apartment> apartmentList);

	boolean isDivisionCorrect();
}
