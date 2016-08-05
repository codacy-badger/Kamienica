package kamienica.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.core.Media;
import kamienica.feature.meter.MeterService;
import kamienica.feature.meter.MeterWater;

public class MeterWaterServiceTest extends AbstractServiceTest {

	@Autowired
	MeterService service;
	
	@Test
	@Override
	public void getList() {
		List<MeterWater> list = service.getList(Media.WATER);
		System.out.println(list);
		assertEquals(7, list.size());
		
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
