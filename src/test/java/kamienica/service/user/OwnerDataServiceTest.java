package kamienica.service.user;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.OwnerData;
import kamienica.model.entity.Residence;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class OwnerDataServiceTest extends ServiceTest {

    @Test
    public void getMainData(){
        List<Residence> residences = Collections.singletonList(getOWnersResidence());
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residences);

        final OwnerData result = dataService.getMainData();
        final LocalDate expectedReadingDate = new LocalDate("2016-09-01");
        final LocalDate expectedInvoiceDate = new LocalDate("2016-08-01");

        assertEquals(1, result.getEmptyApartments());
        assertEquals(1, result.getNumOrResidences());
        assertEquals(expectedInvoiceDate, result.getOldestInvoice().getInvoiceDate());
        assertEquals(expectedReadingDate, result.getOldestReading().getReadingDate());
    }
}