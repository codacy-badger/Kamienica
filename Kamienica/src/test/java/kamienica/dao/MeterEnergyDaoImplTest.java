package kamienica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.feature.meter.MeterEnergy;

public class MeterEnergyDaoImplTest extends EntityDaoImplTest {

	@Autowired
	DaoInterface<MeterEnergy> meterDao;

	@Test
	public void findById() {
		Assert.assertNotNull(meterDao.getById(1L));
		Assert.assertNull(meterDao.getById(7L));
	}

	@Test
	public void save() {
		meterDao.save(getSampleMeter());
		Assert.assertEquals(meterDao.getList().size(), 5);
	}

	@Test
	public void deleteById() {
		meterDao.deleteById(5L);
		Assert.assertEquals(meterDao.getList().size(), 4);
	}

	@Test
	public void deletetByInvalidId() {
		meterDao.deleteById(9L);
		Assert.assertEquals(meterDao.getList().size(), 4);
	}

	@Test
	public void findAll() {
		Assert.assertEquals(meterDao.getList().size(), 4);
	}

	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void saveDuplicate() {
		meterDao.save(getDuplcateNubmerApartment());
		Assert.assertEquals(meterDao.getList().size(), 4);
	}

	public MeterEnergy getSampleMeter() {
		MeterEnergy meter = new MeterEnergy();
		meter.setApartment(null);
		meter.setDescription("test");
		meter.setSerialNumber("7676434211");
		meter.setUnit("test");
		return meter;
	}

	public MeterEnergy getDuplcateNubmerApartment() {
		MeterEnergy meter = new MeterEnergy();
		meter.setApartment(null);
		meter.setDescription("test");
		meter.setSerialNumber("1");
		meter.setUnit("test");
		return meter;
	}
}
