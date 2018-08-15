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
public class ReadingGasServiceTest extends ServiceTest {

    private static final LocalDate FIRST_OCTOBER = LocalDate.parse("2016-10-01");
    private static final LocalDate TWENTYNINGTH_JULY = LocalDate.parse("2016-07-29");
    private static Residence residence;

    @Before
    public void initData() {
        residence = getOwnersResidence();
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

    @SuppressWarnings("unchecked")
    @Test
    public void getForInvoice() {
        final Invoice i = invoiceService.getByID(2L);
        List<Reading> list =  readingService.getForInvoice(i);
        for (Reading readingGas : list) {
            assertEquals(i.getReadingDetails().getReadingDate(), readingGas.getReadingDetails().getReadingDate());
        }
    }

    @Test
    public void getById() {
        Reading reading = readingService.getById(4L);
        assertEquals(TWENTYNINGTH_JULY, reading.getReadingDetails().getReadingDate());
        assertEquals(3, reading.getValue(), 0);

    }

    @Transactional
    @Test
    public void add() {
        List<Meter> list = meterService.getListForOwner(Media.GAS);
        List<Reading> readingsToSave = new ArrayList<>();
        final ReadingDetails details = new ReadingDetails(LocalDate.parse("2050-01-01"), Media.GAS, residence);

        for (Meter meter : list) {
            Reading reading = new Reading(details, 800, residence, meter);
            readingsToSave.add(reading);
        }

        final ReadingForm form = new ReadingForm(readingsToSave);
        readingService.save(form);
        assertEquals(24, readingService.getList(residence, Media.GAS).size());
    }
}
