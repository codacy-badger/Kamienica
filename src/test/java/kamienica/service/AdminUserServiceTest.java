package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.core.enums.Status;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;
import kamienica.model.Apartment;
import kamienica.model.Tenant;
import kamienica.testutils.EntityProvider;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AdminUserServiceTest extends DatabaseTest {


    private Map<String, Object> map;

    private final int daysFromLastReading = Days.daysBetween(LocalDate.parse("2016-09-01"), LocalDate.now()).getDays();

//    @Before
//    public void loadMainData() {
//        map = adminUserService.getMainData();
//    }

    @Test
    public void emptyApartmentsShouldEqualZero() {

        map = adminUserService.getMainData();
        assertEquals(0, map.get("emptyApartments"));
    }

    @Test
    @Transactional
    public void emptyApartmentsShouldEqualOneAfterInsertingNewApartment() {
        final Apartment ap = new Apartment(7,"1234", "test");
        apartmentService.save(ap);
        map = adminUserService.getMainData();
        assertEquals(1, map.get("emptyApartments"));
    }

    @Test
    @Transactional
    public void emptyApartmentsShouldEqualOneAfterDeactivatingTenant() {
        Tenant t = tenantService.getTenantById(4L);
        t.setStatus(Status.INACTIVE);
        tenantService.saveTenant(t);
        map = adminUserService.getMainData();
        assertEquals(1, map.get("emptyApartments"));
    }

    @Test
    public void getMainDataShouldNotBeNull() {
        map = adminUserService.getMainData();
        assertNotNull(map);
    }

    @Test
    public void mediaShouldPointToWater() {
        map = adminUserService.getMainData();
        assertEquals("Woda", map.get("readingMedia"));
    }

    @Test
    public void shouldCorrectlyCountDaysFromLastReading() {
        map = adminUserService.getMainData();
        assertEquals(daysFromLastReading, map.get("readingDays"));
    }

    @Test
    public void settingShouldBenull() {
        map = adminUserService.getMainData();
        assertNull(map.get("settings"));
    }

    @Test
    public void getListsForTenants() {
        Apartment apartment = apartmentService.getById(2L);
        List<ReadingEnergy> energy = adminUserService.getReadingEnergyForTenant(apartment);
        assertEquals(6, energy.size());
        for (ReadingEnergy readingEnergy : energy) {
            int apNum = readingEnergy.getMeter().getApartment().getApartmentNumber();
            assertTrue(apNum == 0 || apNum == 1);
        }

        List<ReadingGas> gas = adminUserService.getReadingGasForTenant(apartment);
        for (ReadingGas readingGas : gas) {
            int apNum = readingGas.getMeter().getApartment().getApartmentNumber();
            assertTrue(apNum == 0 || apNum == 1);
        }

        List<ReadingWater> water = adminUserService.getReadingWaterForTenant(apartment);
        for (ReadingWater readingWater : water) {
            int apNum = readingWater.getMeter().getApartment().getApartmentNumber();
            assertTrue(apNum == 0 || apNum == 1);
        }

        assertEquals(9, gas.size());
        assertEquals(6, water.size());

    }


}
