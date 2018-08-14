package kamienica.service.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.RentContract;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.UserRole;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

@WithUserDetails(ServiceTest.OWNER)
public class TenantServiceTest extends ServiceTest {

    private final static String dummyMail = "dummy@dummy";

    @Test
    public void getList() {
        final List<Tenant> list = tenantService.list();
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
        final List<Tenant> list = tenantService.getOwners();
        assertEquals(2, list.size());
        final long id = list.get(0).getId();
        assertEquals(1L, id);
    }

    @Test
    public void loadByMail() {
        final Tenant tenant = tenantService.loadByMail(FIRST_OWNER_MAIL);
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
        final Tenant previousOwner = tenantService.loadByMail(FIRST_OWNER_MAIL);
        final LocalDate previousOwnerContractEnd = previousOwner.getRentContract().getContractEnd();
        final boolean isOneDayBefore = previousOwnerContractEnd.equals(movementDate.minusDays(1));
        assertTrue(isOneDayBefore);
    }

    @Transactional
    @Test
    public void shouldDeactivateNewTenantWhenMovementDateIsOlderThanCurrentTenant() {
        final Tenant newOwner = createTenant(LocalDate.parse("2015-01-01"));
        assertTrue(newOwner.checkIsActive());
        tenantService.save(newOwner);
        final Tenant previousOwner = tenantService.loadByMail(FIRST_OWNER_MAIL);
        assertTrue(previousOwner.checkIsActive());
        assertFalse(newOwner.checkIsActive());
    }

    @Transactional
    @Test
    public void shouldAddOwnerWithNoContract() {
        final Tenant t = createDummyTenant();
        t.setRole(UserRole.OWNER);
        tenantService.save(t);
        assertNotNull(t.getId());
    }

    @Transactional
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForAddingTenantWithNoContract() {
        final Tenant t = createDummyTenant();
        t.setRole(UserRole.TENANT);
        tenantService.save(t);
    }

    private Tenant createDummyTenant() {
        return new Tenant("firstName", "lastName", "email@mail", "1111", null);
    }

    private Tenant createTenant(final LocalDate localDate) {
        final Apartment apartment = apartmentService.getById(2L);
        final Tenant tenant = createDummyTenant();
        final RentContract rc = new RentContract(apartment, 100, localDate);
        tenant.setRole(UserRole.TENANT);
        tenant.setRentContract(rc);
        return tenant;
    }
}
