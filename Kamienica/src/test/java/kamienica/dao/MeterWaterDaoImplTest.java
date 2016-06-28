package kamienica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.feature.meter.MeterDao;
import kamienica.feature.meter.MeterWater;

public class MeterWaterDaoImplTest extends EntityDaoImplTest {

	@Autowired
	MeterDao<MeterWater> meterDao;

	

	@Test
	public void findById() {
		Assert.assertNotNull(meterDao.getById(1L));
		Assert.assertNull(meterDao.getById(8L));
	}

	@Test
	public void saveAndDelete() {
		meterDao.save(getSampleMeter());
		Assert.assertEquals(meterDao.getList().size(), 6);
		meterDao.deleteById(6L);
		Assert.assertEquals(meterDao.getList().size(), 5);
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
