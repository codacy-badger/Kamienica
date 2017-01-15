package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.core.enums.Media;
import kamienica.model.MeterEnergy;
import kamienica.model.MeterWater;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MeterWaterServiceTest extends DatabaseTest {

    @Test
    public void getList() {
        List<MeterWater> list = meterService.getList(Media.WATER);

        assertEquals(7, list.size());

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
        assertEquals(7, meterService.getIdListForActiveMeters(Media.WATER).size());
        MeterWater meter = meterService.getById(4L, Media.WATER);
        meter.setDeactivation(LocalDate.now().minusDays(1));
        meterService.update(meter, Media.WATER);

        assertEquals(6, meterService.getIdListForActiveMeters(Media.WATER).size());

    }
}
