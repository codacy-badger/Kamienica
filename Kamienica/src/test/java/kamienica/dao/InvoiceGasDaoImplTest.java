package kamienica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.feature.invoice.InvoiceDao;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.meter.MeterGas;

public class InvoiceGasDaoImplTest extends EntityDaoImplTest {


	@Autowired 
	@Qualifier("invoiceGas")
	InvoiceDao<InvoiceGas> invDao;

	@Test
	public void findById() {
		Assert.assertNotNull(invDao.getById(1L));
		Assert.assertNull(invDao.getById(2L));
	}

	// @Test
	// public void save() {
	// invDao.save(getSampleMeter());
	// Assert.assertEquals(invDao.getList().size(), 5);
	// }

	// @Test
	// public void deleteById() {
	// invDao.deleteGasByID(5);
	// Assert.assertEquals(invDao.getList().size(), 4);
	// }

	// @Test
	// public void deletetByInvalidId() {
	// invDao.deleteGasByID(9);
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

	public MeterGas getSampleMeter() {
		MeterGas meter = new MeterGas();
		meter.setApartment(null);
		meter.setDescription("test");
		meter.setSerialNumber("7676434211");
		meter.setUnit("test");
		return meter;
	}

	// private InvoiceGas getSampleInvoice() {
	// InvoiceGas invoice = new InvoiceGas("fsdfsd", description, date,
	// totalAmount, reading)
	// return null;
	// }

	public MeterGas getDuplcateNubmerApartment() {
		MeterGas meter = new MeterGas();
		meter.setApartment(null);
		meter.setDescription("test");
		meter.setSerialNumber("1");
		meter.setUnit("test");
		return meter;
	}
}
