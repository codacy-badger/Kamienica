package kamienica.service;

import kamienica.core.util.CommonUtils;
import kamienica.feature.user_admin.AdminUserService;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by macfol on 12/4/16.
 */
public class AdminUserServiceImplTest extends AbstractServiceTest {

    private final LocalDate oldestReading = LocalDate.parse("2016-09-01");

    @Autowired
    private AdminUserService service;

    @Test
    public void getMainData() throws Exception {
        Map<String, Object> result = service.getMainData();
        final int invoiceDays = (int) result.get("invoiceDays");
        final int readingDays = (int) result.get("readingDays");
        final int emptyApartments = (int) result.get("emptyApartments");
        final String readingMedia = (String) result.get("readingMedia");
        final String invoiceMedia = (String) result.get("invoiceMedia");

        assertEquals(0 , emptyApartments);
        assertEquals("Woda" , readingMedia);
        assertEquals("Gaz" , invoiceMedia);

        final int dayCount = CommonUtils.countDaysBetween(oldestReading, new LocalDate());
        assertEquals(dayCount , readingDays);
        assertEquals(9999, invoiceDays);
        System.out.println(result);
    }

    @Test
    public void getReadingEnergyForTenant() throws Exception {

    }

    @Test
    public void getReadingGasForTenant() throws Exception {

    }

    @Test
    public void getReadingWaterForTenant() throws Exception {

    }

    @Test
    public void getReadingsForTenant() throws Exception {

    }

    @Test
    public void getCurrentUser() throws Exception {

    }

}