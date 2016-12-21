package kamienica.service;

import kamienica.core.enums.Media;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaymentServiceTest extends AbstractServiceTest {

    @Test
    public void getList() {
        assertEquals(3, paymentService.getPaymentList(Media.ENERGY).size());
        assertEquals(3, paymentService.getPaymentList(Media.GAS).size());
        assertEquals(3, paymentService.getPaymentList(Media.WATER).size());

    }


}
