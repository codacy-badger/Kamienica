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
//import kamienica.model.InvoiceGas;
//import kamienica.feature.meter.MeterGas;
//import kamienica.feature.reading.ReadingDao;
//import kamienica.model.ReadingGas;
//
//public class ReadingGasDaoTest extends EntityDaoImplTest {
//
//	@Autowired
//	ReadingDao<ReadingGas, InvoiceGas> daoservice;
//	@Autowired
//	BasicDao<MeterGas> meterDAO;
//
//	@Testing
//	public void getUnresolvedReadings() {
//		Assert.assertEquals(daoservice.getUnresolvedReadings().size(), 2);
//	}
//
//	@Testing
//	public void getByDate() {
//		ReadingGas reading = daoservice.getById(1L);
//		List<ReadingGas> list = daoservice.getByDate(reading.getReadingDate().toString());
//		Assert.assertEquals(list.size(), 5);
//		for (ReadingGas readingGas : list) {
//			Assert.assertEquals(readingGas.getValue(), 100.0);
//		}
//
//	}
//
//	@Testing
//	public void getPrevious() {
//		List<ReadingGas> list = daoservice.getPrevious("2010-03-01");
//		Assert.assertEquals(list.size(), 5);
//		for (ReadingGas readingGas : list) {
//			Assert.assertEquals(readingGas.getValue(), 120.0);
//		}
//	}
//
//	@Testing
//	public void getLatestList() {
//		List<ReadingGas> list = daoservice.getLatestList();
//		Assert.assertEquals(list.size(), 5);
//		for (ReadingGas readingGas : list) {
//			Assert.assertEquals(readingGas.getValue(), 145.0);
//		}
//	}
//
//	@Testing
//	public void listForTenant() {
//
//		List<ReadingGas> list = daoservice.getListForTenant(getAp());
//		double sum = 0.0;
//		for (ReadingGas readingGas : list) {
//			sum += readingGas.getValue();
//		}
//		Assert.assertEquals(list.size(), 6);
//		Assert.assertEquals(sum, 730.0);
//
//	}
//
////	@Testing
////	public void getLatestMap() {
////		HashMap<Integer, ReadingGas> list = daoservice.getLatestReadingsMap();
////		Assert.assertEquals(list.get(1).getValue(), 145.0);
////
////	}
//
//	@Testing
//	public void addAndRemove() {
//		MeterGas test = meterDAO.getById(1L);
//		ReadingGas reading = new ReadingGas(new LocalDate(), 300, test);
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
