//package kamienica.dao;
//
//import java.util.List;
//
//import org.joda.time.Days;
//import org.joda.time.LocalDate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import kamienica.feature.apartment.Apartment;
//import kamienica.feature.invoice.InvoiceEnergy;
//import kamienica.feature.meter.MeterEnergy;
//import kamienica.feature.reading.ReadingDao;
//import kamienica.feature.reading.ReadingEnergy;
//
//public class ReadingEnergyDaoTest extends EntityDaoImplTest {
//
//	@Autowired
//	ReadingDao<ReadingEnergy, InvoiceEnergy> dao;
//	@Autowired
//	DaoInterface<MeterEnergy> meterDAO;
//
//	
//
//	@Test
//	public void getUnresolvedReadings() {
//		List<ReadingEnergy> list = dao.getUnresolvedReadings();
//		System.out.println(list);
//		Assert.assertEquals(list.size(), 2);
//	}
//
//	@Test
//	public void getByDate() {
//
//		List<ReadingEnergy> list = dao.getByDate(new LocalDate(2010, 01, 01).toString());
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
//	@Test
//	public void countDaysFromLastReading() {
//		LocalDate start = LocalDate.parse("2010-04-01");
//		System.out.println(start.getMonthOfYear());
//		LocalDate end = LocalDate.now();
//		int actual = Days.daysBetween(start, end).getDays();
//		Assert.assertEquals(dao.countDaysFromLastReading(), actual);
//	}
//
//	@Rollback
//	@Test
//	public void resolveReadings() {
//		ReadingEnergy reading = dao.getById(13L);
//		InvoiceEnergy invoice = new InvoiceEnergy("sdf", "test", LocalDate.now(), 120, reading);
//		dao.resolveReadings(invoice);
//		dao.getUnresolvedReadings();
//
//		List<ReadingEnergy> list = dao.getByDate(new LocalDate(2010, 04, 01).toString());
//		Assert.assertEquals(list.size(), 5);
//		for (ReadingEnergy readingEnergy : list) {
//			Assert.assertTrue(readingEnergy.isResolved());
//		}
//
//	}
//
//	// @Test
//	// public void getPrevious() {
//	// List<ReadingEnergy> list = dao.getPrevious("2010-03-01");
//	// Assert.assertEquals(list.size(), 4);
//	// for (ReadingEnergy readingEnergy : list) {
//	// Assert.assertEquals(readingEnergy.getValue(), 120.0);
//	// }
//	// }
//	//
//	// @Test
//	// public void getLatestList() {
//	// List<ReadingEnergy> list = dao.getLatestList();
//	// Assert.assertEquals(list.size(), 4);
//	// for (ReadingEnergy readingEnergy : list) {
//	// Assert.assertEquals(readingEnergy.getValue(), 145.0);
//	// }
//	// }
//
//	@Test
//	public void listForTenant() {
//
//		List<ReadingEnergy> list = dao.getListForTenant(getAp());
//		double sum = 0.0;
//		for (ReadingEnergy readingEnergy : list) {
//			sum += readingEnergy.getValue();
//		}
//		Assert.assertEquals(list.size(), 6);
//		Assert.assertEquals(sum, 730.0);
//
//	}
//
//	// @Test
//	// public void getLatestMap() {
//	// HashMap<Integer, ReadingEnergy> list = dao.getLatestReadingsMap();
//	// Assert.assertEquals(list.get(1).getValue(), 145.0);
//	//
//	// }
//	@Rollback
//	@Test
//	public void add() {
//		MeterEnergy test = meterDAO.getById(1L);
//		ReadingEnergy reading = new ReadingEnergy(new LocalDate(), 300, test);
//		dao.save(reading);
//		Assert.assertEquals(dao.getList().size(), 21);
//	}
//
//	@Rollback
//	@Test
//	public void remove() {
//		dao.deleteById(13L);
//		Assert.assertEquals(dao.getList().size(), 20);
//	}
//
//	private static Apartment getAp() {
//		Apartment ap = new Apartment();
//		ap.setId(3L);
//		ap.setApartmentNumber(2);
//		return ap;
//	}
//}
