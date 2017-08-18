package kamienica.service.user;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Tenant;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class UserServiceTest extends ServiceTest {

    @Before
    public void mockUser() {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
    }

    @Test
    public void getMapOfReadings() throws Exception {
        Map<String, List<Reading>> map = userService.getMapOfReadings();
        assertEquals(3, map.size());

        List<Reading> energy = map.get("ENERGY");
        List<Reading> gas = map.get("GAS");
        List<Reading> water = map.get("WATER");

        assertEquals(6, energy.size());
        assertEquals(9, gas.size());
        assertEquals(6, water.size());
    }

    @Test
    public void getMapOfPayments() throws Exception {
        Map<String, List<Payment>> map = userService.getMapOfPayments();
        assertEquals(3, map.size());

        List<Payment> energy = map.get("ENERGY");
        List<Payment> gas = map.get("GAS");
        List<Payment> water = map.get("WATER");

        final Tenant tenant = getOwner();
        for (Payment p : energy) {
            assertEquals(tenant.getId(), p.getTenant().getId());
        }
        for (Payment p : gas) {
            assertEquals(tenant.getId(), p.getTenant().getId());
        }
        for (Payment p : water) {
            assertEquals(tenant.getId(), p.getTenant().getId());
        }
    }

    @Test
    public void getTenantInfo() throws Exception {
        final Tenant expected = getOwner();
        final Tenant actual = userService.getTenantInfo();
        assertEquals(expected.getId(), actual.getId());

    }

    @Test
    public void getLatestReadingDates() throws Exception {
        when(SecurityDetails.getApartmentForLoggedTenant()).thenReturn(apartmentService.getById(2L));
        final Map<String, ReadingDetails> map = userService.getLatestReadingDates();
        assertEquals(3, map.size());

        final ReadingDetails energy = map.get("ENERGY");
        final ReadingDetails gas = map.get("GAS");
        final ReadingDetails water = map.get("WATER");
        assertEquals(LocalDate.parse("2016-09-01"), energy.getReadingDate());
        assertEquals(LocalDate.parse("2016-10-01"), gas.getReadingDate());
        assertEquals(LocalDate.parse("2016-09-01"), water.getReadingDate());
    }

}