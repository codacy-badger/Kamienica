package kamienica.junitservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.reading.ReadingService;

public class ReadingEnergyServiceTest extends AbstractServiceTest {

	@Autowired
	ReadingService service;

	@Test
	@Override
	public void getList() {
		assertEquals(15, service.getReadingEnergy().size());

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
