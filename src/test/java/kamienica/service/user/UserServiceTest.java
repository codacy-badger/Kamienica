package kamienica.service.user;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.List;
import java.util.Map;
import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Tenant;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest extends ServiceTest {

    @Before
    public void mockUser() {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
    }
//TODO investigate why gas size was 9
    @Test
    public void getMapOfReadings() {
      final List<Reading> list = userService.getReadings();
      assertEquals(18, list.size());
    }

  @Test
  public void getMapOfPayments() {
    final List<Payment> list = userService.getPayments();
    final Tenant tenant = getOwner();
    for (Payment p : list) {
      assertEquals(tenant.getId(), p.getTenant().getId());
    }
  }

    @Test
    public void getTenantInfo() {
        final Tenant expected = getOwner();
        final Tenant actual = userService.getTenantInfo();
        assertEquals(expected.getId(), actual.getId());

    }

    @Test
    public void getLatestReadingDates() {
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