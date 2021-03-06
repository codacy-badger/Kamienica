package kamienica.service.user;

import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.RentContract;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.UserRole;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TenantServiceTest extends ServiceTest {

    private final static String dummyMail = "dummy@dummy";

    @Test
    public void getList() {
        List<Tenant> list = tenantService.list();
        assertEquals(7, list.size());
    }

    @Test
    public void shouldGetOnlyAciveTenants() {
        final Residence r = residenceService.getById(1L);
        final List<Tenant> result = tenantService.listActiveTenants(r);
        assertEquals(3, result.size());
    }

    @Test
    public void getOwners() {
        List<Tenant> list = tenantService.getOwners();
        assertEquals(2, list.size());
        final long id = list.get(0).getId();
        assertEquals(1L, id);
    }

    @Test
    public void loadByMail() {
        Tenant tenant = tenantService.loadByMail(FIRST_OWNER_MAIL);
        assertNotNull(tenant);
        assertEquals("LivesAndOwns 1Residence", tenant.fullName());
    }


    @Transactional
    @Test
    public void shouldDeactivateOldTenantWhenNewIsInserted() {
        final LocalDate movementDate = LocalDate.parse("2017-03-10");
        final Tenant newOwner = createTenant(movementDate);
        newOwner.setRole(UserRole.OWNER);
        tenantService.save(newOwner);
        Tenant previousOwner = tenantService.loadByMail(FIRST_OWNER_MAIL);
        LocalDate previousOwnerContractEnd = previousOwner.getRentContract().getContractEnd();
        final boolean isOneDayBefore = previousOwnerContractEnd.equals(movementDate.minusDays(1));
        assertTrue(isOneDayBefore);
    }

    @Transactional
    @Test
    public void shouldDeactivateNewTenantWhenMovementDateIsOlderThanCurrentTenant() {
        final Tenant newOwner = createTenant(LocalDate.parse("2015-01-01"));
        assertTrue(newOwner.checkIsActive());
        tenantService.save(newOwner);
        Tenant previousOwner = tenantService.loadByMail(FIRST_OWNER_MAIL);
        assertTrue(previousOwner.checkIsActive());
        assertFalse(newOwner.checkIsActive());
    }

    @Transactional
    @Test
    public void shouldAddOwnerWithNoContract() {
        Tenant t = new Tenant("firstName", "lastName", "email@mail", "111", null);
        t.setRole(UserRole.OWNER);
        tenantService.save(t);
        assertNotNull(t.getId());
    }

    @Transactional
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForAddingTenantWithNoContract() {
        Tenant t = new Tenant("firstName", "lastName", "email@mail", "1111", null);
        t.setRole(UserRole.TENANT);
        tenantService.save(t);
    }


    private Tenant createTenant(final LocalDate localDate) {
        final Apartment apartment = apartmentService.getById(2L);
        Tenant tenant = new Tenant();
        final RentContract rc = new RentContract(apartment, 100, localDate);

        tenant.setEmail(dummyMail);
        tenant.setFirstName("dummy");
        tenant.setLastName("dumy");
        tenant.setPassword("dummy");
        tenant.setRole(UserRole.TENANT);
        tenant.setRentContract(rc);
        return tenant;
    }
}
