package kamienica.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import kamienica.core.Media;
import kamienica.feature.meter.MeterEnergy;
import kamienica.feature.meter.MeterService;

public class MeterEnergyServiceTest extends AbstractServiceTest {

	@Autowired
	MeterService service;

	@Override
	public void getList() {
		assertEquals(5, service.getList(Media.ENERGY).size());
		List<MeterEnergy> list = service.getList(Media.ENERGY);
		assertEquals(5, list.size());

	}

	@Override
	public void getById() {
		MeterEnergy meter = service.getById(3L, Media.ENERGY);
		assertEquals("Piwnica", meter.getDescription());
		assertEquals(1, meter.getApartment().getApartmentNumber());

	}

	@Transactional
	@Override
	@Test
	public void add() {
		MeterEnergy meter = createDummyMeter();
		service.save(meter, Media.ENERGY);
		assertEquals(6, service.getIdList(Media.ENERGY).size());
	}

	@Transactional
	@Override
	public void remove() {
		MeterEnergy meter = createDummyMeter();
		service.save(meter, Media.ENERGY);
		assertEquals(6, service.getList(Media.ENERGY).size());
		service.delete(6L, Media.ENERGY);
		service.delete(7L, Media.ENERGY);
		service.delete(8L, Media.ENERGY);
		assertEquals(5, service.getList(Media.ENERGY).size());

	}

	@Override
	public void update() {
		MeterEnergy meter = service.getById(4L, Media.ENERGY);
		meter.setDescription("uPdate");
		service.update(meter, Media.ENERGY);
		meter = null;
		meter = service.getById(4L, Media.ENERGY);
		assertEquals("uPdate", meter.getDescription());
	}

	@Override
	public void addWithValidationError() {
		// TODO Auto-generated method stub

	}

	private MeterEnergy createDummyMeter() {
		return new MeterEnergy("test", "test", "test", service.getById(3L, Media.ENERGY).getApartment());
	}

}
