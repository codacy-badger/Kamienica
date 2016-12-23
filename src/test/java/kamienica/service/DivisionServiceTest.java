package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.exception.WrongDivisionInputException;
import kamienica.model.Division;
import kamienica.testutils.EntityProvider;
import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DivisionServiceTest extends DatabaseTest {


    final LocalDate date = new LocalDate();

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
    @Ignore
    public void saveList() throws InvalidDivisionException {

        divisionService.saveList(EntityProvider.DIVISION, date);
        final List<Division> result = divisionService.getList();
        for (Division d : result) {
            assertEquals(date, d.getDate());
        }
    }

    @Transactional
    @Ignore
    @Test(expected = InvalidDivisionException.class)
    public void saveListShouldThrowException() throws InvalidDivisionException {
        final LocalDate date = new LocalDate();
        divisionService.saveList(EntityProvider.DIVISION_WRONG, date);

    }

    @Test
    @Ignore
    public void prepareForm() throws WrongDivisionInputException {
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

}
