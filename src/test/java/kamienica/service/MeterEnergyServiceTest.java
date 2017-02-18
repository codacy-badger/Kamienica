package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.enums.Media;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Residence;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<MeterEnergy> list = meterService.getListForOwner(Media.ENERGY);
        assertEquals(5, list.size());
    }

    @Test
    public void getList() {
        List<MeterEnergy> list = meterService.list(Media.ENERGY);
        assertEquals(6, list.size());
    }

    @Transactional
    @Test
    public void getActiveMeters() {
        final Residence r = residenceService.getById(1L);
        assertEquals(5, meterService.getIdListForActiveMeters(r, Media.ENERGY).size());
        MeterEnergy meter = meterService.getById(4L, Media.ENERGY);
        meter.setDeactivation(LocalDate.now().minusDays(1));
        meterService.update(meter, Media.ENERGY);

        assertEquals(4, meterService.getIdListForActiveMeters(r, Media.ENERGY).size());

    }

    @Test
    public void getById() {
        MeterEnergy meter = meterService.getById(3L, Media.ENERGY);
        assertEquals("Piwnica", meter.getDescription());
        assertEquals(1, meter.getApartment().getApartmentNumber());

    }

    @Transactional
    @Test
    public void add() {
        MeterEnergy meter = createDummyMeter();
        meterService.save(meter, Media.ENERGY);
        assertEquals(7, meterService.getIdList(Media.ENERGY).size());
    }

    @Test
    @Transactional
    public void remove() {
        mockStatic(SecurityDetails.class);
        List<Residence> residences = getMockedResidences();
        when(SecurityDetails.getResidencesForOwner()).thenReturn(residences);
        MeterEnergy meter = createDummyMeter();
        meterService.save(meter, Media.ENERGY);
        assertEquals(6, meterService.getListForOwner(Media.ENERGY).size());
        meterService.delete(6L, Media.ENERGY);
        meterService.delete(7L, Media.ENERGY);
        meterService.delete(8L, Media.ENERGY);
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
        meterService.delete(5L, Media.ENERGY);
        final MeterEnergy deleted = meterService.getById(5L, Media.ENERGY);
        assertEquals(TODAY, deleted.getDeactivation());
    }

    @Test
    public void update() {
        MeterEnergy meter = meterService.getById(4L, Media.ENERGY);
        meter.setDescription("uPdate");
        meterService.update(meter, Media.ENERGY);
        meter = meterService.getById(4L, Media.ENERGY);
        assertEquals("uPdate", meter.getDescription());
    }

    private MeterEnergy createDummyMeter() {
        MeterEnergy m =  new MeterEnergy("test", "test", "test", meterService.getById(3L, Media.ENERGY).getApartment());
        m.setResidence(residenceService.getById(1L));
        return m;
    }

}
