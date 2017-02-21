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

public class ReadingGasServiceTest extends ServiceTest {

    private static final LocalDate PREVIOUS_DATE = LocalDate.parse("2016-08-01");
    private Set<Long> meterIdList = new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L));
    private static Residence residence;

    @Before
    public void initData() {
        residence = residenceService.getById(RESIDENCE_ID);
    }

    @Test
    public void getLatest() throws NoMainCounterException {
        List<Reading> list = readingService.getLatestNew(residence, Media.GAS);
        assertEquals(6, list.size());
        for (Reading readingGas : list) {
            assertEquals(LocalDate.parse("2016-10-01"), readingGas.getReadingDetails().getReadingDate());
        }
    }

    @Transactional
    @Test
    public void getLatestActiveOnly() throws NoMainCounterException {
        Meter meter = meterService.getById(3L, Media.GAS);
        meter.setStatus(Status.INACTIVE);
        meterService.update(meter);

        List<Reading> list2 = readingService.getLatestNew(residence, Media.GAS);
        assertEquals(5, list2.size());
        for (Reading readingGas : list2) {
            assertEquals(LocalDate.parse("2016-10-01"), readingGas.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getListForResidence() {
        List<Reading> list = (List<Reading>) readingService.getList(residence, Media.GAS);
        assertEquals(18, list.size());
    }

    @Test
    public void getList() {
        assertEquals(19, readingService.getList(residence, Media.GAS).size());
    }

    @Test
    @Transactional
    public void shouldDeleteLatestList() {
        readingService.deleteLatestReadings(residence, Media.GAS);
        List<? extends Reading> list = readingService.getList(residence, Media.GAS);
        assertEquals(12, list.size());
        for (Reading readingGas : list) {
            assertNotEquals(LocalDate.parse("2016-10-01"), readingGas.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void shouldRetrieviePreviousReadings() {
        List<Reading> list = readingService.getPreviousReading(PREVIOUS_DATE, meterIdList);

        for (Reading readingGas : list) {
            assertEquals(LocalDate.parse("2016-07-29"), readingGas.getReadingDetails().getReadingDate());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getByDate() {
        final Residence r = residenceService.getById(RESIDENCE_ID);
        List<Reading> list =  readingService.getByDate(r, LocalDate.parse("2016-07-01"), Media.GAS);
        for (Reading readingGas : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingGas.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getUnresolved() {
        List<Reading> list = readingService.getUnresolvedReadings(Media.GAS, residence);
        assertEquals(2, list.size());
        assertEquals("2016-07-29", list.get(0).getReadingDetails().getReadingDate().toString());
        assertEquals(true, list.get(0).getMeter().isMain());
        assertEquals("2016-10-01", list.get(1).getReadingDetails().getReadingDate().toString());

    }

    @Transactional
    @Test
    public void firstReadingForANewMeter() throws NoMainCounterException {
        final Apartment ap = apartmentService.getById(2L);
        Meter meter = new Meter("test", "34", "3535", ap, Media.GAS);
        meterService.save(meter, Media.GAS);
        List<Reading> list = readingService.getLatestNew(residence, Media.GAS);
        assertEquals(7, list.size());
    }

    @Test
    public void getById() {
        Reading reading = readingService.getById(4L);
        assertEquals(LocalDate.parse("2016-07-29"), reading.getReadingDetails().getReadingDate());
        assertEquals(3, reading.getValue(), 0);

    }

    @Test
    public void getPreviousReadings() {
        List<Reading> list = readingService.getPreviousReading(LocalDate.parse("2016-10-01"), meterIdList);
        assertEquals(6, list.size());
        for (Reading readingGas : list) {
            assertEquals("2016-09-01", readingGas.getReadingDetails().getReadingDate().toString());

        }
    }

    @Transactional
    @Test
    public void add() throws NoMainCounterException {
//        mockStatic(SecurityDetails.class);
//        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        List<Meter> list = meterService.getListForOwner(Media.GAS);
        List<Reading> toSave = new ArrayList<>();

        final ReadingDetails details = new ReadingDetails(LocalDate.parse("2050-01-01"), Media.GAS);

        for (Meter meter : list) {
            Reading reading = new Reading(details, 800, residence, meter);
            toSave.add(reading);
        }
        final Residence r = residenceService.getById(RESIDENCE_ID);
        readingService.save(toSave, LocalDate.parse("2050-01-01"));
        assertEquals(25, readingService.getList(residence, Media.GAS).size());
        assertEquals(LocalDate.parse("2050-01-01"), readingService.getLatestNew(r, Media.GAS).get(0).getReadingDetails().getReadingDate());
    }

}
