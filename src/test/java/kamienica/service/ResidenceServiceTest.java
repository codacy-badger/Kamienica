package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class ResidenceServiceTest extends ServiceTest {

    @Test
    @Transactional
    public void save() {
        final Residence res = new Residence("Świętojańska", "46", "Gdynia");

        final Tenant t = getOwner();
        List<Residence> residences = getMockedResidences();

        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residences);
        when(SecurityDetails.getLoggedTenant()).thenReturn(t);
        residenceService.save(res);

        final List<Residence> result = residenceService.getList();
        assertEquals(3, result.size());

        final List<ResidenceOwnership> ownerships = residenceOwnershipService.list();
        assertEquals(2, ownerships.size());

        final List<Apartment> ap = apartmentService.list();
        assertEquals(6, ap.size());

        final List<Meter> meterEnergies = meterService.getListForOwner(Media.ENERGY);
        assertEquals(5, meterEnergies.size());

    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    public void shouldThrowException() throws Exception {
        mockStatic(SecurityDetails.class);
        Tenant t = tenantService.getById(1L);
        when(SecurityDetails.getLoggedTenant()).thenReturn(t);

        final Residence res = new Residence("Świętojańska", "45", "Gdynia");


        residenceService.save(res);

        final List<Residence> result = residenceService.getList();
        assertEquals(2, result.size());
    }

    @Test
    @Transactional
    public void update() {
        final String city = "Sopot";
        Residence residence = residenceService.getById(1L);
        residence.setCity(city);
        residenceService.update(residence);
        residence = residenceService.getById(1L);
        assertEquals(city, residence.getCity());
    }

    @Test
    @Transactional
    public void getList() {
        final List<Residence> result = residenceService.getList();
        assertEquals(2, result.size());
    }

    @Test
    @Transactional
    public void getListForTenant() {
        Tenant t = tenantService.getById(1L);
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(t);

        final List<Residence> result = residenceService.listForOwner();
        assertEquals(1, result.size());
    }

    @Test
    public void getListForOwner() {
        Tenant t = tenantService.getById(1L);
        when(SecurityDetails.getLoggedTenant()).thenReturn(t);

        final List<Residence> residences = residenceService.listForOwner();
        assertEquals(1, residences.size());
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