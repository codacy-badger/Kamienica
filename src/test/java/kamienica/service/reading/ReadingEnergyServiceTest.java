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
        List<Reading> list = readingService.getPreviousReadingForWarmWater(r, Media.ENERGY, FIRST_AUGUST);

        for (Reading Reading : list) {
            assertEquals(LocalDate.parse("2016-08-01"), Reading.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getPreviousReadings() {
        List<Reading> list = readingService.getPreviousReadingForWarmWater(r, Media.ENERGY, LocalDate.parse("2016-08-01"));
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

    @Test
    public void getById() {
        Reading reading = readingService.getById(42L);
        assertEquals(FIRST_JULY, reading.getReadingDetails().getReadingDate());
        assertEquals(11, reading.getValue(), 0);

    }

    @Transactional
    @Test
    public void add() {
        List<Meter> list = meterService.getListForOwner(Media.ENERGY);
        List<Reading> toSave = new ArrayList<>();
        final LocalDate dateForNewReadings = LocalDate.parse("2050-01-01");
        final ReadingDetails details = new ReadingDetails(dateForNewReadings, Media.ENERGY, r);

        for (Meter meter : list) {
            Reading reading = new Reading(details, 800, r, meter);
            toSave.add(reading);
        }
        final ReadingForm form = new ReadingForm(toSave);
        readingService.save(form);
        List<Reading> actual = readingService.getList(r, Media.ENERGY);
        assertEquals(20, actual.size());
        assertEquals(dateForNewReadings, actual.get(0).getReadingDetails().getReadingDate());
    }
}
