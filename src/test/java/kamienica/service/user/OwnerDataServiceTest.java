package kamienica.service.user;

import static org.junit.Assert.assertEquals;

import kamienica.configuration.ServiceTest;
import kamienica.model.entity.OwnerData;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;

@WithUserDetails(ServiceTest.OWNER)
public class OwnerDataServiceTest extends ServiceTest {

    @Test
    public void getMainData(){
        final OwnerData result = dataService.getMainData();
        final LocalDate expectedReadingDate = new LocalDate("2016-09-01");
        final LocalDate expectedInvoiceDate = new LocalDate("2016-08-01");

        assertEquals(1, result.getEmptyApartments());
        assertEquals(1, result.getNumOfResidences());
        assertEquals(expectedInvoiceDate, result.getOldestInvoice().getInvoiceDate());
        assertEquals(expectedReadingDate, result.getOldestReading().getReadingDate());
    }
}