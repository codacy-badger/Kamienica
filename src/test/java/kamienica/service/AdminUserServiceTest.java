package kamienica.service;

import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;
import kamienica.model.Apartment;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AdminUserServiceTest extends AbstractServiceTest {



    private Map<String, Object> map;

    private final int daysFromLastReading = Days.daysBetween(LocalDate.parse("2016-09-01"), LocalDate.now()).getDays();

    @Before
    public void loadMainData() {
        map = adminUserService.getMainData();
    }

    @Test
    public void emptyApartmentsShouldEqualZero() {
        assertEquals(0, map.get("emptyApartments"));
    }

    @Test
    public void getMainDataShouldNotBeNull() {
        assertNotNull(map);
    }

    @Test
    public void mediaShouldPointToWater() {
        assertEquals("Woda", map.get("readingMedia"));
    }

    @Test
    public void shouldCorrectlyCountDaysFromLastReading() {
        assertEquals(daysFromLastReading, map.get("readingDays"));
    }

    @Test
    @Ignore
    public void settingShouldBenull() {
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
