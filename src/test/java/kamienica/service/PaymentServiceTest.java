package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PaymentServiceTest extends ServiceTest {


    @Test
    public void getList() {
        assertEquals(3, paymentService.getPaymentList(Media.ENERGY).size());
        assertEquals(3, paymentService.getPaymentList(Media.GAS).size());
        assertEquals(3, paymentService.getPaymentList(Media.WATER).size());
    }


    @Test
    public void getEnergyForTenant() {
        final Tenant TENANT = tenantService.getById(1L);
        List<Payment> result = paymentService.getPaymentForTenant(TENANT, Media.ENERGY);
        assertEquals(1, result.size());
        assertEquals(88.67, result.get(0).getPaymentAmount(), DELTA);
    }


    @Test
    public void getGasForTenant() {
        final Tenant TENANT = tenantService.getById(1L);
        List<Payment> result = paymentService.getPaymentForTenant(TENANT, Media.GAS);
        assertEquals(1, result.size());
        assertEquals(38.63, result.get(0).getPaymentAmount(), DELTA);
    }


    @Test
    public void getWaterForTenant() {
        final Tenant TENANT = tenantService.getById(1L);
        List<Payment> result = paymentService.getPaymentForTenant(TENANT, Media.WATER);
        assertEquals(1, result.size());
        assertEquals(27.27, result.get(0).getPaymentAmount(), DELTA);
    }

}
