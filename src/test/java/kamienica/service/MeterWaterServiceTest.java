package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class MeterWaterServiceTest extends ServiceTest {

    @Test
    public void getListForOwner() {
        Tenant t = getOwner();
        List<Residence> residences = getMockedResidences();

        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(t);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residences);

        List<Meter> list = meterService.getListForOwner(Media.WATER);
        assertEquals(7, list.size());

    }

    @Test
    public void getList() {
        List<Meter> list = meterService.list(Media.WATER);
        assertEquals(8, list.size());
    }

    @Test
    public void ifMainExcists() {
        final boolean result = meterService.ifMainExists(Media.WATER);
        assertEquals(true, result);
    }

    @Test
    @Transactional
    public void delete() {
        meterService.delete(5L);
        final Meter deleted = meterService.getById(5L);
        assertEquals(Status.INACTIVE, deleted.getStatus());
    }

    @Transactional
    @Test
    public void getActiveMeters() {
        final Residence r = residenceService.getById(1L);
        assertEquals(7, meterService.getIdListForActiveMeters(r, Media.WATER).size());
        Meter meter = meterService.getById(10L);
        meter.setStatus(Status.INACTIVE);
        meterService.update(meter);

        assertEquals(6, meterService.getIdListForActiveMeters(r, Media.WATER).size());

    }
}
