package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.core.enums.Media;
import kamienica.feature.meter.MeterEnergy;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MeterEnergyServiceTest extends DatabaseTest {

	;

	@Test
	public void getList() {
		assertEquals(5, meterService.getList(Media.ENERGY).size());
		List<MeterEnergy> list = meterService.getList(Media.ENERGY);
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

	@Transactional
	public void remove() {
		MeterEnergy meter = createDummyMeter();
		meterService.save(meter, Media.ENERGY);
		assertEquals(6, meterService.getList(Media.ENERGY).size());
		meterService.delete(6L, Media.ENERGY);
		meterService.delete(7L, Media.ENERGY);
		meterService.delete(8L, Media.ENERGY);
		assertEquals(5, meterService.getList(Media.ENERGY).size());

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
		return new MeterEnergy("test", "test", "test", meterService.getById(3L, Media.ENERGY).getApartment());
	}

}
