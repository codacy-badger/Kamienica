package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import kamienica.model.exception.NoMainCounterException;
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
        List<Reading> list = readingService.getLatestNew(r, Media.WATER);
        assertEquals(7, list.size());
        for (Reading readingWater : list) {
            assertEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @Transactional
    @Test
    public void getLatestActiveOnly() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        Meter meter = meterService.getById(3L, Media.WATER);
        meter.setStatus(Status.INACTIVE);
        meterService.update(meter);

        final Residence r = residenceService.getById(RESIDENCE_ID);

        List<Reading> list2 = readingService.getLatestNew(r, Media.WATER);

        assertEquals(6, list2.size());
        for (Reading readingWater : list2) {
            assertEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getListForResidence() {
        final Residence r = residenceService.getById(RESIDENCE_ID);
        List<Reading> list = readingService.getList(r, Media.WATER);
        assertEquals(21, list.size());
    }

    @Test
    public void getList() {
        final Residence r = residenceService.getById(RESIDENCE_ID);
        assertEquals(22, readingService.getList(r, Media.WATER).size());
    }

    @Test
    @Transactional
    public void shouldDeleteLatestList() {
        final Residence r = residenceService.getById(RESIDENCE_ID);
        readingService.deleteLatestReadings(r, Media.WATER);
        List<Reading> list = readingService.getList(r, Media.WATER);
        assertEquals(14, list.size());
        for (Reading readingWater : list) {
            assertNotEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void shouldRetrieviePreviousReadings() {
        List<Reading> list = readingService.getPreviousReading(LocalDate.parse("2016-08-01"), meterIdList);
        for (Reading readingWater : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getByDate() {
        final Residence r = residenceService.getById(RESIDENCE_ID);
        List<Reading> list = readingService.getByDate(r, LocalDate.parse("2016-07-01"), Media.WATER);
        for (Reading readingWater : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getUnresolved() {
        List<Reading> list = readingService.getUnresolvedReadings(Media.WATER, residenceService.getById(RESIDENCE_ID));
        assertEquals(2, list.size());
        assertEquals("2016-07-01", list.get(0).getReadingDetails().getReadingDate().toString());
        assertEquals(true, list.get(0).getMeter().isMain());
        assertEquals("2016-09-01", list.get(1).getReadingDetails().toString());

    }


    //(String description, String serialNumber, String unit, Apartment apartment, Residence residence, boolean main, Status status, boolean cwu, boolean isWarmWater, Media media
    @Transactional
    @Test
    public void firstReadingForANewMeter() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        final Apartment ap = apartmentService.getById(2L);
        Meter meter = new Meter("test", "346767676", "unit", ap, ap.getResidence(), false, Status.ACTIVE, false, false, Media.WATER);
        meterService.save(meter, Media.WATER);

        final Residence r = residenceService.getById(RESIDENCE_ID);

        List<Reading> list = readingService.getLatestNew(r, Media.WATER);
        assertEquals(8, list.size());
    }

    @Test
    public void getById() {
        Reading reading = readingService.getById(4L);
        assertEquals(LocalDate.parse("2016-07-01"), reading.getReadingDetails().getReadingDate());
        assertEquals(5, reading.getValue(), 0);

    }

    @Test
    public void getPreviousReadings() {
        List<Reading> list = readingService.getPreviousReading(LocalDate.parse("2016-09-01"), meterIdList);
        assertEquals(7, list.size());
        for (Reading readingWater : list) {
            assertEquals("2016-08-01", readingWater.getReadingDetails().getReadingDate().toString());

        }
    }

    @Transactional
    @Test
    public void add() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        List<Residence> residences = getMockedResidences();
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residences);
        final Residence r = residenceService.getById(RESIDENCE_ID);
        List<Meter> list = meterService.getListForOwner(Media.WATER);
        List<Reading> toSave = new ArrayList<>();

        final ReadingDetails details = new ReadingDetails(LocalDate.parse("2050-01-01"), Media.WATER);

        for (Meter meter : list) {
            Reading reading = new Reading(details, 800, r, meter);
            toSave.add(reading);
        }
        readingService.save(toSave, LocalDate.parse("2050-01-01"));


        assertEquals(29, readingService.getList(r, Media.WATER).size());
        assertEquals(LocalDate.parse("2050-01-01"), readingService.getLatestNew(r, Media.WATER).get(0).getReadingDetails().getReadingDate());
    }

}
