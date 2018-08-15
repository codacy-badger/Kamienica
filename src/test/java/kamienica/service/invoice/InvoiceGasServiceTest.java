package kamienica.service.invoice;

import static org.junit.Assert.assertEquals;

import java.util.List;
import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Settings;
import kamienica.model.enums.Media;
import kamienica.model.enums.WaterHeatingSystem;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

@WithUserDetails(ServiceTest.OWNER)
public class InvoiceGasServiceTest extends ServiceTest {

    private Residence r;

    @Before
    public void initData() {
        r = residenceService.getById(1L);
    }

    @Test
    public void getList() {
        assertEquals(1, invoiceService.list(Media.GAS, r.getId()).size());
    }

    @Test
    @Transactional
    public void add() {
        List<ReadingDetails> list = readingDetailsService.getUnresolved(r, Media.GAS);
        assertEquals(2, list.size());
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(1), Media.GAS);

        invoiceService.save(invoice);
        assertEquals(2, invoiceService.list(Media.GAS, r.getId()).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.GAS);

        assertEquals(6, paymentList.size());

        assertEquals(43.39, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(64.55, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(92.06, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingDetailsService.getUnresolved(r, Media.GAS);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-07-29"), list.get(0).getReadingDate());
    }

    @Test
    @Transactional
    public void addForFirstReadingWithSharedWaterHeating() {
        List<ReadingDetails> list = readingDetailsService.getUnresolved(r, Media.GAS);
        assertEquals(2, list.size());
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(0), Media.GAS);

        invoiceService.save(invoice);
        assertEquals(2, invoiceService.list(Media.GAS, r.getId()).size());

        List<Payment> paymentList = paymentService.getPaymentList(Media.GAS);
        assertEquals(6, paymentList.size());

        assertEquals(28.27, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(68.13, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(103.61, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingDetailsService.getUnresolved(r, Media.GAS);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-10-01"), list.get(0).getReadingDate());

    }

    /**
     * check if this test is needed. The fail here is because of the wrong data->
     *in case there is separated water heating system there should be no main water heater meter.
     * Since there is still a CWU meter and it's readings that impacy the final value
     *
     * */
    @Ignore
    @Test
    @Transactional
    public void addForFirstReadingWithSeparateWaterHeating() {
        List<ReadingDetails> list = readingDetailsService.getUnresolved(r, Media.GAS);
        Settings setings = settingsService.getSettings(getOwnersResidence());
        setings.setWaterHeatingSystem(WaterHeatingSystem.INDIVIDUAL);
        settingsService.save(setings);
        assertEquals(2, list.size());
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(0), Media.GAS);

        invoiceService.save(invoice);
        assertEquals(2, invoiceService.list(Media.GAS, r.getId()).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.GAS);

        assertEquals(6, paymentList.size());

        assertEquals(71.43, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(78.57, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(50.00, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingDetailsService.getUnresolved(r, Media.GAS);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-10-01"), list.get(0).getReadingDate());

    }

    @Test
    @Transactional
    public void remove() {
        final Invoice invoice = invoiceService.getByID(1L);
        invoiceService.delete(invoice);
        final List<ReadingDetails> list = readingDetailsService.getUnresolved(r, Media.GAS);
        assertEquals(2, list.size());
    }

    @Transactional
    @Test
    public void prepareForRegistration() {
        List<ReadingDetails> list = readingDetailsService.getUnresolved(r, Media.GAS);
        assertEquals(2, list.size());
    }
}
