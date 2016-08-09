package kamienica.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

		List<ReadingGas> gas = service.getReadingGasForTenant(aparmtent);
		System.out.println(gas);
		List<ReadingWater> water = service.getReadingWaterForTenant(aparmtent);

		assertEquals(6, energy.size());
		assertEquals(6, gas.size());
		assertEquals(6, water.size());
	}

	@Override
	public void getList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getById() {
		// TODO Auto-generated method stub

	}

	@Override
	public void add() {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addWithValidationError() {
		// TODO Auto-generated method stub

	}

}
