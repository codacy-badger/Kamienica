package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.enums.Status;
import kamienica.model.enums.UserRole;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Tenant;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TenantServiceTest extends ServiceTest {

    private final static String dummyMail = "dummy@dummy";


    @Test
    public void getList() {
        List<Tenant> list = tenantService.list();
        assertEquals(7, list.size());
    }

    @Test
    public void shouldGetOnlyAciveTenants() {
        final Criterion active = Restrictions.eq("status", Status.ACTIVE);
        final Criterion tenant = Restrictions.eq("role", UserRole.TENANT);

        final List<Tenant> result = tenantService.findByCriteria(active, tenant);
        assertEquals(3, result.size());
    }

    @Test
    public void getActiveTenants() {
        List<Tenant> list = tenantService.getActiveTenants();
        assertEquals(5, list.size());
    }

    @Test
    public void getOwners() {
        List<Tenant> list = tenantService.getOwners();
        assertEquals(2, list.size());
        final long id = list.get(0).getId();
        assertEquals(1L, id );
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
        final Tenant newOwner = createTenant(LocalDate.parse("2018-01-01"));
        tenantService.save(newOwner);
        Tenant previousOwner = tenantService.loadByMail(FIRST_OWNER_MAIL);
        assertEquals(Status.INACTIVE, previousOwner.getStatus());
    }

//    @Ignore
//    @Test
//    public void ownerShouldHaveOneResidence() {
//        final Tenant owner = tenantService.getById(1L);
//      //  final List<Residence> residenceList = owner.getResidencesOwned();
////        assertEquals(1, residenceList.size());
//    }//


    @Transactional
    @Test
    public void shouldDeactivateNewTenantWhenMovementDateIsOlderThanCurrentTenant() {
        final Tenant newOwner = createTenant(LocalDate.parse("2015-01-01"));
        assertEquals(Status.ACTIVE, newOwner.getStatus());
        tenantService.save(newOwner);
        Tenant previousOwner = tenantService.loadByMail(FIRST_OWNER_MAIL);
        assertEquals(Status.ACTIVE, previousOwner.getStatus());
        assertEquals(Status.INACTIVE, newOwner.getStatus());
    }


    private Tenant createTenant(LocalDate localDate) {
        final Apartment apartment = apartmentService.getById(2L);

        tenantService.loadByMail(FIRST_OWNER_MAIL);
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
