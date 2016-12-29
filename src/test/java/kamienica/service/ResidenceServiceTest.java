package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.model.Residence;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ResidenceServiceTest extends DatabaseTest {

    @Test
    @Transactional
    public void save() {
        final Residence res = new Residence("Świętojańska", "46", "Gdynia");
        residenceService.save(res);

        final List<Residence> result = residenceService.getList();
        assertEquals(3, result.size());
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    public void shouldThrowException() throws Exception {
        final Residence res = new Residence("Świętojańska", "45", "Gdynia");
        residenceService.save(res);

        final List<Residence> result = residenceService.getList();
        assertEquals(2, result.size());
    }

    @Test
    @Transactional
    public void update() throws Exception {
        Residence residence = residenceService.getById(1L);
        residence.setCity("Sopot");
        residenceService.update(residence);
    }

    @Test
    @Transactional
    public void getList() throws Exception {
        final List<Residence> result = residenceService.getList();
        assertEquals(2, result.size());
    }


    @Test
    @Transactional
    public void getById() throws Exception {
        final Residence result = residenceService.getById(1L);
        assertNotNull(result);
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    public void shouldThrowViolationExcpetionWheTryingToDelete() throws Exception {
        residenceService.deleteById(1L);
        final List<Residence> result = residenceService.getList();
        assertEquals(0, result.size());
    }

}