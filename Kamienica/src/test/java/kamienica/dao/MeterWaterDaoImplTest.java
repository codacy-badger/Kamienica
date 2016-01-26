package kamienica.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.model.MeterWater;

public class MeterWaterDaoImplTest extends EntityDaoImplTest{

	
	@Autowired
	MeterWaterDAO meterDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().
				getResourceAsStream("MeterWater.xml"));
		return dataSet;
	}

	@Test
	public void findById() {
		Assert.assertNotNull(meterDao.getById(1));
		Assert.assertNull(meterDao.getById(4));
	}

	@Test
	public void save() {
		meterDao.save(getSampleMeter());
		Assert.assertEquals(meterDao.getList().size(), 4);
	}

	@Test
	public void deleteById() {
		meterDao.deleteWaterByID(1);
		Assert.assertEquals(meterDao.getList().size(), 2);
	}

	@Test
	public void deletetByInvalidId() {
		meterDao.deleteWaterByID(9);
		Assert.assertEquals(meterDao.getList().size(), 3);
	}

	@Test
	public void findAll() {
		Assert.assertEquals(meterDao.getList().size(), 3);
	}

	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void saveDuplicate() {
		meterDao.save(getDuplcateNubmerApartment());
		Assert.assertEquals(meterDao.getList().size(), 4);
	}

	public MeterWater getSampleMeter() {
		MeterWater meter = new MeterWater();
		meter.setApartment(null);
		meter.setDescription("test");
		meter.setSerialNumber("7676");
		meter.setUnit("test");
		return meter;
	}

	public MeterWater getDuplcateNubmerApartment() {
		MeterWater meter = new MeterWater();
		meter.setApartment(null);
		meter.setDescription("test");
		meter.setSerialNumber("1");
		meter.setUnit("test");
		return meter;
	}
}
