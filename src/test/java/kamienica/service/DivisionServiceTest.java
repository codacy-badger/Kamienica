package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.exception.WrongDivisionInputException;
import kamienica.feature.division.DivisionForm;
import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.Residence;
import kamienica.model.Tenant;
import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore("Planning to get rid of Division class")
public class DivisionServiceTest extends ServiceTest {

    @Test
    public void getList() {
        final List<Division> list = divisionService.getList();
        assertEquals(12, list.size());
    }


    @Test
    @Transactional
    public void deleteAll() {
        divisionService.deleteAll();
        final List<Division> result = divisionService.getList();
        assertEquals(0, result.size());
        assertEquals(false, settingsService.isDivisionCorrect());
    }

    @Transactional
    @Test
    public void saveList() throws InvalidDivisionException {
        final List<Division> listToSave = createCorrectList(true);
        divisionService.saveList(listToSave, TODAY);
        final List<Division> result = divisionService.getList();
        for (Division d : result) {
            assertEquals(TODAY, d.getDate());
        }
    }

    @Ignore
    @Transactional
    @Test(expected = InvalidDivisionException.class)
    public void saveListShouldThrowException() throws InvalidDivisionException {
        //TODO move validation from controller to service
        final LocalDate date = new LocalDate();
        final List<Division> listToSave = createCorrectList(false);
        divisionService.saveList(listToSave, date);

    }

    @Test
    public void prepareForm() throws WrongDivisionInputException {
        DivisionForm form = new DivisionForm();
        divisionService.prepareForm(form);

        final List<Tenant> tenants = form.getTenants();
        final List<Apartment> apartments = form.getApartments();

        assertEquals(3, tenants.size());
        assertEquals(4, apartments.size());
    }

    @Test
    public void createDivisionForResidence() {
        Residence res = residenceService.getById(1L);
        List<Division> divisions = divisionService.createDivisionForResidence(res);
        assertEquals(12, divisions.size());
    }


    @Test
    @Ignore
    public void prepareDivisionListForRegistration() {

    }


    @Test
    public void isDivisionCorrect() {
        final boolean result = divisionService.isDivisionCorrect();
        assertEquals(true, result);
    }

    @Test
    @Ignore
    public void getMappedList() {

    }


    private List<Division> createCorrectList(boolean returnCorrect) {
        List<Apartment> apartments = apartmentService.list();
        List<Tenant> tenants = tenantService.getActiveTenants();
        List<Division> listToReturn = new ArrayList<>();
        for (Apartment a : apartments) {
            for (Tenant t : tenants) {
                double value = 1;
                if (returnCorrect) {
                    value = calculateValue(t, a);
                }
                Division d = new Division(TODAY, t, a, value);
                listToReturn.add(d);
            }
        }
        return listToReturn;
    }

    private double calculateValue(Tenant t, Apartment a) {
        if (a.getApartmentNumber() == 0) {
            return 0.33;
        }
        if (t.getApartment().getId().equals(a.getId())) {
            return 1;
        } else return 0;
    }

}
