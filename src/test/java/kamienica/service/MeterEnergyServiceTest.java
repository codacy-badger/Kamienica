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

public class MeterEnergyServiceTest extends ServiceTest {


    @Test
    public void getListForFirstOwner() {
        List<Residence> residences = getMockedResidences();
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residences);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residences);
        List<Meter> list = meterService.getListForOwner(Media.ENERGY);
        assertEquals(5, list.size());
    }

    @Test
    public void getList() {
        List<Meter> list = meterService.list(Media.ENERGY);
        assertEquals(6, list.size());
    }

    @Transactional
    @Test
    public void getActiveMeters() {
        final Residence r = residenceService.getById(1L);
        assertEquals(5, meterService.getIdListForActiveMeters(r, Media.ENERGY).size());
        Meter meter = meterService.getById(4L);
        meter.setStatus(Status.INACTIVE);
        meterService.update(meter);
        Set<Long> metersId = meterService.getIdListForActiveMeters(r, Media.ENERGY);
        assertEquals(4,metersId.size());

    }

    @Test
    public void getById() {
        Meter meter = meterService.getById(3L);
        assertEquals("E Piwnica", meter.getDescription());
        assertEquals(1, meter.getApartment().getApartmentNumber());

    }

    @Transactional
    @Test
    public void add() {
        Meter meter = createDummyMeter();
        meterService.save(meter);
        Residence r = getOWnersResidence();
        List<Meter> meters = meterService.list(r, Media.ENERGY);
        assertEquals(6, meters.size());
    }

    @Test
    @Transactional
    public void remove() {
        mockStatic(SecurityDetails.class);
        List<Residence> residences = getMockedResidences();
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residences);
        Meter meter = createDummyMeter();
        meterService.save(meter);
        assertEquals(6, meterService.getListForOwner(Media.ENERGY).size());
        meterService.delete(meter.getId());
        assertEquals(5, meterService.getListForOwner(Media.ENERGY).size());

    }

    @Test
    public void ifMainExcists() {
        final boolean result = meterService.ifMainExists(Media.ENERGY);
        assertEquals(true, result);
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
        final Residence r = getOWnersResidence();
        //String description, String serialNumber, String unit, Apartment apartment, Residence residence, boolean main, Status status, boolean cwu, boolean isWarmWater, Media media
        Meter m = new Meter("test", "test", "test", meterService.getById(3L).getApartment(), r, false, Status.ACTIVE, false, false, Media.ENERGY);
        m.setResidence(residenceService.getById(1L));
        return m;
    }

}
