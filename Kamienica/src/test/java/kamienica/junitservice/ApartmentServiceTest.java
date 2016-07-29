package kamienica.junitservice;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;

public class ApartmentServiceTest extends AbstractServiceTest{

	@Autowired
	ApartmentService service;

	
	@Test
	public void getList() {
		List<Apartment> list = service.getList();
		System.out.println(list.toString());
		assertEquals(5, list.size());
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
