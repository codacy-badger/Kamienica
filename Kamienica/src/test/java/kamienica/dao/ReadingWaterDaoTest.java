package kamienica.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.apartment.Apartment;
import kamienica.model.MeterWater;
import kamienica.model.ReadingWater;

public class ReadingWaterDaoTest extends EntityDaoImplTest {

	@Autowired
	ReadingWaterDAO dao;
	@Autowired
	DaoInterface<MeterWater> meterDAO;

	@Test
	public void getUnresolvedReadings() {
		List<ReadingWater> list = dao.getList();
		for (ReadingWater readingWater : list) {
			System.out.println(readingWater);
		}

		List<MeterWater> meterList = meterDAO.getList();
		for (MeterWater meterWater : meterList) {
			System.out.println(meterWater);
		}
		Assert.assertEquals(dao.getUnresolvedReadings().size(), 2);
	}

	@Test
	public void getByDate() {
		ReadingWater reading = dao.getById(1);
		List<ReadingWater> list = dao.getByDate(reading.getReadingDate().toString());
		Assert.assertEquals(list.size(), 5);
		for (ReadingWater readingWater : list) {
			Assert.assertEquals(readingWater.getValue(), 100.0);
		}

	}

	@Test
	public void getPrevious() {
		List<ReadingWater> list = dao.getPrevious("2010-03-01");
		Assert.assertEquals(list.size(), 5);
		for (ReadingWater readingWater : list) {
			Assert.assertEquals(readingWater.getValue(), 120.0);
		}
	}

	@Test
	public void getLatestList() {
		List<ReadingWater> list = dao.getLatestList();
		Assert.assertEquals(list.size(), 5);
		for (ReadingWater readingWater : list) {
			Assert.assertEquals(readingWater.getValue(), 145.0);
		}
	}

	@Test
	public void listForTenant() {

		List<ReadingWater> list = dao.getListForTenant(getAp());
		double sum = 0.0;
		for (ReadingWater readingWater : list) {
			sum += readingWater.getValue();
		}
		Assert.assertEquals(list.size(), 7);
		Assert.assertEquals(sum, 730.0);

	}
//
//	@Test
//	public void getLatestMap() {
//		HashMap<Integer, ReadingWater> list = dao.getLatestReadingsMap();
//		Assert.assertEquals(list.get(1).getValue(), 145.0);
//
//	}

	@Test
	public void addAndRemove() {
		MeterWater test = meterDAO.getById(1);
		ReadingWater reading = new ReadingWater(new Date(), 300, test);
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
