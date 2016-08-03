package kamienica.dao;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentDao;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApartmentDaolmplTestJUNIT extends EntityDaoImplTestJUNIT {

	@Autowired
	ApartmentDao apartemtnDao;

	@Test
	public void findById() {
		Apartment ap = apartemtnDao.getById(1L);
		Assert.assertNotNull(ap);
		Assert.assertEquals(ap.getDescription(), "Część Wspólna");
		Assert.assertNull(apartemtnDao.getById(8L));
	}

	@Test
	@Rollback
	public void save() {
		Assert.assertEquals(5, apartemtnDao.getList().size() );
		apartemtnDao.save(getSampleApartment());
		Assert.assertEquals(6, apartemtnDao.getList().size());
	}

	@Test
	public void deleteApartmentByInvalidId() {
		apartemtnDao.deleteById(85L);
		Assert.assertEquals(apartemtnDao.getList().size(), 5);
	}

	@Test
	@Rollback
	public void deleteApartment() {
		Assert.assertEquals(apartemtnDao.getList().size(), 5);
		apartemtnDao.deleteById(5L);
		Assert.assertEquals(apartemtnDao.getList().size(), 4);
	}

	@Test
	public void findAllApartments() {
		Assert.assertEquals(apartemtnDao.getList().size(), 5);
	}

	@Test(expected= ConstraintViolationException.class)
	public void saveDuplicateAppNuber() {
		apartemtnDao.save(getDuplcateNubmerApartment());
		Assert.assertEquals(apartemtnDao.getList().size(), 3);
	}

	@Transactional
	@Test(expected= ConstraintViolationException.class)
	public void deleteApartmentWhereThereIsMeterOrTenant() {
		apartemtnDao.deleteById(2L);
	}

	@Test(expected= ConstraintViolationException.class)
	public void saveApartmentWithWrongData() {
		Apartment ap = new Apartment();
		ap.setIntercom("234a");
		ap.setApartmentNumber(43);
		ap.setDescription("test");
		apartemtnDao.save(ap);
	}

	public Apartment getSampleApartment() {
		Apartment ap = new Apartment();
		ap.setApartmentNumber(88);
		ap.setDescription("test");
		ap.setIntercom("0000");
		return ap;
	}

	public Apartment getDuplcateNubmerApartment() {
		Apartment ap = new Apartment();
		ap.setApartmentNumber(1);
		ap.setDescription("test");
		ap.setIntercom("0000");
		return ap;
	}
}
