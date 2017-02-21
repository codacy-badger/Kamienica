package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Meter;
import kamienica.model.enums.Media;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Status;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void getActiveMeters() {
        final Residence r = residenceService.getById(1L);
        assertEquals(6, meterService.getIdListForActiveMeters(r, Media.GAS).size());
        Meter meter = meterService.getById(4L, Media.GAS);
        meter.setStatus(Status.INACTIVE);
        meterService.update(meter);

        assertEquals(5, meterService.getIdListForActiveMeters(r, Media.GAS).size());

    }
    @Test
    public void ifMainExcists() {
        final boolean result = meterService.ifMainExists(Media.GAS);
        assertEquals(true, result);
    }


    @Test
    @Transactional
    public void delete() {
        meterService.delete(5L, Media.GAS);
        final Meter deleted = meterService.getById(5L, Media.GAS);
        assertEquals(Status.INACTIVE, deleted.getStatus());
    }
}
