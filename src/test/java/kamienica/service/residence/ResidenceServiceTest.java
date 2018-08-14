package kamienica.service.residence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.entity.ResidenceOwnership;
import kamienica.model.enums.Media;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

@WithUserDetails(ServiceTest.OWNER)
public class ResidenceServiceTest extends ServiceTest {

    @Test
    @Transactional
    public void save() {
        final Residence res = new Residence("New", "Test", "Warszawa");
        residenceService.save(res);

        final List<Residence> result = residenceService.getList();
        assertEquals(3, result.size());

        final List<ResidenceOwnership> ownerships = residenceOwnershipService.list();
        assertEquals(2, ownerships.size());

        final List<Apartment> ap = apartmentService.list();
        assertEquals(7, ap.size());

        final List<Meter> meterEnergies = meterService.getListForOwner(Media.ENERGY);
        assertEquals(6, meterEnergies.size());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldThrowException() {
        final Residence res = new Residence("Swietojanska", "45", "Gdynia");
        System.out.println(residenceService.getList());
        residenceService.save(res);
        final List<Residence> result = residenceService.getList();
        System.out.println(residenceService.getList());
        assertEquals(2, result.size());
    }

    @Test
    @Transactional
    public void update() {
        final String city = "Sopot";
        final Residence residence = residenceService.getById(1L);
        residence.setCity(city);
        residenceService.update(residence);
        final Residence result = residenceService.getById(1L);
        assertEquals(city, result.getCity());
    }

    @Test
    public void getList() {
        final List<Residence> result = residenceService.getList();
        assertEquals(2, result.size());
    }

    @Test
    public void getListForTenant() {
        final List<Residence> result = residenceService.listForOwner();
        assertEquals(1, result.size());
    }

    @Test
    public void getListForOwner() {
        final List<Residence> residences = residenceService.listForOwner();
        assertEquals(1, residences.size());
    }

    @Test
    public void getById() {
        final Residence result = residenceService.getById(1L);
        assertNotNull(result);
    }
}