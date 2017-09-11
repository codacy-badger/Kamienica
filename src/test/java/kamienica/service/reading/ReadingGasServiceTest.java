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

public class ReadingGasServiceTest extends ServiceTest {

    private static final LocalDate PREVIOUS_DATE = LocalDate.parse("2016-08-01");
    private static final LocalDate FIRST_OCTOBER = LocalDate.parse("2016-10-01");
    private static final LocalDate FIRST_SEMPTEMBER = LocalDate.parse("2016-09-01");
    private static final LocalDate TWENTYNINGTH_JULY = LocalDate.parse("2016-07-29");
    private static Residence residence;

    @Before
    public void initData() {
        residence =getOWnersResidence();
    }

    @Test
    public void getLatest() throws NoMainCounterException {
        List<Reading> list = readingService.getLatestNew(residence, Media.GAS);
        assertEquals(6, list.size());
        for (Reading readingGas : list) {
            assertEquals(FIRST_OCTOBER, readingGas.getReadingDetails().getReadingDate());
        }
    }

    @Transactional
    @Test
    public void getLatestActiveOnly() throws NoMainCounterException {
        Meter meter = meterService.getById(3L);
        meter.setStatus(Status.INACTIVE);
        meterService.update(meter);

        List<Reading> readings = readingService.getLatestNew(residence, Media.GAS);
        assertEquals(6, readings.size());
        for (Reading readingGas : readings) {
            assertEquals(FIRST_OCTOBER, readingGas.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getListForResidence() {
        List<Reading> list = readingService.getList(residence, Media.GAS);
        assertEquals(18, list.size());
    }

    @Test
    @Transactional
    public void shouldDeleteLatestList() {
        readingService.deleteLatestReadings(residence, Media.GAS);
        List<? extends Reading> list = readingService.getList(residence, Media.GAS);
        assertEquals(12, list.size());
        for (Reading readingGas : list) {
            assertNotEquals(FIRST_OCTOBER, readingGas.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void shouldRetrieviePreviousReadings() {
        List<Meter> meters = meterService.list(residence, Media.GAS);
        List<Reading> list = readingService.getPreviousReadingForWarmWater(PREVIOUS_DATE, meters);

        for (Reading readingGas : list) {
            assertEquals(TWENTYNINGTH_JULY, readingGas.getReadingDetails().getReadingDate());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getForInvoice() {
        final Invoice i = invoiceService.getByID(2L);
        List<Reading> list =  readingService.getForInvoice(i);
        for (Reading readingGas : list) {
            assertEquals(i.getReadingDetails().getReadingDate(), readingGas.getReadingDetails().getReadingDate());
        }
    }

    @Transactional
    @Test
    public void firstReadingForANewMeter() throws NoMainCounterException {
        final Apartment ap = apartmentService.getById(2L);
        Meter meter = new Meter("test", "34", "3535", ap, Media.GAS);
        meterService.save(meter);
        List<Reading> list = readingService.getLatestNew(residence, Media.GAS);
        assertEquals(7, list.size());
    }

    @Test
    public void getById() {
        Reading reading = readingService.getById(4L);
        assertEquals(TWENTYNINGTH_JULY, reading.getReadingDetails().getReadingDate());
        assertEquals(3, reading.getValue(), 0);

    }

    @Test
    public void getPreviousReadings() {
        List<Meter> meters = meterService.list(residence, Media.GAS);
        List<Reading> list = readingService.getPreviousReadingForWarmWater(FIRST_OCTOBER, meters);
        assertEquals(6, list.size());
        for (Reading readingGas : list) {
            assertEquals("2016-09-01", readingGas.getReadingDetails().getReadingDate().toString());

        }
    }

    @Transactional
    @Test
    public void add() throws NoMainCounterException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        List<Meter> list = meterService.getListForOwner(Media.GAS);
        List<Reading> readingsToSave = new ArrayList<>();
        final ReadingDetails details = new ReadingDetails(LocalDate.parse("2050-01-01"), Media.GAS, residence);

        for (Meter meter : list) {
            Reading reading = new Reading(details, 800, residence, meter);
            readingsToSave.add(reading);
        }

        readingService.save(readingsToSave, details);
        assertEquals(24, readingService.getList(residence, Media.GAS).size());
        List<Reading> result = readingService.getLatestNew(residence, Media.GAS);
        assertEquals(LocalDate.parse("2050-01-01"), result.get(0).getReadingDetails().getReadingDate());
    }

    @Test
    public void shouldRetreiveMapOfReadings() {
        Map<ReadingDetails, List<Reading>> result = readingService.list(residence, Media.GAS);
        assertEquals(3, result.size());
    }
}
