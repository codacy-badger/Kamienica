package kamienica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.feature.meter.MeterGas;

public class MeterGasDaoImplTest extends EntityDaoImplTest {

	@Autowired
	DaoInterface<MeterGas> meterDao;
	//
	// @Override
	// protected IDataSet getDataSet() throws Exception {
	// IDataSet[] datasets = new IDataSet[] {
	// new
	// FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Apartment.xml")),
	// new
	// FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("MeterGas.xml"))
	// };
	// return new CompositeDataSet(datasets);
	// }

	@Test
	public void findById() {
		Assert.assertNotNull(meterDao.getById(1L));
		Assert.assertNotNull(meterDao.getById(3L));
		Assert.assertNotNull(meterDao.getById(5L));
		Assert.assertNull(meterDao.getById(6L));

	}

	@Test
	public void save() {
		meterDao.save(getSampleMeter());
		Assert.assertEquals(meterDao.getList().size(), 6);
	}

	@Test
	public void deleteById() {
		meterDao.deleteById(1L);
		Assert.assertEquals(meterDao.getList().size(), 4);
	}

	@Test
	public void deletetByInvalidId() {
		meterDao.deleteById(9L);
		Assert.assertEquals(meterDao.getList().size(), 5);
	}

	@Test
	public void findAll() {
	
		Assert.assertEquals(meterDao.getList().size(), 5);
	}

	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void saveDuplicate() {
		meterDao.save(getDuplcateNubmerApartment());
		Assert.assertEquals(meterDao.getList().size(), 5);

	}

	public MeterGas getSampleMeter() {
		MeterGas meter = new MeterGas();
		meter.setApartment(null);
		meter.setDescription("test");
		meter.setSerialNumber("7676434211");
		meter.setUnit("test");
		return meter;
	}

	public MeterGas getDuplcateNubmerApartment() {
		MeterGas meter = new MeterGas();
		meter.setApartment(null);
		meter.setDescription("test");
		meter.setSerialNumber("1");
		meter.setUnit("test");
		return meter;
	}
}
