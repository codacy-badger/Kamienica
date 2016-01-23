package kamienica.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.model.Apartment;

public class ApartmentDaolmplTest extends EntityDaoImplTest {

	@Autowired
	ApartmentDao apartemtnDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().
				getResourceAsStream("Apartment.xml"));
		return dataSet;
	}

	@Test
	public void findById() {
		Assert.assertNotNull(apartemtnDao.getById(1));
		Assert.assertNull(apartemtnDao.getById(4));
	}

	@Test
	public void saveApartment() {
		apartemtnDao.save(getSampleApartment());
		Assert.assertEquals(apartemtnDao.getList().size(), 4);
	}

	@Test
	public void deleteApartmentById() {
		apartemtnDao.deleteByID(1);
		Assert.assertEquals(apartemtnDao.getList().size(), 2);
	}

	@Test
	public void deleteApartmentByInvalidId() {
		apartemtnDao.deleteByID(8);
		Assert.assertEquals(apartemtnDao.getList().size(), 3);
	}

	@Test
	public void findAllApartments() {
		Assert.assertEquals(apartemtnDao.getList().size(), 3);
	}

	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void saveDuplicateAppNuber() {
		apartemtnDao.save(getDuplcateNubmerApartment());
		Assert.assertEquals(apartemtnDao.getList().size(), 4);
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
