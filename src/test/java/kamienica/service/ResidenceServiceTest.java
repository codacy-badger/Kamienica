package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.core.enums.Media;
import kamienica.model.*;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Ignore;
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
        final Tenant t = tenantService.getTenantById(1L);
        residenceService.save(res, t);

        final List<Residence> result = residenceService.getList();
        assertEquals(3, result.size());

        final List<ResidenceOwnership> ownerships = residenceOwnershipService.list(t);
        assertEquals(2, ownerships.size());

        final List<Apartment> ap = apartmentService.getList();
        assertEquals(5, ap.size());

        final List<MeterEnergy> meterEnergies = meterService.getListForOwner(Media.ENERGY, t);
        assertEquals(6, meterEnergies.size());

    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    public void shouldThrowException() throws Exception {
        final Residence res = new Residence("Świętojańska", "45", "Gdynia");
        final Tenant t = tenantService.getTenantById(1L);
        residenceService.save(res, t);

        final List<Residence> result = residenceService.getList();
        assertEquals(2, result.size());
    }

    @Test
    @Transactional
    public void update() {
        Residence residence = residenceService.getById(1L);
        residence.setCity("Sopot");
        residenceService.update(residence);
    }

    @Test
    @Transactional
    public void getList()  {
        final List<Residence> result = residenceService.getList();
        assertEquals(2, result.size());
    }

    @Test
    @Transactional
    public void getListForTenant() {
        Tenant t = tenantService.getTenantById(1L);
        final List<Residence> result = residenceService.listForOwner(t);
        assertEquals(1, result.size());
    }


    @Test
    @Ignore
    public void getListForOwner() {
        final List<Residence> residences = residenceService.getList();
        assertEquals(1, residences.size());
    }

    @Test
    @Ignore
    public void getListForAdmin() {
        final List<Residence> residences = residenceService.getList();
        assertEquals(2, residences.size());
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