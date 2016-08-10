package kamienica.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.Media;
import kamienica.feature.meter.MeterEnergy;
import kamienica.feature.meter.MeterService;

public class MeterEnergyServiceTest extends AbstractServiceTest {

	@Autowired
	MeterService service;

//	private Set<Long> idSet = new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L));

	@Test
	public void getList() {
		assertEquals(5, service.getList(Media.ENERGY).size());
		List<MeterEnergy> list = service.getList(Media.ENERGY);
		assertEquals(5, list.size());

	}

	@Transactional
	@Test
	public void getActiveMeters() {
		assertEquals(5, service.getIdListForActiveMeters(Media.ENERGY).size());
		MeterEnergy meter = service.getById(4L, Media.ENERGY);
		meter.setDeactivation(LocalDate.now().minusDays(1));
		service.update(meter, Media.ENERGY);

		assertEquals(4, service.getIdListForActiveMeters(Media.ENERGY).size());

	}

	@Test
	public void getById() {
		MeterEnergy meter = service.getById(3L, Media.ENERGY);
		assertEquals("Piwnica", meter.getDescription());
		assertEquals(1, meter.getApartment().getApartmentNumber());

	}

	@Transactional
	@Test
	public void add() {
		MeterEnergy meter = createDummyMeter();
		service.save(meter, Media.ENERGY);
		assertEquals(6, service.getIdList(Media.ENERGY).size());
	}

	@Transactional
	public void remove() {
		MeterEnergy meter = createDummyMeter();
		service.save(meter, Media.ENERGY);
		assertEquals(6, service.getList(Media.ENERGY).size());
		service.delete(6L, Media.ENERGY);
		service.delete(7L, Media.ENERGY);
		service.delete(8L, Media.ENERGY);
		assertEquals(5, service.getList(Media.ENERGY).size());

	}

	@Test
	public void update() {
		MeterEnergy meter = service.getById(4L, Media.ENERGY);
		meter.setDescription("uPdate");
		service.update(meter, Media.ENERGY);
		meter = null;
		meter = service.getById(4L, Media.ENERGY);
		assertEquals("uPdate", meter.getDescription());
	}

	private MeterEnergy createDummyMeter() {
		return new MeterEnergy("test", "test", "test", service.getById(3L, Media.ENERGY).getApartment());
	}

}
