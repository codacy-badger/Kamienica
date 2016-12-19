package kamienica.service;

import kamienica.core.enums.Media;
import kamienica.feature.payment.PaymentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

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
