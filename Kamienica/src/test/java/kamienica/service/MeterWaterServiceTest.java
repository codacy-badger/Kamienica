package kamienica.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.enums.Media;
import kamienica.feature.meter.MeterService;
import kamienica.feature.meter.MeterWater;

public class MeterWaterServiceTest extends AbstractServiceTest {

	@Autowired
	MeterService service;

	@Test
	public void getList() {
		List<MeterWater> list = service.getList(Media.WATER);
		
		assertEquals(7, list.size());

	}

	
	@Transactional
	@Test
	public void getActiveMeters() {
		assertEquals(7, service.getIdListForActiveMeters(Media.WATER).size());
		MeterWater meter = service.getById(4L, Media.WATER);
		meter.setDeactivation(LocalDate.now().minusDays(1));
		service.update(meter, Media.WATER);

		assertEquals(6, service.getIdListForActiveMeters(Media.WATER).size());

	}
}
