package kamienica.junitservice;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.division.Division;
import kamienica.feature.division.DivisionService;

public class DivisionServiceTest extends AbstractServiceTest {

	@Autowired
	DivisionService service;

	@Test
	public void getList() {
		List<Division> list = service.getList();
		System.out.println(list);
		assertEquals(15, list.size());
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
