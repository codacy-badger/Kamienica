//package kamienica.dao;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.joda.time.LocalDate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.testng.Assert;
//import org.testng.annotations.Testing;
//
//import kamienica.model.Apartment;
//import kamienica.feature.apartment.ApartmentDao;
//import kamienica.model.Tenant;
//import kamienica.feature.tenant.TenantDao;
//import kamienica.feature.tenant.Status;
//
//public class TenantDaoImplTest extends EntityDaoImplTest {
//
//	@Autowired
//	TenantDao dao;
//	@Autowired
//	ApartmentDao apDao;
//
//	@Testing(expectedExceptions = javax.validation.ConstraintViolationException.class)
//	public void saveNull() {
//		
//		dao.save(new Tenant());
//		Tenant tenant = getSample();
//		// tenant.setEmail("dfg");
//		dao.save(tenant);
//
//	}
//
//	@Testing
//	public void loadByMail() {
//		Tenant tenant = dao.loadByMail("a@wp");
//		Assert.assertNotNull(tenant);
//		Assert.assertEquals(tenant.getFirstName(), "Maciej");
//
//	}
//
//	@Testing
//	@Rollback
//	public void deactivateTenant() {
//		Tenant tenant = dao.getById(1L);
//		
//		Assert.assertEquals(tenant.getStatus(), Status.ACTIVE.getUserStatus());
//
//		dao.deactivateByApparmentId(tenant.getApartment().getId());
//		Assert.assertEquals(dao.getById(1L).getStatus(), Status.INACTIVE.getUserStatus());
//	}
//
//	@Testing
//	public void getIdList() {
//		Set<Long> tested = dao.getIdList();
//		Set<Long> expected = new HashSet<>();
//		expected.addAll(Arrays.asList(1L,2L,3L,4L));
//
//		Assert.assertEquals(tested, expected);
//	}
//
//	@Testing
//	public void getLoggedTenant() {
//		Apartment ap = apDao.getById(2L);
//		Tenant ten = dao.getTenantForApartment(ap);
//		Assert.assertEquals(ten.getFirstName(), "Maciej");
//	}
//
//	@Rollback
//	@Testing(expectedExceptions = javax.validation.ConstraintViolationException.class)
//	public void saveWithWrongEmail() {
//		Tenant tenant = getSample();
//		tenant.setEmail("dfg");
//		dao.save(tenant);
//	}
//
//	@Rollback
//	@Testing
//	public void updateTenant() {
//		Tenant ten = dao.getById(1L);
//		Assert.assertEquals(ten.getFirstName(), "Maciej");
//		ten.setFirstName("Karol");
//		dao.update(ten);
//		Assert.assertEquals(dao.getById(1l).getFirstName(), "Karol");
//	}
//
//	@Testing
//	public void findById() {
//		Assert.assertNotNull(dao.getById(1L));
//		Assert.assertNull(dao.getById(5L));
//	}
//
//	@Rollback
//	@Testing(dependsOnMethods = { "findAll" })
//	public void saveTenant() {
//		dao.save(getSample());
//		Assert.assertEquals(dao.getListForOwner().size(), 5);
//	}
//
//	@Rollback
//	@Testing(dependsOnMethods = { "findAll" })
//	public void deleteById() {
//		Assert.assertEquals(dao.getListForOwner().size(), 4);
//		dao.deleteById(1L);
//		Assert.assertEquals(dao.getListForOwner().size(), 3);
//	}
//
//	@Testing(dependsOnMethods = { "findAll" })
//	public void deleteInvalidId() {
//		Assert.assertEquals(dao.getListForOwner().size(), 4);
//		dao.deleteById(8L);
//		Assert.assertEquals(dao.getListForOwner().size(), 4);
//	}
//
//	@Testing
//	public void findAll() {
//		List<Tenant> list = dao.getListForOwner();
//		System.out.println(list);
//		Assert.assertEquals(list.size(), 4);
//	}
//
//	@Testing(dependsOnMethods = { "findAll" })
//	public void getActiveList() {
//		Assert.assertEquals(dao.getActiveTenants().size(), 2);
//	}
//
//	@Testing(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
//	public void saveDuplicateEmail() {
//		dao.save(getDuplcate());
//		Assert.assertEquals(dao.getListForOwner().size(), 4);
//	}
//
//	public Tenant getSample() {
//		Tenant ap = new Tenant();
//		ap.setFirstName("erstse");
//		ap.setLastName("dfgdfg");
//		ap.setEmail("gdf@wp.pl");
//		ap.setFirstName("ab");
//		ap.setApartment(null);
//		ap.setMovementDate(new LocalDate());
//		ap.setPhone("345");
//		ap.setRole("OWNER");
//		ap.setStatus("ACTIVE");
//		ap.setPassword("witaj");
//		return ap;
//	}
//
//	public Tenant getDuplcate() {
//		Tenant ap = new Tenant();
//		ap.setFirstName("erstse");
//		ap.setLastName("dfgdfg");
//		ap.setEmail("c@wp.pl");
//		ap.setFirstName("ab");
//		ap.setApartment(null);
//		ap.setMovementDate(new LocalDate());
//		ap.setPhone("345");
//		ap.setRole("OWNER");
//		ap.setStatus("ACTIVE");
//		ap.setPassword("witaj");
//		return ap;
//	}
//
//}
