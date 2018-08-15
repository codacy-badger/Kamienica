package kamienica.service.meter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

@WithUserDetails(ServiceTest.OWNER)
public class MeterEnergyServiceTest extends ServiceTest {

    @Test
    public void getListForFirstOwner() {
        final List<Meter> list = meterService.getListForOwner(Media.ENERGY);
        assertEquals(5, list.size());
    }

    @Test
    public void getList() {
        final List<Meter> list = meterService.list(Media.ENERGY);
        assertEquals(6, list.size());
    }

    @Transactional
    @Test
    public void getActiveMeters() {
        final Residence r = residenceService.getById(1L);
        assertEquals(5, meterService.getIdListForActiveMeters(r, Media.ENERGY).size());
        final Meter meter = meterService.getById(4L);
        meter.setStatus(Status.INACTIVE);
        meterService.update(meter);
        final Set<Long> metersId = meterService.getIdListForActiveMeters(r, Media.ENERGY);
        assertEquals(4, metersId.size());

    }

    @Test
    public void getById() {
        final Meter meter = meterService.getById(3L);
        assertEquals("E Piwnica", meter.getDescription());
        assertEquals(1, meter.getApartment().getApartmentNumber());

    }

    @Transactional
    @Test
    public void add() {
        final Meter meter = createDummyMeter();
        meterService.save(meter);
        final Residence r = getOwnersResidence();
        final List<Meter> meters = meterService.list(r, Media.ENERGY);
        assertEquals(6, meters.size());
    }

    @Test
    @Transactional
    public void remove() {
        final Meter meter = createDummyMeter();
        meterService.save(meter);
        assertEquals(6, meterService.getListForOwner(Media.ENERGY).size());
        meterService.delete(meter);
        assertEquals(5, meterService.getListForOwner(Media.ENERGY).size());

    }

    @Test
    public void ifMainExcists() {
        final boolean result = meterService.ifMainExists(Media.ENERGY);
        assertTrue(result);
    }

    @Test
    @Transactional
    public void delete() {
        meterService.delete(5L);
        final Meter deleted = meterService.getById(5L);
        assertEquals(Status.INACTIVE, deleted.getStatus());
    }

    @Test
    public void update() {
        Meter meter = meterService.getById(4L);
        meter.setDescription("uPdate");
        meterService.update(meter);
        meter = meterService.getById(4L);
        assertEquals("uPdate", meter.getDescription());
    }

    private Meter createDummyMeter() {
        final Residence r = getOwnersResidence();
        //String description, String serialNumber, String unit, Apartment apartment, Residence residence, boolean main, Status status, boolean cwu, boolean isWarmWater, Media media
        final Meter m = new Meter("test", "test", "test", meterService.getById(3L).getApartment(), r, false, Status.ACTIVE, false, false, Media.ENERGY);
        m.setResidence(residenceService.getById(1L));
        return m;
    }

}
