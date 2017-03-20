package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class InvoiceWaterServiceTest extends ServiceTest {

    private Residence r;

    @Before
    public void initData() {
        r = residenceService.getById(1L);
    }

    @Test
    public void getList() {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        assertEquals(1, invoiceService.list(Media.WATER).size());
    }

    @Test
    @Transactional
    public void add()  {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.WATER);
        assertEquals(2, list.size());
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(1), Media.WATER);

        invoiceService.save(invoice);
        when(SecurityDetails.getLoggedTenant()).thenReturn(tenantService.getById(1L));
        assertEquals(2, invoiceService.list(Media.WATER).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.WATER);

        assertEquals(6, paymentList.size());

        assertEquals(38.09, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(52.38, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(109.52, paymentList.get(5).getPaymentAmount(), DELTA);

       list = readingDetailsService.getUnresolved( r, Media.WATER);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-07-01"), list.get(0).getReadingDate());
    }

    @Test
    @Transactional
    public void addForFirstReading(){
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.WATER);

        assertEquals(2, list.size());
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(0), Media.WATER);

        invoiceService.save(invoice);
        when(SecurityDetails.getLoggedTenant()).thenReturn(tenantService.getById(1L));
        assertEquals(2, invoiceService.list(Media.WATER).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.WATER);

        assertEquals(6, paymentList.size());
        assertEquals(36.36, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(72.72, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(90.90, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingDetailsService.getUnresolved( r, Media.WATER);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-09-01"), list.get(0).getReadingDate());
    }

    @Test
    @Transactional
    public void remove() {
        invoiceService.delete(1L);
        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.WATER);
        assertEquals(3, list.size());

    }


    @Test
    public void prepareForRegistration() {
        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.WATER);
        assertEquals(2, list.size());
    }


}
