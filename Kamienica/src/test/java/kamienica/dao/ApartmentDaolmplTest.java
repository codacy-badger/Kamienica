//package kamienica.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import kamienica.feature.apartment.Apartment;
//import kamienica.feature.apartment.ApartmentDao;
//
//public class ApartmentDaolmplTest extends EntityDaoImplTest {
//
//	@Autowired
//	ApartmentDao apartemtnDao;
//
//	@Test
//	public void findById() {
//		Apartment ap = apartemtnDao.getById(1L);
//		Assert.assertNotNull(ap);
//		Assert.assertEquals(ap.getDescription(), "Część Wspólna");
//		Assert.assertNull(apartemtnDao.getById(8L));
//	}
//
//	@Test(dependsOnMethods = { "findAllApartments" })
//	@Rollback
//	public void save() {
//		Assert.assertEquals(apartemtnDao.getList().size(), 5);
//		apartemtnDao.save(getSampleApartment());
//		Assert.assertEquals(apartemtnDao.getList().size(), 6);
//	}
//
//	@Test(dependsOnMethods = { "findAllApartments" })
//	public void deleteApartmentByInvalidId() {
//		apartemtnDao.deleteById(85L);
//		Assert.assertEquals(apartemtnDao.getList().size(), 5);
//	}
//
//	@Test
//	@Rollback
//	public void deleteApartment() {
//		Assert.assertEquals(apartemtnDao.getList().size(), 5);
//		apartemtnDao.deleteById(5L);
//		Assert.assertEquals(apartemtnDao.getList().size(), 4);
//	}
//
//	@Test
//	public void findAllApartments() {
//		Assert.assertEquals(apartemtnDao.getList().size(), 5);
//	}
//
//	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
//	public void saveDuplicateAppNuber() {
//		apartemtnDao.save(getDuplcateNubmerApartment());
//		Assert.assertEquals(apartemtnDao.getList().size(), 3);
//	}
//
//	@Rollback
//	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
//	public void deleteApartmentWhereThereIsMeterOrTenant() {
//		apartemtnDao.deleteById(2L);
//	}
//
//	@Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
//	public void saveApartmentWithWrongData() {
//		Apartment ap = new Apartment();
//		ap.setIntercom("234a");
//		ap.setApartmentNumber(43);
//		ap.setDescription("test");
//		apartemtnDao.save(ap);
//	}
//
//	public Apartment getSampleApartment() {
//		Apartment ap = new Apartment();
//		ap.setApartmentNumber(88);
//		ap.setDescription("test");
//		ap.setIntercom("0000");
//		return ap;
//	}
//
//	public Apartment getDuplcateNubmerApartment() {
//		Apartment ap = new Apartment();
//		ap.setApartmentNumber(1);
//		ap.setDescription("test");
//		ap.setIntercom("0000");
//		return ap;
//	}
//}
