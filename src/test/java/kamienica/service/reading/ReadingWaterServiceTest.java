package kamienica.service.reading;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
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
        List<Reading> list = readingService.getPreviousReadingForWarmWater(r, Media.WATER, LocalDate.parse("2016-08-01"));
        for (Reading readingWater : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getForInvoice() {
        final Invoice i = invoiceService.getByID(1L);
        List<Reading> list = readingService.getForInvoice(i);
        for (Reading readingWater : list) {
            assertEquals(i.getReadingDetails().getReadingDate(), readingWater.getReadingDetails().getReadingDate());
        }
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
        List<Reading> list = readingService.getPreviousReadingForWarmWater(r, Media.WATER, LocalDate.parse("2016-09-01"));
        assertEquals(7, list.size());
        for (Reading readingWater : list) {
            assertEquals("2016-08-01", readingWater.getReadingDetails().getReadingDate().toString());

        }
    }

    @Transactional
    @Test
    public void add() {
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
        final ReadingForm form = new ReadingForm(details, toSave);
        readingService.save(form);


        assertEquals(28, readingService.getList(r, Media.WATER).size());
    }
}
