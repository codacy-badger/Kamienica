package kamienica.service.meter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

@WithUserDetails(ServiceTest.OWNER)
public class MeterWaterServiceTest extends ServiceTest {

    @Test
    public void getListForOwner() {
        final List<Meter> list = meterService.getListForOwner(Media.WATER);
        assertEquals(7, list.size());
    }

    @Test
    public void getList() {
        final List<Meter> list = meterService.list(Media.WATER);
        assertEquals(8, list.size());
    }

    @Test
    public void ifMainExcists() {
        final boolean result = meterService.ifMainExists(Media.WATER);
        assertTrue(result);
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
