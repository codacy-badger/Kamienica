package kamienica.service.reading;

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
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class ReadingEnergyServiceTest extends ServiceTest {

    private static final LocalDate JULY_FIRST = LocalDate.parse("2016-07-01");
    private static final LocalDate FIRST_JULY = JULY_FIRST;
    private static final LocalDate FIRST_AUGUST = LocalDate.parse("2016-09-01");
    private Residence r;

    @Before
    public void initData() {
        r = getOWnersResidence();
    }


    @Test
    public void getLatestNew() throws NoMainCounterException {
        List<Reading> list = readingService.getLatestNew(r, Media.ENERGY);
        assertEquals(5, list.size());
        for (Reading Reading : list) {
            assertEquals(FIRST_AUGUST, Reading.getReadingDetails().getReadingDate());
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
        final Residence r = residenceService.getById(1L);

        List<Reading> readings = readingService.getLatestNew(r, Media.ENERGY);

        assertEquals(4, readings.size());
        for (Reading Reading : readings) {
            assertEquals(FIRST_AUGUST, Reading.getReadingDetails().getReadingDate());
        }
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
            assertNotEquals(FIRST_AUGUST, Reading.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void shouldRetrieviePreviousReadings() {
        List<Meter> meters = meterService.list(r, Media.ENERGY);
        List<Reading> list = readingService.getPreviousReadingForWarmWater(FIRST_AUGUST, meters);

        for (Reading Reading : list) {
            assertEquals(LocalDate.parse("2016-08-01"), Reading.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getPreviousReadings() {
        List<Meter> meters = meterService.list(r, Media.ENERGY);
        List<Reading> list = readingService.getPreviousReadingForWarmWater(LocalDate.parse("2016-08-01"), meters);
        assertEquals(5, list.size());
        for (Reading Reading : list) {
            assertEquals(FIRST_JULY, Reading.getReadingDetails().getReadingDate());

        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getForInvoice() {
        final Invoice i = invoiceService.getByID(3L);
        List<Reading> list = readingService.getForInvoice(i);
        for (Reading Reading : list) {
            assertEquals(i.getReadingDetails().getReadingDate(), Reading.getReadingDetails().getReadingDate());
        }
    }

    @Transactional
    @Test
    public void firstReadingForANewMeter() throws NoMainCounterException {
        final Apartment ap = apartmentService.getById(2L);
        Meter meter = new Meter("test", "newlyAdded", "added", ap, r, false, Status.ACTIVE, false, false, Media.ENERGY);
        meterService.save(meter);
        List<Reading> list = readingService.getLatestNew(ap.getResidence(), Media.ENERGY);
        assertEquals(6, list.size());
    }

    @Test
    public void getById() {
        Reading reading = readingService.getById(42L);
        assertEquals(FIRST_JULY, reading.getReadingDetails().getReadingDate());
        assertEquals(11, reading.getValue(), 0);

    }

    @Test
    public void shouldRetreiveMapOfReadings() {
        Map<ReadingDetails, List<Reading>> result = readingService.list(r, Media.ENERGY);
        assertEquals(3, result.size());
    }

    @Transactional
    @Test
    public void add() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        List<Meter> list = meterService.getListForOwner(Media.ENERGY);
        List<Reading> toSave = new ArrayList<>();
        final LocalDate dateForNewReadings = LocalDate.parse("2050-01-01");
        final ReadingDetails details = new ReadingDetails(dateForNewReadings, Media.ENERGY, r);

        for (Meter meter : list) {
            Reading reading = new Reading(details, 800, r, meter);
            toSave.add(reading);
        }

        readingService.save(toSave, details);
        List<Reading> actual = readingService.getList(r, Media.ENERGY);
        assertEquals(20, actual.size());
        assertEquals(dateForNewReadings, actual.get(0).getReadingDetails().getReadingDate());
    }

    @Transactional
    @Test
    public void update() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        List<Reading> list = readingService.getLatestNew(r, Media.ENERGY);

        for (Reading r : list) {
            r.setValue(6767.0);
        }
        readingService.update(list, TODAY);
        List<Reading> list2 = readingService.getLatestNew(r, Media.ENERGY);
        assertEquals(5, list2.size());
        for (Reading Reading : list2) {
            assertEquals(6767, Reading.getValue(), 0);
        }
    }


}
