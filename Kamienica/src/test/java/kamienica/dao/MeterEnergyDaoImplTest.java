package kamienica.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.feature.meter.MeterDao;
import kamienica.feature.meter.MeterEnergy;

public class MeterEnergyDaoImplTest extends EntityDaoImplTest {

	@Autowired
	MeterDao<MeterEnergy> meterDao;

	@Test
	public void findById() {
		Assert.assertNotNull(meterDao.getById(1L));
		Assert.assertNull(meterDao.getById(7L));

	}

	@Test
	public void ifMainExists() {
		Assert.assertTrue(meterDao.ifMainExists());
	}

	@Test
	public void checkIdList() {
		Set<Long> expected = new HashSet<>();
		expected.addAll(Arrays.asList(1L, 2L, 3L, 4L, 5L));
		Set<Long> tested = meterDao.getIdList();

		Assert.assertEquals(expected, tested);
	}

	@Rollback
	@Test(dependsOnMethods = { "findAll" })
	public void save() {
		meterDao.save(getSampleMeter());
		Assert.assertEquals(meterDao.getList().size(), 6);
	}

	@Rollback
	@Test(dependsOnMethods = { "findAll" }, expectedExceptions= org.hibernate.exception.ConstraintViolationException.class)
	public void deleteWhereThereAreReadingsForIt() {
		meterDao.deleteById(5L);
		Assert.assertEquals(meterDao.getList().size(), 4);
	}

	@Test(dependsOnMethods = { "findAll" })
	public void deletetByInvalidId() {
		meterDao.deleteById(9L);
		Assert.assertEquals(meterDao.getList().size(), 5);
	}

	@Test
	public void findAll() {
		List<MeterEnergy> list = meterDao.getList();
		System.out.println(list);
		Assert.assertEquals(list.size(), 5);
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
