package kamienica.service.invoice;

import static org.junit.Assert.assertEquals;

import java.util.List;
import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

@WithUserDetails(ServiceTest.OWNER)
public class InvoiceWaterServiceTest extends ServiceTest {

    private Residence r;

    @Before
    public void initData() {
        r = residenceService.getById(1L);
    }

    @Test
    public void getList() {
        assertEquals(1, invoiceService.list(Media.WATER, r.getId()).size());
    }

    @Test
    @Transactional
    public void add() {
        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.WATER);
        assertEquals(2, list.size());
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(1), Media.WATER);

        invoiceService.save(invoice);
        assertEquals(2, invoiceService.list(Media.WATER, r.getId()).size());
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
    public void addForFirstReading() {
        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.WATER);

        assertEquals(2, list.size());
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(0), Media.WATER);

        invoiceService.save(invoice);
        assertEquals(2, invoiceService.list(Media.WATER, r.getId()).size());
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
        final Invoice invoice = invoiceService.getByID(1L);
        invoiceService.delete(invoice);
        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.WATER);
        assertEquals(3, list.size());

    }

    @Test
    public void prepareForRegistration() {
        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.WATER);
        assertEquals(2, list.size());
    }
}
