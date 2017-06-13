package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class MeterGasServiceTest extends ServiceTest {

    @Test
    public void getListForOwner() {
        List<Residence> residenceList = getMockedResidences();
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residenceList);
        assertEquals(6, meterService.getListForOwner(Media.GAS).size());

    }

    @Test
    public void getList() {
        List<Meter> list = meterService.list(Media.GAS);
        assertEquals(7, list.size());
    }

    @Transactional
    @Test
    public void deactivateMeter() {
        final Residence r = residenceService.getById(1L);
        final Set<Long> meters = meterService.getIdListForActiveMeters(r, Media.GAS);
        assertEquals(6, meters.size());
        Meter meter = meterService.getById(17L);
        meter.setStatus(Status.INACTIVE);
        meterService.update(meter);
        final Set<Long> metersId = meterService.getIdListForActiveMeters(r, Media.GAS);
        assertEquals(5, metersId.size());

    }

    @Test
    public void ifMainExcists() {
        final boolean result = meterService.ifMainExists(Media.GAS);
        assertEquals(true, result);
    }


    @Test
    @Transactional
    public void delete() {
        meterService.delete(5L);
        final Meter deleted = meterService.getById(5L);
        assertEquals(Status.INACTIVE, deleted.getStatus());
    }
}
