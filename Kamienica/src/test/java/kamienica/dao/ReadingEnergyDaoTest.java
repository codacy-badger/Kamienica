package kamienica.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.InvoiceEnergy;
import kamienica.feature.meter.MeterEnergy;
import kamienica.feature.reading.ReadingDao;
import kamienica.feature.reading.ReadingEnergy;

public class ReadingEnergyDaoTest extends EntityDaoImplTest {

	@Autowired
	ReadingDao<ReadingEnergy, InvoiceEnergy> dao;
	@Autowired
	DaoInterface<MeterEnergy> meterDAO;

	@Test
	public void getUnresolvedReadings() {
		Assert.assertEquals(dao.getUnresolvedReadings().size(), 2);
	}

	@Test
	public void getByDate() {
		ReadingEnergy reading = dao.getById(1);
		List<ReadingEnergy> list = dao.getByDate(reading.getReadingDate().toString());
		Assert.assertEquals(list.size(), 4);
		for (ReadingEnergy readingEnergy : list) {
			Assert.assertEquals(readingEnergy.getValue(), 100.0);
		}

	}

	@Test
	public void getPrevious() {
		List<ReadingEnergy> list = dao.getPrevious("2010-03-01");
		Assert.assertEquals(list.size(), 4);
		for (ReadingEnergy readingEnergy : list) {
			Assert.assertEquals(readingEnergy.getValue(), 120.0);
		}
	}

	@Test
	public void getLatestList() {
		List<ReadingEnergy> list = dao.getLatestList();
		Assert.assertEquals(list.size(), 4);
		for (ReadingEnergy readingEnergy : list) {
			Assert.assertEquals(readingEnergy.getValue(), 145.0);
		}
	}

	@Test
	public void listForTenant() {

		List<ReadingEnergy> list = dao.getListForTenant(getAp());
		double sum = 0.0;
		for (ReadingEnergy readingEnergy : list) {
			sum += readingEnergy.getValue();
		}
		Assert.assertEquals(list.size(), 6);
		Assert.assertEquals(sum, 730.0);

	}

//	@Test
//	public void getLatestMap() {
//		HashMap<Integer, ReadingEnergy> list = dao.getLatestReadingsMap();
//		Assert.assertEquals(list.get(1).getValue(), 145.0);
//
//	}

	@Test
	public void addAndRemove() {
		MeterEnergy test = meterDAO.getById(1);
		ReadingEnergy reading = new ReadingEnergy(new Date(), 300, test);
		dao.save(reading);
		Assert.assertEquals(dao.getList().size(), 13);
		Assert.assertEquals(dao.getById(13).getValue(), 300.0);
		dao.deleteById(13);
		Assert.assertEquals(dao.getList().size(), 12);
	}

	private static Apartment getAp() {
		Apartment ap = new Apartment();
		ap.setId(3);
		ap.setApartmentNumber(2);
		return ap;
	}
}
