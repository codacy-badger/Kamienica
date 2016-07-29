package kamienica.junitservice;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantService;

public class TenantServiceTest extends AbstractServiceTest {

	@Autowired
	TenantService service;

	@Test
	public void getList() {
		List<Tenant> list = service.getList();
		System.out.println(list);

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

	@Override
	public void addWithValidationError() {
		// TODO Auto-generated method stub
		
	}

}
