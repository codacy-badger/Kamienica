package kamienica.service;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.enums.Media;
import kamienica.feature.meter.MeterGas;
import kamienica.feature.meter.MeterService;

public class MeterGasServiceTest extends AbstractServiceTest {

	@Autowired
	MeterService service;

	@Test
	public void getList() {
		assertEquals(6, service.getList(Media.GAS).size());

	}
	
	@Transactional
	@Test
	public void getActiveMeters() {
		assertEquals(6, service.getIdListForActiveMeters(Media.GAS).size());
		MeterGas meter = service.getById(4L, Media.GAS);
		meter.setDeactivation(LocalDate.now().minusDays(1));
		service.update(meter, Media.GAS);

		assertEquals(5, service.getIdListForActiveMeters(Media.GAS).size());

	}

}
