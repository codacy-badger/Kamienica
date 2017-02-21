package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import kamienica.model.exception.NoMainCounterException;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class ReadingEnergyServiceTest extends ServiceTest {

    private Set<Long> meterIdList = new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L));
    private Residence r;

    @Before
    public void initData() {
        r = residenceService.getById(RESIDENCE_ID);
    }


    @Test
    public void getLatest() throws NoMainCounterException {
        final Residence r = residenceService.getById(RESIDENCE_ID);
        List<Reading> list = readingService.getLatestNew(r, Media.ENERGY);

        assertEquals(5, list.size());
        for (Reading Reading : list) {
            assertEquals(LocalDate.parse("2016-09-01"), Reading.getReadingDetails().getReadingDate());
        }
    }

    @Transactional
    @Test
    public void getLatestActiveOnly() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        Meter meter = meterService.getById(3L, Media.ENERGY);
        meter.setStatus(Status.INACTIVE);
        meterService.update(meter);
        final Residence r = residenceService.getById(1L);

        List<Reading> list2 = readingService.getLatestNew(r, Media.ENERGY);

        assertEquals(4, list2.size());
        for (Reading Reading : list2) {
            assertEquals(LocalDate.parse("2016-09-01"), Reading.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getList() {
        assertEquals(16, readingService.getList(r, Media.ENERGY).size());
    }

    @Test
    public void getListForResidence() {


        List<Reading> result = readingService.getList(r, Media.ENERGY);
        assertEquals(15, result.size());
    }

    @Test
    @Transactional
    public void shouldDeleteLatestList() {
        readingService.deleteLatestReadings(r, Media.ENERGY);
        List<Reading> list = readingService.getList(r, Media.ENERGY);
        assertEquals(10, list.size());
        for (Reading Reading : list) {
            assertNotEquals(LocalDate.parse("2016-09-01"), Reading.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void shouldRetrieviePreviousReadings() {
        List<Reading> list = readingService.getPreviousReading(LocalDate.parse("2016-09-01"), meterIdList);

        for (Reading Reading : list) {
            assertEquals(LocalDate.parse("2016-08-01"), Reading.getReadingDetails().getReadingDate());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getByDate() {
        List<Reading> list = readingService.getByDate(r, LocalDate.parse("2016-07-01"), Media.ENERGY);
        for (Reading Reading : list) {
            assertEquals(LocalDate.parse("2016-07-01"), Reading.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getUnresolved() {
        List<Reading> list = readingService.getUnresolvedReadings(Media.ENERGY, r);
        assertEquals(2, list.size());
        assertEquals("2016-07-01", list.get(0).getReadingDetails().getReadingDate().toString());
        assertEquals(true, list.get(0).getMeter().isMain());
        assertEquals("2016-09-01", list.get(1).getReadingDetails().getReadingDate().toString());

    }

    @Transactional
    @Test
    public void firstReadingForANewMeter() throws NoMainCounterException {
        final Apartment ap = apartmentService.getById(2L);
//        String description, String serialNumber, String unit, Apartment apartment, Residence residence, boolean main, Status status, boolean cwu, boolean isWarmWater, Media media
        Meter meter = new Meter("test", "newlyAdded", "added", ap, r, false, Status.ACTIVE, false, false, Media.ENERGY);
        meterService.save(meter, Media.ENERGY);
        List<Reading> list = readingService.getLatestNew(ap.getResidence(), Media.ENERGY);
        assertEquals(6, list.size());
    }

    @Test
    public void getById() {
        Reading reading = readingService.getById(4L);
        assertEquals(LocalDate.parse("2016-07-01"), reading.getReadingDetails().getReadingDate());
        assertEquals(2, reading.getValue(), 0);

    }

    @Test
    public void getPreviousReadings() {
        List<Reading> list = readingService.getPreviousReading(LocalDate.parse("2016-08-01"), meterIdList);
        assertEquals(5, list.size());
        for (Reading Reading : list) {
            assertEquals("2016-07-01", Reading.getReadingDetails().getReadingDate().toString());

        }
    }

    @Transactional
    @Test
    public void add() throws NoMainCounterException {
        final Residence r = residenceService.getById(1L);
//        mockStatic(SecurityDetails.class);
//        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        List<Meter> list = meterService.getListForOwner(Media.ENERGY);
        List<Reading> toSave = new ArrayList<>();

        final ReadingDetails details = new ReadingDetails(LocalDate.parse("2050-01-01"), Media.ENERGY);

        for (Meter meter : list) {
            Reading reading = new Reading(details, 800, r, meter);
            toSave.add(reading);
        }


        readingService.save(toSave, LocalDate.parse("2050-01-01"));
        assertEquals(21, readingService.getList(r, Media.ENERGY).size());
        assertEquals(LocalDate.parse("2050-01-01"), readingService.getLatestNew(r, Media.ENERGY).get(0).getReadingDetails().getReadingDate());
    }

    @Transactional
    @Test
    public void update() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        final Residence r = residenceService.getById(1L);
        List<Reading> list = readingService.getLatestNew(r, Media.ENERGY);

        for (Reading aList : list) {
            aList.setValue(6767.0);
        }
        readingService.update(list, TODAY);
        List<Reading> list2 = readingService.getLatestNew(r, Media.ENERGY);
        assertEquals(5, list2.size());
        for (Reading Reading : list2) {
            assertEquals(6767, Reading.getValue(), 0);
        }
    }


}
