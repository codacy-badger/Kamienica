package kamienica.service;

import kamienica.core.enums.Media;
import kamienica.feature.meter.MeterWater;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MeterWaterServiceTest extends AbstractServiceTest {

	@Test
	public void getList() {
		List<MeterWater> list = meterService.getList(Media.WATER);
		
		assertEquals(7, list.size());

	}

	
	@Transactional
	@Test
	public void getActiveMeters() {
		assertEquals(7, meterService.getIdListForActiveMeters(Media.WATER).size());
		MeterWater meter = meterService.getById(4L, Media.WATER);
		meter.setDeactivation(LocalDate.now().minusDays(1));
		meterService.update(meter, Media.WATER);

		assertEquals(6, meterService.getIdListForActiveMeters(Media.WATER).size());

	}
}
