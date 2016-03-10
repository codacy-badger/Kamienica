package kamienica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.model.MeterWater;

public class MeterWaterDaoImplTest extends EntityDaoImplTest {

	@Autowired
	MeterWaterDAO meterDao;

	

	@Test
	public void findById() {
		Assert.assertNotNull(meterDao.getById(1));
		Assert.assertNull(meterDao.getById(8));
	}

	@Test
	public void saveAndDelete() {
		meterDao.save(getSampleMeter());
		Assert.assertEquals(meterDao.getList().size(), 6);
		meterDao.deleteWaterByID(6);
		Assert.assertEquals(meterDao.getList().size(), 5);
	}

	@Test
	public void deletetByInvalidId() {
		meterDao.deleteWaterByID(9);
		Assert.assertEquals(meterDao.getList().size(), 5);
	}

	@Test
	public void findAll() {
		Assert.assertEquals(meterDao.getList().size(), 5);
	}

	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void saveDuplicate() {
		meterDao.save(getDuplcateNubmerApartment());
		// Assert.assertEquals(meterDao.getList().size(), 4);
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
