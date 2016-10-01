package kamienica.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.core.util.Media;
import kamienica.feature.payment.PaymentService;

public class PaymentServiceTest extends AbstractServiceTest {

	@Autowired
	PaymentService service;
	
	@Test
	public void getList() {
		assertEquals(3, service.getPaymentList(Media.ENERGY).size());
		assertEquals(3, service.getPaymentList(Media.GAS).size());
		assertEquals(3, service.getPaymentList(Media.WATER).size());

	}

	

}
