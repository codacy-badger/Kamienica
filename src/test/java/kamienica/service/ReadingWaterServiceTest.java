package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.enums.Media;
import kamienica.model.exception.NoMainCounterException;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Residence;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class ReadingWaterServiceTest extends ServiceTest {

    private Set<Long> meterIdList = new HashSet<>(Arrays.asList(RESIDENCE_ID, 2L, 3L, 4L, 5L, 6L, 7L));

    @Test
    public void getLatest() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        final Residence r = residenceService.getById(RESIDENCE_ID);
        List<ReadingWater> list = IReadingService.getLatestNew(r,Media.WATER);
        assertEquals(7, list.size());
        for (ReadingWater readingWater : list) {
            assertEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDate());
        }
    }

    @Transactional
    @Test
    public void getLatestActiveOnly() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        MeterWater meter = meterService.getById(3L, Media.WATER);
        meter.setDeactivation(LocalDate.parse("2016-01-01"));
        meterService.update(meter, Media.WATER);

        final Residence r = residenceService.getById(RESIDENCE_ID);

        List<ReadingWater> list2 = IReadingService.getLatestNew(r, Media.WATER);

        assertEquals(6, list2.size());
        for (ReadingWater readingWater : list2) {
            assertEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDate());
        }
    }

    @Test
    public void getListForResidence() {
       final Residence r = residenceService.getById(RESIDENCE_ID);
        List<ReadingWater> list = (List<ReadingWater>) IReadingService.getList(r, Media.WATER);
        assertEquals(21, list.size());
    }

    @Test
    public void getList() {
        assertEquals(22, IReadingService.getList(Media.WATER).size());
    }

    @Test
    @Transactional
    public void shouldDeleteLatestList() {
        final Residence r = residenceService.getById(RESIDENCE_ID);
        IReadingService.deleteLatestReadings(r, Media.WATER);
        List<? extends Reading> list = IReadingService.getList(Media.WATER);
        assertEquals(14, list.size());
        for (Reading readingWater : list) {
            assertNotEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDate());
        }
    }

    @Test
    public void shouldRetrieviePreviousReadings() {
        List<ReadingWater> list = IReadingService.getPreviousReadingWater(LocalDate.parse("2016-08-01"), meterIdList);
        for (ReadingWater readingWater : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDate());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getByDate() {
        final Residence r = residenceService.getById(RESIDENCE_ID);
        List<ReadingWater> list = (List<ReadingWater>) IReadingService.getByDate(r, LocalDate.parse("2016-07-01"), Media.WATER);
        for (ReadingWater readingWater : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDate());
        }
    }

    @Test
    public void getUnresolved() {
        List<ReadingWater> list = IReadingService.getUnresolvedReadingsWater();
        assertEquals(2, list.size());
        assertEquals("2016-07-01", list.get(0).getReadingDate().toString());
        assertEquals(true, list.get(0).getMeter().isMain());
        assertEquals("2016-09-01", list.get(1).getReadingDate().toString());

    }

    @Transactional
    @Test
    public void firstReadingForANewMeter() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        final Apartment ap = apartmentService.getById(2L);
        MeterWater meter = new MeterWater("test", "346767676", "3535", ap, false);
        meterService.save(meter, Media.WATER);

        final Residence r = residenceService.getById(RESIDENCE_ID);

        List<ReadingWater> list = IReadingService.getLatestNew(r, Media.WATER);
        assertEquals(8, list.size());
    }

    @Test
    public void getById() {
        ReadingWater reading = IReadingService.getById(4L, Media.WATER);
        assertEquals(LocalDate.parse("2016-07-01"), reading.getReadingDate());
        assertEquals(5, reading.getValue(), 0);

    }

    @Test
    public void getPreviousReadings() {
        List<ReadingWater> list = IReadingService.getPreviousReadingWater(LocalDate.parse("2016-09-01"), meterIdList);
        assertEquals(7, list.size());
        for (ReadingWater readingWater : list) {
            assertEquals("2016-08-01", readingWater.getReadingDate().toString());

        }
    }

    @Transactional
    @Test
    public void add() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        List<Residence> residences = getMockedResidences();
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residences);

        List<MeterWater> list = meterService.getListForOwner(Media.WATER);
        List<ReadingWater> toSave = new ArrayList<>();
        for (MeterWater meter : list) {
            ReadingWater reading = new ReadingWater(LocalDate.parse("2050-01-01"), 800, meter);
            toSave.add(reading);
        }
        IReadingService.save(toSave, LocalDate.parse("2050-01-01"), Media.WATER);

        final Residence r = residenceService.getById(RESIDENCE_ID);

        assertEquals(29, IReadingService.getList(Media.WATER).size());
        assertEquals(LocalDate.parse("2050-01-01"), IReadingService.getLatestNew(r, Media.WATER).get(0).getReadingDate());
    }

}
