package kamienica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentDao;

public class ApartmentDaolmplTest extends EntityDaoImplTest {

	@Autowired
	ApartmentDao apartemtnDao;

	// @BeforeClass
	// @Override
	// protected IDataSet getDataSet() throws Exception {
	// IDataSet dataSet = new
	// FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Apartment.xml"));
	// return dataSet;
	// }

	@Test
	public void findById() {
		Assert.assertNotNull(apartemtnDao.getById(1L));
		Assert.assertNull(apartemtnDao.getById(4L));
	}

	@Test
	public void saveAndDelete() {
		Assert.assertEquals(apartemtnDao.getList().size(), 3);
		apartemtnDao.save(getSampleApartment());
		Assert.assertEquals(apartemtnDao.getList().size(), 4);
		apartemtnDao.deleteById(4L);
		// apartemtnDao.deleteByID(4);
		Assert.assertEquals(apartemtnDao.getList().size(), 3);
	}

	@Test
	public void deleteApartmentByInvalidId() {
		apartemtnDao.deleteById(8L);
		Assert.assertEquals(apartemtnDao.getList().size(), 3);
	}

	@Test
	public void findAllApartments() {
		Assert.assertEquals(apartemtnDao.getList().size(), 3);
	}

	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void saveDuplicateAppNuber() {
		apartemtnDao.save(getDuplcateNubmerApartment());
		Assert.assertEquals(apartemtnDao.getList().size(), 3);
	}

	public Apartment getSampleApartment() {
		Apartment ap = new Apartment();
		ap.setApartmentNumber(4);
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
