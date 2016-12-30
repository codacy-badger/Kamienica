package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.core.enums.Media;
import kamienica.model.MeterEnergy;
import kamienica.model.MeterGas;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

public class MeterGasServiceTest extends DatabaseTest {

    @Test
    public void getList() {
        assertEquals(6, meterService.getList(Media.GAS).size());

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
