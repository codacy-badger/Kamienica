package kamienica.service;

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

import kamienica.core.exception.NoMainCounterException;
import kamienica.core.enums.Media;
import kamienica.feature.meter.MeterEnergy;
import kamienica.feature.meter.MeterService;
import kamienica.feature.reading.Reading;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingService;

public class ReadingEnergyServiceTest extends AbstractServiceTest {

	@Autowired
	ReadingService service;
	@Autowired
	MeterService meterService;

	private Set<Long> meterIdList = new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L));

	@Test
	public void getLatest() throws NoMainCounterException {
		List<ReadingEnergy> list2 = service.getLatestNew(Media.ENERGY);

		assertEquals(5, list2.size());
		for (ReadingEnergy readingEnergy : list2) {
			assertEquals(LocalDate.parse("2016-09-01"), readingEnergy.getReadingDate());
		}
	}

	@Transactional
	@Test
	public void getLatestActiveOnly() throws NoMainCounterException {
		MeterEnergy meter = meterService.getById(3L, Media.ENERGY);
		meter.setDeactivation(LocalDate.parse("2016-01-01"));
		meterService.update(meter, Media.ENERGY);
		
		List<ReadingEnergy> list2 = service.getLatestNew(Media.ENERGY);

		assertEquals(4, list2.size());
		for (ReadingEnergy readingEnergy : list2) {
			assertEquals(LocalDate.parse("2016-09-01"), readingEnergy.getReadingDate());
		}
	}

	@Test
	public void getList() {
		assertEquals(15, service.getList(Media.ENERGY).size());
	}

	@Test
	@Transactional
	public void shouldDeleteLatestList() {
		service.deleteLatestReadings(Media.ENERGY);
		List<? extends Reading> list = service.getList(Media.ENERGY);
		assertEquals(10, list.size());
		for (Reading readingEnergy : list) {
			assertNotEquals(LocalDate.parse("2016-09-01"), readingEnergy.getReadingDate());
		}
	}

	@Test
	public void shouldRetrieviePreviousReadings() {
		List<ReadingEnergy> list = service.getPreviousReadingEnergy(LocalDate.parse("2016-09-01"), meterIdList);

		for (ReadingEnergy readingEnergy : list) {
			assertEquals(LocalDate.parse("2016-08-01"), readingEnergy.getReadingDate());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getByDate() {
		List<ReadingEnergy> list = (List<ReadingEnergy>) service.getByDate(LocalDate.parse("2016-07-01"), Media.ENERGY);
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

	}

	@Transactional
	@Test
	public void firstReadingForANewMeter() throws NoMainCounterException {
		MeterEnergy meter = new MeterEnergy("test", "34", "3535", null);
		meterService.save(meter, Media.ENERGY);
		List<ReadingEnergy> list = service.getLatestNew(Media.ENERGY);
		assertEquals(6, list.size());
	}

	@Test
	public void getById() {
		ReadingEnergy reading = service.getById(4L, Media.ENERGY);
		assertEquals(LocalDate.parse("2016-07-01"), reading.getReadingDate());
		assertEquals(2, reading.getValue(), 0);

	}

	@Test
	public void getPreviousReadings() {
		List<ReadingEnergy> list = service.getPreviousReadingEnergy(LocalDate.parse("2016-08-01"), meterIdList);
		assertEquals(5, list.size());
		for (ReadingEnergy readingEnergy : list) {
			assertEquals("2016-07-01", readingEnergy.getReadingDate().toString());

		}
	}

	@Transactional
	@Test
	public void add() throws NoMainCounterException {
		List<MeterEnergy> list = meterService.getList(Media.ENERGY);
		List<ReadingEnergy> toSave = new ArrayList<>();
		for (MeterEnergy meter : list) {
			ReadingEnergy reading = new ReadingEnergy(LocalDate.parse("2050-01-01"), 800, meter);
			toSave.add(reading);
		}
		service.save(toSave, LocalDate.parse("2050-01-01"), Media.ENERGY);
		assertEquals(20, service.getList(Media.ENERGY).size());
		assertEquals(LocalDate.parse("2050-01-01"), service.getLatestNew(Media.ENERGY).get(0).getReadingDate());
	}

	@Transactional
	@Test
	public void update() throws NoMainCounterException {
		List<ReadingEnergy> list = service.getLatestNew(Media.ENERGY);

		for (int i = 0; i < list.size(); i++) {
			list.get(i).setValue(6767.0);
		}
		service.update(list, new LocalDate(), Media.ENERGY);
		List<ReadingEnergy> list2 = service.getLatestNew(Media.ENERGY);
		assertEquals(5, list2.size());
		for (ReadingEnergy readingEnergy : list2) {
			assertEquals(6767, readingEnergy.getValue(), 0);
		}
	}
	
	

}
