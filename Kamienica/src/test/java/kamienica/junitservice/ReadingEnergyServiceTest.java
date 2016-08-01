package kamienica.junitservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.Media;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingService;

public class ReadingEnergyServiceTest extends AbstractServiceTest {

	@Autowired
	ReadingService service;

	private Set<Long> meterIdList = new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L));

	@Test
	public void getLatest() {

		List<ReadingEnergy> list = service.energyLatestNew(meterIdList);
		assertEquals(5, list.size());
		for (ReadingEnergy readingEnergy : list) {
			assertEquals(LocalDate.parse("2016-09-01"), readingEnergy.getReadingDate());
		}
	}

	@Test
	@Override
	public void getList() {
		assertEquals(15, service.getReadingEnergy().size());
	}

	@Test
	@Transactional
	public void shouldDeleteLatestList() {
		service.deleteLatestReadings(Media.ENERGY);
		List<ReadingEnergy> list = service.getReadingEnergy();
		assertEquals(10, list.size());
		for (ReadingEnergy readingEnergy : list) {
			assertNotEquals(LocalDate.parse("2016-09-01"), readingEnergy.getReadingDate());
		}
	}

	@Test
	public void shouldRetrieviePreviousReadings() {
		List<ReadingEnergy> list =	service.getPreviousReadingEnergy("2016-09-01", meterIdList);
		for (ReadingEnergy readingEnergy : list) {
			assertEquals(LocalDate.parse("2016-08-01"), readingEnergy.getReadingDate());
		}
	}
	
	@Test
	public void getByDate() {
		List<ReadingEnergy> list = service.getReadingEnergyByDate("2016-07-01");
		for (ReadingEnergy readingEnergy : list) {
			assertEquals(LocalDate.parse("2016-07-01"), readingEnergy.getReadingDate());
		}
	}
	
	@Test
	public void getUnresolved() {
		List<ReadingEnergy> list = service.getUnresolvedReadingsEnergy();
		assertEquals(2, list.size());
		assertEquals("2016-07-01", list.get(0).getReadingDate().toString());
		assertEquals(true, list.get(0).getMeter().isMain());
		assertEquals("2016-09-01", list.get(1).getReadingDate().toString());
		
		System.out.println(service.energyLatestEdit(meterIdList));
	}
	
	@Override
	public void getById() {
		// TODO Auto-generated method stub

	}

	@Override
	public void add() {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addWithValidationError() {
		// TODO Auto-generated method stub

	}

}
