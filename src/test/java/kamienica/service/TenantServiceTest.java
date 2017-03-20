package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.RentContract;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Status;
import kamienica.model.enums.UserRole;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
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
        final Tenant newOwner = createTenant(LocalDate.parse("2018-01-01"));
        tenantService.save(newOwner);
        Tenant previousOwner = tenantService.loadByMail(FIRST_OWNER_MAIL);
        assertEquals(false, previousOwner.isActive());
    }

    @Transactional
    @Test
    public void shouldDeactivateNewTenantWhenMovementDateIsOlderThanCurrentTenant() {
        final Tenant newOwner = createTenant(LocalDate.parse("2015-01-01"));
        assertEquals(true, newOwner.isActive());
        tenantService.save(newOwner);
        Tenant previousOwner = tenantService.loadByMail(FIRST_OWNER_MAIL);
        assertEquals(true, previousOwner.isActive());
        assertEquals(false, newOwner.isActive());
    }


    private Tenant createTenant(final LocalDate localDate) {
        final Apartment apartment = apartmentService.getById(2L);
        tenantService.loadByMail(FIRST_OWNER_MAIL);
        Tenant tenant = new Tenant();
        final RentContract rc = new RentContract(apartment, tenant, 100, localDate);

        tenant.setEmail(dummyMail);
        tenant.setFirstName("dummy");
        tenant.setLastName("dumy");
        tenant.setPassword("dummy");
        tenant.setRole(UserRole.TENANT);
        tenant.setRentContract(rc);
        return tenant;
    }
}
