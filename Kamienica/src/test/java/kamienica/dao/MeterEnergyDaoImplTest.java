package kamienica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.model.MeterEnergy;

public class MeterEnergyDaoImplTest extends EntityDaoImplTest  {

	@Autowired
	MeterEnergyDAO meterDao;

	// @Override
	// protected IDataSet getDataSet() throws Exception {
	// IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().
	// getResourceAsStream("MeterEnergy.xml"));
	// return dataSet;
	// }

//	@Override
//	protected IDataSet getDataSet() throws Exception {
//		IDataSet[] datasets = new IDataSet[] {
//				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Apartment.xml")),
//				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("MeterEnergy.xml")) };
//		return new CompositeDataSet(datasets);
//	}

	@Test
	public void findById() {
	
		Assert.assertNotNull(meterDao.getById(1));
		Assert.assertNull(meterDao.getById(5));
	}

	@Test
	public void save() {
		meterDao.save(getSampleMeter());
		Assert.assertEquals(meterDao.getList().size(), 5);
	}

	@Test
	public void deleteById() {
		meterDao.deleteEnergyByID(1);
		Assert.assertEquals(meterDao.getList().size(), 3);
	}

	@Test
	public void deletetByInvalidId() {
		meterDao.deleteEnergyByID(9);
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
