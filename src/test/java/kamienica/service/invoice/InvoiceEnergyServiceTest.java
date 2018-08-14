package kamienica.service.invoice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class InvoiceEnergyServiceTest extends ServiceTest {

    private Residence residence;

    @Before
    public void initData() {
        residence = getOWnersResidence();
    }

    @Test
    public void getList() {
        assertEquals(1, invoiceService.list(Media.ENERGY, residence.getId()).size());
    }

    @Transactional
    @Test
    public void add() {
        List<ReadingDetails> list = readingDetailsService.getUnresolved(residence, Media.ENERGY);
        assertEquals(2, list.size());

        Invoice invoice = new Invoice("112233", TODAY, 200, residence, list.get(1), Media.ENERGY);

        invoiceService.save(invoice);
        assertEquals(2, invoiceService.list(Media.ENERGY, residence.getId()).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.ENERGY);

        assertEquals(6, paymentList.size());
        assertEquals(30.30, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(48.48, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(121.212, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingDetailsService.getUnresolved(residence, Media.ENERGY);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-07-01"), list.get(0).getReadingDate());
    }

    @Transactional
    @Test
    public void addForFirstReading() {
        List<ReadingDetails> details = readingDetailsService.getUnresolved(residence, Media.ENERGY);
        assertEquals(2, details.size());

        Invoice invoice = new Invoice("34", new LocalDate(), 200, residence, details.get(0), Media.ENERGY);
        invoiceService.save(invoice);
        assertEquals(2, invoiceService.list(Media.ENERGY, residence.getId()).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.ENERGY);

        assertEquals(6, paymentList.size());

        assertEquals(48.48, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(84.85, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(66.67, paymentList.get(5).getPaymentAmount(), DELTA);

        details = readingDetailsService.getUnresolved(residence, Media.ENERGY);
        assertEquals(1, details.size());
        assertEquals(LocalDate.parse("2016-09-01"), details.get(0).getReadingDate());
    }

    @Transactional
    @Test
    public void remove() {
        final Invoice invoice = invoiceService.getByID(3L);
        invoiceService.delete(invoice);
        List<ReadingDetails> details = readingDetailsService.getUnresolved(residence, Media.ENERGY);
        List<Invoice> invoices = invoiceService.list(Media.ENERGY, residence.getId());
        assertTrue(invoices.isEmpty());
        assertEquals(3, details.size());
    }
}

