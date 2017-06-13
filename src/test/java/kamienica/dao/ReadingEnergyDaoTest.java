//package kamienica.dao;
//
//import java.util.List;
//
//import org.joda.time.Days;
//import org.joda.time.LocalDate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.testng.Assert;
//import org.testng.annotations.Testing;
//
//import kamienica.model.entity.Apartment;
//import kamienica.model.InvoiceEnergy;
//import kamienica.model.MeterEnergy;
//import kamienica.feature.reading.IReadingDao;
//import kamienica.model.ReadingEnergy;
//
//public class ReadingEnergyDaoTest extends EntityDaoImplTest {
//
//	@Autowired
//	IReadingDao<ReadingEnergy, InvoiceEnergy> daoservice;
//	@Autowired
//	IBasicDao<MeterEnergy> meterDAO;
//
//	
//
//	@Testing
//	public void getUnresolvedReadings() {
//		List<ReadingEnergy> list = daoservice.getUnresolvedReadings();
//		System.out.println(list);
//		Assert.assertEquals(list.size(), 2);
//	}
//
//	@Testing
//	public void getByDate() {
//
//		List<ReadingEnergy> list = daoservice.getByDate(new LocalDate(2010, 01, 01).toString());
//		Assert.assertEquals(list.size(), 5);
//		for (int i = 0; i < list.size(); i++) {
//			if (i > 0) {
//				Assert.assertEquals(list.get(i).getValue(), 100.0);
//			} else {
//				Assert.assertEquals(list.get(i).getValue(), 500.0);
//			}
//		}
//
//	}
//
//	@Testing
//	public void countDaysFromLastReading() {
//		LocalDate start = LocalDate.parse("2010-04-01");
//		System.out.println(start.getMonthOfYear());
//		LocalDate end = LocalDate.now();
//		int actual = Days.daysBetween(start, end).getDays();
//		Assert.assertEquals(daoservice.countDaysFromLastReading(), actual);
//	}
//
//	@Rollback
//	@Testing
//	public void resolveReadings() {
//		ReadingEnergy reading = daoservice.getById(13L);
//		InvoiceEnergy invoice = new InvoiceEnergy("sdf", "test", LocalDate.now(), 120, reading);
//		daoservice.resolveReadings(invoice);
//		daoservice.getUnresolvedReadings();
//
//		List<ReadingEnergy> list = daoservice.getByDate(new LocalDate(2010, 04, 01).toString());
//		Assert.assertEquals(list.size(), 5);
//		for (ReadingEnergy readingEnergy : list) {
//			Assert.assertTrue(readingEnergy.getResolvement());
//		}
//
//	}
//
//	// @Testing
//	// public void getPrevious() {
//	// List<ReadingEnergy> list = daoservice.getPrevious("2010-03-01");
//	// Assert.assertEquals(list.size(), 4);
//	// for (ReadingEnergy readingEnergy : list) {
//	// Assert.assertEquals(readingEnergy.getValue(), 120.0);
//	// }
//	// }
//	//
//	// @Testing
//	// public void getLatestList() {
//	// List<ReadingEnergy> list = daoservice.getLatestList();
//	// Assert.assertEquals(list.size(), 4);
//	// for (ReadingEnergy readingEnergy : list) {
//	// Assert.assertEquals(readingEnergy.getValue(), 145.0);
//	// }
//	// }
//
//	@Testing
//	public void listForTenant() {
//
//		List<ReadingEnergy> list = daoservice.getListForTenant(getAp());
//		double sum = 0.0;
//		for (ReadingEnergy readingEnergy : list) {
//			sum += readingEnergy.getValue();
//		}
//		Assert.assertEquals(list.size(), 6);
//		Assert.assertEquals(sum, 730.0);
//
//	}
//
//	// @Testing
//	// public void getLatestMap() {
//	// HashMap<Integer, ReadingEnergy> list = daoservice.getLatestReadingsMap();
//	// Assert.assertEquals(list.get(1).getValue(), 145.0);
//	//
//	// }
//	@Rollback
//	@Testing
//	public void add() {
//		MeterEnergy test = meterDAO.getById(1L);
//		ReadingEnergy reading = new ReadingEnergy(new LocalDate(), 300, test);
//		daoservice.save(reading);
//		Assert.assertEquals(daoservice.getListForOwner().size(), 21);
//	}
//
//	@Rollback
//	@Testing
//	public void remove() {
//		daoservice.delete(13L);
//		Assert.assertEquals(daoservice.getListForOwner().size(), 20);
//	}
//
//	private static Apartment getAp() {
//		Apartment ap = new Apartment();
//		ap.setId(3L);
//		ap.setApartmentNumber(2);
//		return ap;
//	}
//}
