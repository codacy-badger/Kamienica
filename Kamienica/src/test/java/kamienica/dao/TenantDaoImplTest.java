package kamienica.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantDao;
import kamienica.feature.tenant.UserStatus;

public class TenantDaoImplTest extends EntityDaoImplTest {

	@Autowired
	TenantDao dao;
	@Autowired
	ApartmentDao apDao;

	@Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
	public void saveNull() {
		
		dao.save(new Tenant());
		Tenant tenant = getSample();
		// tenant.setEmail("dfg");
		dao.save(tenant);

	}

	@Test
	public void loadByMail() {
		Tenant tenant = dao.loadByMail("a@wp");
		Assert.assertNotNull(tenant);
		Assert.assertEquals(tenant.getFirstName(), "Maciej");

	}

	@Test
	@Rollback
	public void deactivateTenant() {
		Tenant tenant = dao.getById(1L);
		
		Assert.assertEquals(tenant.getStatus(), UserStatus.ACTIVE.getUserStatus());

		dao.deactivateByApparmentId(tenant.getApartment().getId());
		Assert.assertEquals(dao.getById(1L).getStatus(), UserStatus.INACTIVE.getUserStatus());
	}

	@Test
	public void getIdList() {
		Set<Long> tested = dao.getIdList();
		Set<Long> expected = new HashSet<>();
		expected.addAll(Arrays.asList(1L,2L,3L,4L));

		Assert.assertEquals(tested, expected);
	}

	@Test
	public void getCurrentTenant() {
		Apartment ap = apDao.getById(2L);
		Tenant ten = dao.getTenantForApartment(ap);
		Assert.assertEquals(ten.getFirstName(), "Maciej");
	}

	@Rollback
	@Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
	public void saveWithWrongEmail() {
		Tenant tenant = getSample();
		tenant.setEmail("dfg");
		dao.save(tenant);
	}

	@Rollback
	@Test
	public void updateTenant() {
		Tenant ten = dao.getById(1L);
		Assert.assertEquals(ten.getFirstName(), "Maciej");
		ten.setFirstName("Karol");
		dao.update(ten);
		Assert.assertEquals(dao.getById(1l).getFirstName(), "Karol");
	}

	@Test
	public void findById() {
		Assert.assertNotNull(dao.getById(1L));
		Assert.assertNull(dao.getById(5L));
	}

	@Rollback
	@Test(dependsOnMethods = { "findAll" })
	public void saveTenant() {
		dao.save(getSample());
		Assert.assertEquals(dao.getList().size(), 5);
	}

	@Rollback
	@Test(dependsOnMethods = { "findAll" })
	public void deleteById() {
		Assert.assertEquals(dao.getList().size(), 4);
		dao.deleteById(1L);
		Assert.assertEquals(dao.getList().size(), 3);
	}

	@Test(dependsOnMethods = { "findAll" })
	public void deleteInvalidId() {
		Assert.assertEquals(dao.getList().size(), 4);
		dao.deleteById(8L);
		Assert.assertEquals(dao.getList().size(), 4);
	}

	@Test
	public void findAll() {
		List<Tenant> list = dao.getList();
		System.out.println(list);
		Assert.assertEquals(list.size(), 4);
	}

	@Test(dependsOnMethods = { "findAll" })
	public void getActiveList() {
		Assert.assertEquals(dao.getActiveTenants().size(), 2);
	}

	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void saveDuplicateEmail() {
		dao.save(getDuplcate());
		Assert.assertEquals(dao.getList().size(), 4);
	}

	public Tenant getSample() {
		Tenant ap = new Tenant();
		ap.setFirstName("erstse");
		ap.setLastName("dfgdfg");
		ap.setEmail("gdf@wp.pl");
		ap.setFirstName("ab");
		ap.setApartment(null);
		ap.setMovementDate(new LocalDate());
		ap.setPhone("345");
		ap.setRole("ADMIN");
		ap.setStatus("ACTIVE");
		ap.setPassword("witaj");
		return ap;
	}

	public Tenant getDuplcate() {
		Tenant ap = new Tenant();
		ap.setFirstName("erstse");
		ap.setLastName("dfgdfg");
		ap.setEmail("c@wp.pl");
		ap.setFirstName("ab");
		ap.setApartment(null);
		ap.setMovementDate(new LocalDate());
		ap.setPhone("345");
		ap.setRole("ADMIN");
		ap.setStatus("ACTIVE");
		ap.setPassword("witaj");
		return ap;
	}

}
