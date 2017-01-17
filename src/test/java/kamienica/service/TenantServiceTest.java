package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.core.enums.Status;
import kamienica.core.enums.UserRole;
import kamienica.model.Apartment;
import kamienica.model.Residence;
import kamienica.model.Tenant;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TenantServiceTest extends DatabaseTest {

    private final String dummyMail = "dummy@dummy";
    private final String tenantMail = "folik@wp.pl";

    @Test
    public void getList() {
        List<Tenant> list = tenantService.getList();
        assertEquals(5, list.size());
    }

    @Test
    public void shouldGetOnlyAciveTenants() {
        final Criterion active = Restrictions.eq("status", Status.ACTIVE);
        final Criterion tenant = Restrictions.eq("role", UserRole.TENANT);

        final List<Tenant> result = tenantService.findByCriteria(active, tenant);
        assertEquals(2, result.size());
    }

    @Test
    public void getActiveTenants() {
        List<Tenant> list = tenantService.getActiveTenants();
        assertEquals(3, list.size());
    }

    @Test
    public void getOwners() {
        List<Tenant> list = tenantService.getOwners();
        assertEquals(1, list.size());
        final long id = list.get(0).getId();
        assertEquals(1L, id );
    }

    @Test
    public void loadByMail() {
        Tenant tenant = tenantService.loadByMail(tenantMail);
        assertNotNull(tenant);
        assertEquals("Maciej Folik", tenant.fullName());
    }


    @Transactional
    @Test
    public void shouldDeactivateOldTenantWhenNewIsInserted() {
        final Tenant newOwner = createTenant(LocalDate.parse("2018-01-01"));
        tenantService.saveTenant(newOwner);
        Tenant previousOwner = tenantService.loadByMail(tenantMail);
        assertEquals(Status.INACTIVE, previousOwner.getStatus());
    }

    @Ignore
    @Test
    public void ownerShouldHaveOneResidence() {
        final Tenant owner = tenantService.getTenantById(1L);
      //  final List<Residence> residenceList = owner.getResidencesOwned();
//        assertEquals(1, residenceList.size());
    }//


    @Transactional
    @Test
    public void shouldDeactivateNewTenantWhenMovementDateIsOlderThanCurrentTenant() {
        final Tenant newOwner = createTenant(LocalDate.parse("2015-01-01"));
        assertEquals(Status.ACTIVE, newOwner.getStatus());
        tenantService.saveTenant(newOwner);
        Tenant previousOwner = tenantService.loadByMail(tenantMail);
        assertEquals(Status.ACTIVE, previousOwner.getStatus());
        assertEquals(Status.INACTIVE, newOwner.getStatus());
    }

    @Transactional
    @Test
    public void divisionShouldBecomeIncorrectAfterRemovingTenant() {
        tenantService.deleteTenant(2L);
        boolean resultedState = settingsService.isDivisionCorrect();
        assertEquals(false, resultedState);
    }

    @Ignore("Please check why this fails")
    @Transactional
    @Test
    public void whyFails() {
        tenantService.deleteTenant(3L);
        boolean resultedState = settingsService.isDivisionCorrect();
        assertEquals(false, resultedState);
    }

    private Tenant createTenant(LocalDate localDate) {
        final Apartment apartment = apartmentService.getById(2L);

        tenantService.loadByMail(tenantMail);
        Tenant tenant = new Tenant();
        tenant.setApartment(apartment);
        tenant.setEmail(dummyMail);
        tenant.setFirstName("dummy");
        tenant.setLastName("dumy");
        tenant.setMovementDate(localDate);
        tenant.setPassword("dummy");
        tenant.setRole(UserRole.TENANT);
        tenant.setStatus(Status.ACTIVE);

        return tenant;
    }
}
