package kamienica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.model.Apartment;

public class ApartmentDaolmplTest extends EntityDaoImplTest {

	@Autowired
	DaoInterface<Apartment> apartemtnDao;

	// @BeforeClass
	// @Override
	// protected IDataSet getDataSet() throws Exception {
	// IDataSet dataSet = new
	// FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Apartment.xml"));
	// return dataSet;
	// }

	@Test
	public void findById() {
		Assert.assertNotNull(apartemtnDao.getById(1));
		Assert.assertNull(apartemtnDao.getById(4));
	}

	@Test
	public void saveAndDelete() {
		Assert.assertEquals(apartemtnDao.getList().size(), 3);
		apartemtnDao.save(getSampleApartment());
		Assert.assertEquals(apartemtnDao.getList().size(), 4);
		apartemtnDao.deleteById(4);
		// apartemtnDao.deleteByID(4);
		Assert.assertEquals(apartemtnDao.getList().size(), 3);
	}

	@Test
	public void deleteApartmentByInvalidId() {
		apartemtnDao.deleteById(8);
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
