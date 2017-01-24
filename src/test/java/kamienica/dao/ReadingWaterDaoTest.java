//package kamienica.dao;
//
//import java.util.List;
//
//import org.joda.time.LocalDate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.testng.Assert;
//import org.testng.annotations.Testing;
//
//import kamienica.model.Apartment;
//import kamienica.feature.meter.MeterWater;
//import kamienica.model.ReadingWater;
//import kamienica.feature.reading.ReadingWaterDAO;
//
//public class ReadingWaterDaoTest extends EntityDaoImplTest {
//
//	@Autowired
//	ReadingWaterDAO daoservice;
//	@Autowired
//	BasicDao<MeterWater> meterDAO;
//
//	@Testing
//	public void getUnresolvedReadings() {
//		List<ReadingWater> list = daoservice.getListForOwner();
//		for (ReadingWater readingWater : list) {
//			System.out.println(readingWater);
//		}
//
//		List<MeterWater> meterList = meterDAO.getListForOwner();
//		for (MeterWater meterWater : meterList) {
//			System.out.println(meterWater);
//		}
//		Assert.assertEquals(daoservice.getUnresolvedReadings().size(), 2);
//	}
//
//	@Testing
//	public void getByDate() {
//		ReadingWater reading = daoservice.getById(1L);
//		List<ReadingWater> list = daoservice.getByDate(reading.getReadingDate().toString());
//		Assert.assertEquals(list.size(), 5);
//		for (ReadingWater readingWater : list) {
//			Assert.assertEquals(readingWater.getValue(), 100.0);
//		}
//
//	}
//
//	@Testing
//	public void getPrevious() {
//		List<ReadingWater> list = daoservice.getPrevious("2010-03-01");
//		Assert.assertEquals(list.size(), 5);
//		for (ReadingWater readingWater : list) {
//			Assert.assertEquals(readingWater.getValue(), 120.0);
//		}
//	}
//
//	@Testing
//	public void getLatestList() {
//		List<ReadingWater> list = daoservice.getLatestList();
//		Assert.assertEquals(list.size(), 5);
//		for (ReadingWater readingWater : list) {
//			Assert.assertEquals(readingWater.getValue(), 145.0);
//		}
//	}
//
//	@Testing
//	public void listForTenant() {
//
//		List<ReadingWater> list = daoservice.getListForTenant(getAp());
//		double sum = 0.0;
//		for (ReadingWater readingWater : list) {
//			sum += readingWater.getValue();
//		}
//		Assert.assertEquals(list.size(), 7);
//		Assert.assertEquals(sum, 730.0);
//
//	}
////
////	@Testing
////	public void getLatestMap() {
////		HashMap<Integer, ReadingWater> list = daoservice.getLatestReadingsMap();
////		Assert.assertEquals(list.get(1).getValue(), 145.0);
////
////	}
//
//	@Testing
//	public void addAndRemove() {
//		MeterWater test = meterDAO.getById(1L);
//		ReadingWater reading = new ReadingWater(new LocalDate(), 300, test);
//		daoservice.save(reading);
//		Assert.assertEquals(daoservice.getListForOwner().size(), 16);
//		Assert.assertEquals(daoservice.getById(16L).getValue(), 300.0);
//		daoservice.deleteById(13L);
//		Assert.assertEquals(daoservice.getListForOwner().size(), 15);
//	}
//
//	private static Apartment getAp() {
//		Apartment ap = new Apartment();
//		ap.setId(3L);
//		ap.setApartmentNumber(2);
//		return ap;
//	}
//}
