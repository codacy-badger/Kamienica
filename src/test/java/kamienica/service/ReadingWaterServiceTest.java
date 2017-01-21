package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.core.enums.Media;
import kamienica.core.exception.NoMainCounterException;
import kamienica.model.*;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ReadingWaterServiceTest extends DatabaseTest {

    private Set<Long> meterIdList = new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L));

    @Test
    public void getLatest() throws NoMainCounterException {

        List<ReadingWater> list = readingService.getLatestNew(Media.WATER);
        assertEquals(7, list.size());
        for (ReadingWater readingWater : list) {
            assertEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDate());
        }
    }

    @Transactional
    @Test
    public void getLatestActiveOnly() throws NoMainCounterException {
        MeterWater meter = meterService.getById(3L, Media.WATER);
        meter.setDeactivation(LocalDate.parse("2016-01-01"));
        meterService.update(meter, Media.WATER);

        List<ReadingWater> list2 = readingService.getLatestNew(Media.WATER);

        assertEquals(6, list2.size());
        for (ReadingWater readingWater : list2) {
            assertEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDate());
        }
    }

    @Test
    public void getList() {
        assertEquals(21, readingService.getList(Media.WATER).size());
    }

    @Test
    @Transactional
    public void shouldDeleteLatestList() {
        readingService.deleteLatestReadings(Media.WATER);
        List<? extends Reading> list = readingService.getList(Media.WATER);
        assertEquals(14, list.size());
        for (Reading readingWater : list) {
            assertNotEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDate());
        }
    }

    @Test
    public void shouldRetrieviePreviousReadings() {
        List<ReadingWater> list = readingService.getPreviousReadingWater(LocalDate.parse("2016-08-01"), meterIdList);
        for (ReadingWater readingWater : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDate());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getByDate() {
        List<ReadingWater> list = (List<ReadingWater>) readingService.getByDate(LocalDate.parse("2016-07-01"), Media.WATER);
        for (ReadingWater readingWater : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDate());
        }
    }

    @Test
    public void getUnresolved() {
        List<ReadingWater> list = readingService.getUnresolvedReadingsWater();
        assertEquals(2, list.size());
        assertEquals("2016-07-01", list.get(0).getReadingDate().toString());
        assertEquals(true, list.get(0).getMeter().isMain());
        assertEquals("2016-09-01", list.get(1).getReadingDate().toString());

    }

    @Transactional
    @Test
    public void firstReadingForANewMeter() throws NoMainCounterException {
        final Apartment ap = apartmentService.getById(2L);
        MeterWater meter = new MeterWater("test", "346767676", "3535", ap, false);
        meterService.save(meter, Media.WATER);
        List<ReadingWater> list = readingService.getLatestNew(Media.WATER);
        assertEquals(8, list.size());
    }

    @Test
    public void getById() {
        ReadingWater reading = readingService.getById(4L, Media.WATER);
        assertEquals(LocalDate.parse("2016-07-01"), reading.getReadingDate());
        assertEquals(5, reading.getValue(), 0);

    }

    @Test
    public void getPreviousReadings() {
        List<ReadingWater> list = readingService.getPreviousReadingWater(LocalDate.parse("2016-09-01"), meterIdList);
        assertEquals(7, list.size());
        for (ReadingWater readingWater : list) {
            assertEquals("2016-08-01", readingWater.getReadingDate().toString());

        }
    }

    @Transactional
    @Test
    public void add() throws NoMainCounterException {
        final Tenant t = tenantService.getTenantById(1L);
        List<MeterWater> list = meterService.getListForOwner(Media.WATER, t);
        List<ReadingWater> toSave = new ArrayList<>();
        for (MeterWater meter : list) {
            ReadingWater reading = new ReadingWater(LocalDate.parse("2050-01-01"), 800, meter);
            toSave.add(reading);
        }
        readingService.save(toSave, LocalDate.parse("2050-01-01"), Media.WATER);
        assertEquals(28, readingService.getList(Media.WATER).size());
        assertEquals(LocalDate.parse("2050-01-01"), readingService.getLatestNew(Media.WATER).get(0).getReadingDate());
    }

}
