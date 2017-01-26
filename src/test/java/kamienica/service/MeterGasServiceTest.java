package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.enums.Media;
import kamienica.core.util.SecurityDetails;
import kamienica.model.MeterGas;
import kamienica.model.Residence;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class MeterGasServiceTest extends ServiceTest {

    @Test
    public void getList() {
        List<Residence> residenceList = getMockedResidences();
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residenceList);
        assertEquals(6, meterService.getListForOwner(Media.GAS).size());

    }

    @Transactional
    @Test
    public void getActiveMeters() {
        assertEquals(6, meterService.getIdListForActiveMeters(Media.GAS).size());
        MeterGas meter = meterService.getById(4L, Media.GAS);
        meter.setDeactivation(LocalDate.now().minusDays(1));
        meterService.update(meter, Media.GAS);

        assertEquals(5, meterService.getIdListForActiveMeters(Media.GAS).size());

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
        final MeterGas deleted = meterService.getById(5L, Media.GAS);
        assertEquals(TODAY, deleted.getDeactivation());
    }
}
