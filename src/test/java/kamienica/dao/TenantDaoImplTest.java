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
//import kamienica.model.entity.Apartment;
//import kamienica.feature.apartment.IApartmentDao;
//import kamienica.model.entity.Tenant;
//import kamienica.feature.tenant.ITenantDao;
//import kamienica.feature.tenant.Status;
//
//public class TenantDaoImplTest extends EntityDaoImplTest {
//
//	@Autowired
//	ITenantDao daoservice;
//	@Autowired
//	IApartmentDao apDao;
//
//	@Testing(expectedExceptions = javax.validation.ConstraintViolationException.class)
//	public void saveNull() {
//		
//		daoservice.save(new Tenant());
//		Tenant tenant = getSample();
//		// tenant.setEmail("dfg");
//		daoservice.save(tenant);
//
//	}
//
//	@Testing
//	public void loadByMail() {
//		Tenant tenant = daoservice.loadByMail("a@wp");
//		Assert.assertNotNull(tenant);
//		Assert.assertEquals(tenant.getFirstName(), "Maciej");
//
//	}
//
//	@Testing
//	@Rollback
//	public void deactivateTenant() {
//		Tenant tenant = daoservice.getById(1L);
//		
//		Assert.assertEquals(tenant.getStatus(), Status.ACTIVE.getUserStatus());
//
//		daoservice.deactivateByApparmentId(tenant.fetchApartment().getId());
//		Assert.assertEquals(daoservice.getById(1L).getStatus(), Status.INACTIVE.getUserStatus());
//	}
//
//	@Testing
//	public void getIdList() {
//		Set<Long> tested = daoservice.getIdList();
//		Set<Long> expected = new HashSet<>();
//		expected.addAll(Arrays.asList(1L,2L,3L,4L));
//
//		Assert.assertEquals(tested, expected);
//	}
//
//	@Testing
//	public void getLoggedTenant() {
//		Apartment ap = apDao.getById(2L);
//		Tenant ten = daoservice.getTenantForApartment(ap);
//		Assert.assertEquals(ten.getFirstName(), "Maciej");
//	}
//
//	@Rollback
//	@Testing(expectedExceptions = javax.validation.ConstraintViolationException.class)
//	public void saveWithWrongEmail() {
//		Tenant tenant = getSample();
//		tenant.setEmail("dfg");
//		daoservice.save(tenant);
//	}
//
//	@Rollback
//	@Testing
//	public void update() {
//		Tenant ten = daoservice.getById(1L);
//		Assert.assertEquals(ten.getFirstName(), "Maciej");
//		ten.setFirstName("Karol");
//		daoservice.update(ten);
//		Assert.assertEquals(daoservice.getById(1l).getFirstName(), "Karol");
//	}
//
//	@Testing
//	public void findById() {
//		Assert.assertNotNull(daoservice.getById(1L));
//		Assert.assertNull(daoservice.getById(5L));
//	}
//
//	@Rollback
//	@Testing(dependsOnMethods = { "findAll" })
//	public void save() {
//		daoservice.save(getSample());
//		Assert.assertEquals(daoservice.getListForOwner().size(), 5);
//	}
//
//	@Rollback
//	@Testing(dependsOnMethods = { "findAll" })
//	public void delete() {
//		Assert.assertEquals(daoservice.getListForOwner().size(), 4);
//		daoservice.delete(1L);
//		Assert.assertEquals(daoservice.getListForOwner().size(), 3);
//	}
//
//	@Testing(dependsOnMethods = { "findAll" })
//	public void deleteInvalidId() {
//		Assert.assertEquals(daoservice.getListForOwner().size(), 4);
//		daoservice.delete(8L);
//		Assert.assertEquals(daoservice.getListForOwner().size(), 4);
//	}
//
//	@Testing
//	public void findAll() {
//		List<Tenant> list = daoservice.getListForOwner();
//		System.out.println(list);
//		Assert.assertEquals(list.size(), 4);
//	}
//
//	@Testing(dependsOnMethods = { "findAll" })
//	public void getActiveList() {
//		Assert.assertEquals(daoservice.getActiveTenants().size(), 2);
//	}
//
//	@Testing(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
//	public void saveDuplicateEmail() {
//		daoservice.save(getDuplcate());
//		Assert.assertEquals(daoservice.getListForOwner().size(), 4);
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
