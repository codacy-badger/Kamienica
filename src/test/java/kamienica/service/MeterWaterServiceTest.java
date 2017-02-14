package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.enums.Media;
import kamienica.core.util.SecurityDetails;
import kamienica.model.MeterWater;
import kamienica.model.Residence;
import kamienica.model.Tenant;
import org.joda.time.LocalDate;
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

        List<MeterWater> list = meterService.getListForOwner(Media.WATER);
        assertEquals(7, list.size());

    }

    @Test
    public void getList() {
        List<MeterWater> list = meterService.list(Media.WATER);
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
        meterService.delete(5L, Media.WATER);
        final MeterWater deleted = meterService.getById(5L, Media.WATER);
        assertEquals(TODAY, deleted.getDeactivation());
    }

    @Transactional
    @Test
    public void getActiveMeters() {
        final Residence r = residenceService.getById(1L);
        assertEquals(7, meterService.getIdListForActiveMeters(r, Media.WATER).size());
        MeterWater meter = meterService.getById(4L, Media.WATER);
        meter.setDeactivation(LocalDate.now().minusDays(1));
        meterService.update(meter, Media.WATER);

        assertEquals(6, meterService.getIdListForActiveMeters(r, Media.WATER).size());

    }
}
