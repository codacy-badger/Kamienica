package kamienica.service.reading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.ReadingForm;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

@WithUserDetails(ServiceTest.OWNER)
public class ReadingWaterServiceTest extends ServiceTest {

    private Residence r;

    @Before
    public void fetchResidence() {
        r = getOwnersResidence();
    }

    @Test
    public void getListForResidence() {
        final List<Reading> list = readingService.getList(r, Media.WATER);
        assertEquals(21, list.size());
    }

    @Test
    @Transactional
    public void shouldDeleteLatestList() {
        readingService.deleteLatestReadings(r, Media.WATER);
        final List<Reading> list = readingService.getList(r, Media.WATER);
        assertEquals(14, list.size());
        for (Reading readingWater : list) {
            assertNotEquals(LocalDate.parse("2016-09-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void shouldRetrieviePreviousReadings() {
        final List<Reading> list = readingService.getPreviousReadingForWarmWater(r, Media.WATER, LocalDate.parse("2016-08-01"));
        for (Reading readingWater : list) {
            assertEquals(LocalDate.parse("2016-07-01"), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getForInvoice() {
        final Invoice i = invoiceService.getByID(1L);
        final List<Reading> list = readingService.getForInvoice(i);
        for (Reading readingWater : list) {
            assertEquals(i.getReadingDetails().getReadingDate(), readingWater.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getById() {
        final Reading reading = readingService.getById(22L);
        assertEquals(LocalDate.parse("2016-07-01"), reading.getReadingDetails().getReadingDate());
        assertEquals(2, reading.getValue(), 0);

    }

    @Test
    public void getPreviousReadings() {
        final List<Reading> list = readingService.getPreviousReadingForWarmWater(r, Media.WATER, LocalDate.parse("2016-09-01"));
        assertEquals(7, list.size());
        for (Reading readingWater : list) {
            assertEquals("2016-08-01", readingWater.getReadingDetails().getReadingDate().toString());

        }
    }

    @Transactional
    @Test
    public void add() {
        final List<Meter> list = meterService.getListForOwner(Media.WATER);
        final List<Reading> toSave = new ArrayList<>();
        final LocalDate dateForNewReadings = LocalDate.parse("2050-01-01");
        final ReadingDetails details = new ReadingDetails(dateForNewReadings, Media.WATER, r);

        for (final Meter meter : list) {
            final Reading reading = new Reading(details, 800, r, meter);
            toSave.add(reading);
        }
        final ReadingForm form = new ReadingForm(toSave);
        readingService.save(form);

        assertEquals(28, readingService.getList(r, Media.WATER).size());
    }
}
