package kamienica.junitservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.meter.MeterService;

public class MeterGasServiceTest extends AbstractServiceTest {

	@Autowired
	MeterService service;

	@Test
	@Override
	public void getList() {
		assertEquals(6, service.getGasList().size());

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

}
