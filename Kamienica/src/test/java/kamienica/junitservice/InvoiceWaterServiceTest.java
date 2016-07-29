package kamienica.junitservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.invoice.InvoiceService;

public class InvoiceWaterServiceTest extends AbstractServiceTest {

	@Autowired
	InvoiceService service;

	
	@Test
	@Override
	public void getList() {
		assertEquals(1, service.getWaterInvoiceList().size());

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
