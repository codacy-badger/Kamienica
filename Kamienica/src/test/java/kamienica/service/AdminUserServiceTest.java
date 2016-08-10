package kamienica.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;
import kamienica.feature.user_admin.AdminUserService;

public class AdminUserServiceTest extends AbstractServiceTest {

	@Autowired
	AdminUserService service;
	@Autowired
	ApartmentService apService;

	@Test
	@Ignore
	public void getMainData() {
		int days = Days.daysBetween(LocalDate.parse("2016-10-01"), LocalDate.now()).getDays();
		Map<String, Object> map = service.getMainData();
		assertEquals(0, map.get("emptyApartments"));
		assertNotNull(map);
		assertNull(map.get("settings"));
		assertEquals("Woda", map.get("readingMedia"));
		assertEquals(days, map.get("radingDays"));
	}

	@Test
	public void getListsForTenants() {
		Apartment aparmtent = apService.getById(2L);
		List<ReadingEnergy> energy = service.getReadingEnergyForTenant(aparmtent);
		assertEquals(6, energy.size());
		for (ReadingEnergy readingEnergy : energy) {
			int apNum = readingEnergy.getMeter().getApartment().getApartmentNumber();
			assertTrue(apNum == 0 || apNum == 1);
		}

		List<ReadingGas> gas = service.getReadingGasForTenant(aparmtent);
		for (ReadingGas readingGas : gas) {
			int apNum = readingGas.getMeter().getApartment().getApartmentNumber();
			assertTrue(apNum == 0 || apNum == 1);
		}

		List<ReadingWater> water = service.getReadingWaterForTenant(aparmtent);
		for (ReadingWater readingWater : water) {
			int apNum = readingWater.getMeter().getApartment().getApartmentNumber();
			assertTrue(apNum == 0 || apNum == 1);
		}

		assertEquals(9, gas.size());
		assertEquals(6, water.size());

	}

	

}
