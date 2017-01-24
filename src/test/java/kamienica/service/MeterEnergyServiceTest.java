package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.core.enums.Media;
import kamienica.model.MeterEnergy;
import kamienica.model.Tenant;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MeterEnergyServiceTest extends DatabaseTest {

    ;

    @Test
    public void getList() {
        final Tenant t = tenantService.getTenantById(1L);
        List<MeterEnergy> list = meterService.getListForOwner(Media.ENERGY, t);
        assertEquals(5, list.size());

    }

    @Transactional
    @Test
    public void getActiveMeters() {
        assertEquals(5, meterService.getIdListForActiveMeters(Media.ENERGY).size());
        MeterEnergy meter = meterService.getById(4L, Media.ENERGY);
        meter.setDeactivation(LocalDate.now().minusDays(1));
        meterService.update(meter, Media.ENERGY);

        assertEquals(4, meterService.getIdListForActiveMeters(Media.ENERGY).size());

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
        assertEquals(6, meterService.getIdList(Media.ENERGY).size());
    }

    @Test
    @Transactional
    public void remove() {
        final Tenant t = tenantService.getTenantById(1L);
        MeterEnergy meter = createDummyMeter();
        meterService.save(meter, Media.ENERGY);
        assertEquals(6, meterService.getListForOwner(Media.ENERGY, t).size());
        meterService.delete(6L, Media.ENERGY);
        meterService.delete(7L, Media.ENERGY);
        meterService.delete(8L, Media.ENERGY);
        assertEquals(5, meterService.getListForOwner(Media.ENERGY, t).size());

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
