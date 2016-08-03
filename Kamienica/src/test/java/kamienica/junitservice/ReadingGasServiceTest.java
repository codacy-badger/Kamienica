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
import kamienica.feature.meter.MeterGas;
import kamienica.feature.meter.MeterService;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingService;

public class ReadingGasServiceTest extends AbstractServiceTest {

	@Autowired
	ReadingService service;
	@Autowired
	MeterService meterService;

	private Set<Long> meterIdList = new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L));

	@Test
	public void getLatest() {

		List<ReadingGas> list = service.getLatestNew(Media.GAS);
		assertEquals(6, list.size());
		for (ReadingGas readingGas : list) {
			assertEquals(LocalDate.parse("2016-10-01"), readingGas.getReadingDate());
		}
	}

	@Test
	@Override
	public void getList() {
		assertEquals(18, service.getReadingGas().size());
	}

	@Test
	@Transactional
	public void shouldDeleteLatestList() {
		service.deleteLatestReadings(Media.GAS);
		List<ReadingGas> list = service.getReadingGas();
		assertEquals(12, list.size());
		for (ReadingGas readingGas : list) {
			assertNotEquals(LocalDate.parse("2016-10-01"), readingGas.getReadingDate());
		}
	}

	@Test
	public void shouldRetrieviePreviousReadings() {
		List<ReadingGas> list = service.getPreviousReadingGas(LocalDate.parse("2016-08-01"), meterIdList);

		for (ReadingGas readingGas : list) {
			assertEquals(LocalDate.parse("2016-07-29"), readingGas.getReadingDate());
		}
	}

	@Test
	public void getByDate() {
		List<ReadingGas> list = service.getByDate(LocalDate.parse("2016-07-01"), Media.GAS);
		for (ReadingGas readingGas : list) {
			assertEquals(LocalDate.parse("2016-07-01"), readingGas.getReadingDate());
		}
	}

	@Test
	public void getUnresolved() {
		List<ReadingGas> list = service.getUnresolvedReadingsGas();
		assertEquals(2, list.size());
		assertEquals("2016-07-29", list.get(0).getReadingDate().toString());
		assertEquals(true, list.get(0).getMeter().isMain());
		assertEquals("2016-10-01", list.get(1).getReadingDate().toString());

	}

	@Transactional
	@Test
	public void firstReadingForANewMeter() {
		MeterGas meter = new MeterGas("test", "34", "3535", null, false);
		meterService.save(meter, Media.GAS);
		List<ReadingGas> list = service.getLatestNew(Media.GAS);
		assertEquals(7, list.size());
	}

	@Override
	public void getById() {
		ReadingGas reading = service.getById(4L, Media.GAS);
		assertEquals(LocalDate.parse("2016-07-29"), reading.getReadingDate());
		assertEquals(3, reading.getValue(), 0);

	}

	@Test
	public void getPreviousReadings() {
		List<ReadingGas> list = service.getPreviousReadingGas(LocalDate.parse("2016-10-01"), meterIdList);
		assertEquals(6, list.size());
		for (ReadingGas readingGas : list) {
			assertEquals("2016-09-01", readingGas.getReadingDate().toString());

		}
	}

	@Transactional
	@Test
	@Override
	public void add() {
		List<MeterGas> list = meterService.getList(Media.GAS);
		List<ReadingGas> toSave = new ArrayList<>();
		for (MeterGas meter : list) {
			ReadingGas reading = new ReadingGas(LocalDate.parse("2050-01-01"), 800, meter);
			toSave.add(reading);
		}
		service.save(toSave, LocalDate.parse("2050-01-01"), Media.GAS);
		assertEquals(24, service.getReadingGas().size());
		assertEquals(LocalDate.parse("2050-01-01"), service.getLatestNew(Media.GAS).get(0).getReadingDate());
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
