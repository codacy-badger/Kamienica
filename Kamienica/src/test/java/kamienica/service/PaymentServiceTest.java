package kamienica.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.payment.PaymentService;

public class PaymentServiceTest extends AbstractServiceTest {

	@Autowired
	PaymentService service;
	
	@Test
	@Override
	public void getList() {
		assertEquals(3, service.getPaymentEnergyList().size());
		assertEquals(3, service.getPaymentGasList().size());
		assertEquals(3, service.getPaymentWaterList().size());

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
