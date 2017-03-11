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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class ReadingWaterServiceTest extends ServiceTest {

    private Residence r;

    @Before
    public void fetchResidence() {
        r = getOWnersResidence();
    }

    @Test
    public void getLatest() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

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
        Meter meter = meterService.getById(3L);
        meter.setStatus(Status.INACTIVE);
        meterService.update(meter);

        List<Reading> readings = readingService.getLatestNew(r, Media.WATER);

        assertEquals(7, readings.size());
        for (Reading readingWater : readings) {
            assertEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getListForResidence() {
        List<Reading> list = readingService.getList(r, Media.WATER);
        assertEquals(21, list.size());
    }

    @Test
    @Transactional
    public void shouldDeleteLatestList() {
        readingService.deleteLatestReadings(r, Media.WATER);
        List<Reading> list = readingService.getList(r, Media.WATER);
        assertEquals(14, list.size());
        for (Reading readingWater : list) {
            assertNotEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void shouldRetrieviePreviousReadings() {
        List<Meter> meters = meterService.list(r, Media.WATER);
        List<Reading> list = readingService.getPreviousReading(LocalDate.parse("2016-08-01"), meters);
        for (Reading readingWater : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getByDate() {

        List<Reading> list = readingService.getByDate(r, LocalDate.parse("2016-07-01"), Media.WATER);
        for (Reading readingWater : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @Transactional
    @Test
    public void firstReadingForANewMeter() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        final Apartment ap = apartmentService.getById(2L);
        Meter meter = new Meter("test", "346767676", "unit", ap, ap.getResidence(), false, Status.ACTIVE, false, false, Media.WATER);
        meterService.save(meter);

        List<Reading> list = readingService.getLatestNew(r, Media.WATER);
        assertEquals(8, list.size());
    }

    @Test
    public void getById() {
        Reading reading = readingService.getById(22L);
        assertEquals(LocalDate.parse("2016-07-01"), reading.getReadingDetails().getReadingDate());
        assertEquals(2, reading.getValue(), 0);

    }

    @Test
    public void getPreviousReadings() {
        List<Meter> meters = meterService.list(r, Media.WATER);
        List<Reading> list = readingService.getPreviousReading(LocalDate.parse("2016-09-01"), meters);
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
        List<Meter> list = meterService.getListForOwner(Media.WATER);
        List<Reading> toSave = new ArrayList<>();
        final LocalDate dateForNewReadings = LocalDate.parse("2050-01-01");
        final ReadingDetails details = new ReadingDetails(dateForNewReadings, Media.WATER, r);

        for (Meter meter : list) {
            Reading reading = new Reading(details, 800, r, meter);
            toSave.add(reading);
        }
        readingService.save(toSave, details);


        assertEquals(28, readingService.getList(r, Media.WATER).size());
        assertEquals(dateForNewReadings, readingService.getLatestNew(r, Media.WATER).get(0).getReadingDetails().getReadingDate());
    }

}
