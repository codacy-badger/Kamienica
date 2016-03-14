package kamienica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.dao.invoice.InvoiceWaterDAO;
import kamienica.model.MeterWater;

public class InvoiceWaterDaoImplTest extends EntityDaoImplTest {

	
	@Autowired
	InvoiceWaterDAO invDao;

	@Test
	public void findById() {
		Assert.assertNotNull(invDao.getById(1));
		Assert.assertNull(invDao.getById(2));
	}

	// @Test
	// public void save() {
	// invDao.save(getSampleMeter());
	// Assert.assertEquals(invDao.getList().size(), 5);
	// }

	// @Test
	// public void deleteById() {
	// invDao.deleteWaterByID(5);
	// Assert.assertEquals(invDao.getList().size(), 4);
	// }

	// @Test
	// public void deletetByInvalidId() {
	// invDao.deleteWaterByID(9);
	// Assert.assertEquals(invDao.getList().size(), 4);
	// }

	@Test
	public void findAll() {
		Assert.assertEquals(invDao.getList().size(), 1);
	}

	// @Test(expectedExceptions =
	// org.hibernate.exception.ConstraintViolationException.class)
	// public void saveDuplicate() {
	// invDao.save(getDuplcateNubmerApartment());
	// Assert.assertEquals(invDao.getList().size(), 4);
	// }

	public MeterWater getSampleMeter() {
		MeterWater meter = new MeterWater();
		meter.setApartment(null);
		meter.setDescription("test");
		meter.setSerialNumber("7676434211");
		meter.setUnit("test");
		return meter;
	}

	// private InvoiceWater getSampleInvoice() {
	// InvoiceWater invoice = new InvoiceWater("fsdfsd", description, date,
	// totalAmount, reading)
	// return null;
	// }

	public MeterWater getDuplcateNubmerApartment() {
		MeterWater meter = new MeterWater();
		meter.setApartment(null);
		meter.setDescription("test");
		meter.setSerialNumber("1");
		meter.setUnit("test");
		return meter;
	}
}
