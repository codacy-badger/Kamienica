package kamienica.junitservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.Media;
import kamienica.feature.meter.MeterWater;
import kamienica.feature.meter.MeterService;
import kamienica.feature.reading.ReadingWater;
import kamienica.feature.reading.ReadingService;

public class ReadingWaterServiceTest extends AbstractServiceTest {

	@Autowired
	ReadingService service;
	@Autowired
	MeterService meterService;

	private Set<Long> meterIdList = new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7l));

	@Test
	public void getLatest() {

		List<ReadingWater> list = service.getLatestNew(Media.WATER);
		assertEquals(7, list.size());
		for (ReadingWater readingWater : list) {
			assertEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDate());
		}
	}

	@Test
	@Override
	public void getList() {
		assertEquals(21, service.getReadingWater().size());
	}

	@Test
	@Transactional
	public void shouldDeleteLatestList() {
		service.deleteLatestReadings(Media.WATER);
		List<ReadingWater> list = service.getReadingWater();
		assertEquals(14, list.size());
		for (ReadingWater readingWater : list) {
			assertNotEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDate());
		}
	}

	@Test
	public void shouldRetrieviePreviousReadings() {
		List<ReadingWater> list = service.getPreviousReadingWater(LocalDate.parse("2016-08-01"), meterIdList);
		for (ReadingWater readingWater : list) {
			assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDate());
		}
	}

	@Test
	public void getByDate() {
		List<ReadingWater> list = service.getByDate(LocalDate.parse("2016-07-01"), Media.WATER);
		for (ReadingWater readingWater : list) {
			assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDate());
		}
	}

	@Test
	public void getUnresolved() {
		List<ReadingWater> list = service.getUnresolvedReadingsWater();
		assertEquals(2, list.size());
		assertEquals("2016-07-01", list.get(0).getReadingDate().toString());
		assertEquals(true, list.get(0).getMeter().isMain());
		assertEquals("2016-09-01", list.get(1).getReadingDate().toString());

	}

	@Transactional
	@Test
	public void firstReadingForANewMeter() {
		MeterWater meter = new MeterWater("test", "346767676", "3535", null, false);
		meterService.save(meter, Media.WATER);
		List<ReadingWater> list = service.getLatestNew(Media.WATER);
		assertEquals(8, list.size());
	}

	@Override
	public void getById() {
		ReadingWater reading = service.getById(4L, Media.WATER);
		assertEquals(LocalDate.parse("2016-07-01"), reading.getReadingDate());
		assertEquals(5, reading.getValue(), 0);

	}

	@Test
	public void getPreviousReadings() {
		List<ReadingWater> list = service.getPreviousReadingWater(LocalDate.parse("2016-09-01"), meterIdList);
		assertEquals(7, list.size());
		for (ReadingWater readingWater : list) {
			assertEquals("2016-08-01", readingWater.getReadingDate().toString());

		}
	}

	@Transactional
	@Test
	@Override
	public void add() {
		List<MeterWater> list = meterService.getList(Media.WATER);
		List<ReadingWater> toSave = new ArrayList<>();
		for (MeterWater meter : list) {
			ReadingWater reading = new ReadingWater(LocalDate.parse("2050-01-01"), 800, meter);
			toSave.add(reading);
		}
		service.save(toSave, LocalDate.parse("2050-01-01"), Media.WATER);
		assertEquals(28, service.getReadingWater().size());
		assertEquals(LocalDate.parse("2050-01-01"), service.getLatestNew(Media.WATER).get(0).getReadingDate());
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {

	}

	@Override
	public void addWithValidationError() {
		// TODO Auto-generated method stub

	}

}
