package kamienica.service;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.dao.EntityDaoImplTest;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantService;
import kamienica.feature.tenant.UserStatus;

public class TenantServiceTest extends EntityDaoImplTest {

	@Autowired
	TenantService service;

	@Autowired
	ApartmentDao apDao;

	/**
	 * 
	 * If new tenant's movement date is prior to current than he should be
	 * deactivated first (data backfill case)
	 * 
	 */
	@Test
	public void olderTenantShouldBeInactivated() {
		Tenant t = getTenant(apDao);
		t.setMovementDate(new LocalDate(1990, 10, 10));
		service.saveTenant(t);
		Assert.assertEquals(service.getTenantById(1L).getStatus(), UserStatus.ACTIVE.getUserStatus());
		Assert.assertNotSame(service.getTenantById(5L), UserStatus.INACTIVE.getUserStatus());

	}

	@Test
	public void newTenantShouldDeactivatePrevious() {
		Tenant t = getTenant(apDao);
		t.setMovementDate(new LocalDate());
		service.saveTenant(t);
		
		System.out.println(service.getTenantById(1L));
		Assert.assertEquals(service.getTenantById(1L).getStatus(), UserStatus.INACTIVE.getUserStatus());
		Assert.assertNotSame(service.getTenantById(5L), UserStatus.ACTIVE.getUserStatus());
	}

	// public void saveTenant(Tenant newTenant) {
	// Tenant currentTenant =
	// tenantDao.getTenantForApartment(newTenant.getApartment());
	// if (currentTenant == null) {
	// tenantDao.save(newTenant);
	// } else if
	// (currentTenant.getMovementDate().isAfter(newTenant.getMovementDate())) {
	// newTenant.setStatus(UserStatus.INACTIVE.getUserStatus());
	// tenantDao.save(newTenant);
	// } else {
	// tenantDao.deactivateByApparmentId(newTenant.getApartment().getId());
	// tenantDao.save(newTenant);
	// }
	//
	// }

	private static Tenant getTenant(ApartmentDao apDao) {
		return new Tenant("Test", "tEST2", "TEST@wp.pl", "23234", apDao.getById(2L));
	}
}
