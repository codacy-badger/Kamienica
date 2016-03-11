package kamienica.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.model.Apartment;
import kamienica.model.MeterGas;
import kamienica.model.ReadingGas;

public class ReadingGasDaoTest extends EntityDaoImplTest {

	@Autowired
	ReadingGasDAO dao;
	@Autowired
	MeterGasDAO meterDAO;

	@Test
	public void getUnresolvedReadings() {
		Assert.assertEquals(dao.getUnresolvedReadings().size(), 2);
	}

	@Test
	public void getByDate() {
		ReadingGas reading = dao.getById(1);
		List<ReadingGas> list = dao.getByDate(reading.getReadingDate().toString());
		Assert.assertEquals(list.size(), 5);
		for (ReadingGas readingGas : list) {
			Assert.assertEquals(readingGas.getValue(), 100.0);
		}

	}

	@Test
	public void getPrevious() {
		List<ReadingGas> list = dao.getPrevious("2010-03-01");
		Assert.assertEquals(list.size(), 5);
		for (ReadingGas readingGas : list) {
			Assert.assertEquals(readingGas.getValue(), 120.0);
		}
	}

	@Test
	public void getLatestList() {
		List<ReadingGas> list = dao.getLatestList();
		Assert.assertEquals(list.size(), 5);
		for (ReadingGas readingGas : list) {
			Assert.assertEquals(readingGas.getValue(), 145.0);
		}
	}

	@Test
	public void listForTenant() {

		List<ReadingGas> list = dao.getListForTenant(getAp());
		double sum = 0.0;
		for (ReadingGas readingGas : list) {
			sum += readingGas.getValue();
		}
		Assert.assertEquals(list.size(), 6);
		Assert.assertEquals(sum, 730.0);

	}

	@Test
	public void getLatestMap() {
		HashMap<Integer, ReadingGas> list = dao.getLatestReadingsMap();
		Assert.assertEquals(list.get(1).getValue(), 145.0);

	}

	@Test
	public void addAndRemove() {
		MeterGas test = meterDAO.getById(1);
		ReadingGas reading = new ReadingGas(new Date(), 300, test);
		dao.save(reading);
		Assert.assertEquals(dao.getList().size(), 16);
		Assert.assertEquals(dao.getById(16).getValue(), 300.0);
		dao.deleteById(13);
		Assert.assertEquals(dao.getList().size(), 15);
	}

	private static Apartment getAp() {
		Apartment ap = new Apartment();
		ap.setId(3);
		ap.setApartmentNumber(2);
		return ap;
	}
}
