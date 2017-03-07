package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.enums.Media;
import kamienica.model.enums.WaterHeatingSystem;
import kamienica.model.exception.InvalidDivisionException;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.*;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class InvoiceGasServiceTest extends ServiceTest {

    private Tenant t;
    private Residence r;

    @Before
    public void initData() {
        t = tenantService.getById(1L);
        r = residenceService.getById(1L);
    }

    @Test
    public void getList() {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        assertEquals(1, invoiceService.list(Media.GAS).size());
    }

    @Test
    @Transactional
    public void add() {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.GAS);
        assertEquals(2, list.size());
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(1), Media.GAS);

        invoiceService.save(invoice, Media.GAS, t, r);
        assertEquals(2, invoiceService.list(Media.GAS).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.GAS);

        assertEquals(6, paymentList.size());

        assertEquals(43.39, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(64.55, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(92.06, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingDetailsService.getUnresolved( r, Media.GAS);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-07-29"), list.get(0).getReadingDate());
    }

    @Test
    @Transactional
    public void addForFirstReadingWithSharedWaterHeating() throws InvalidDivisionException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());


        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.GAS);
        assertEquals(2, list.size());
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(0), Media.GAS);

        invoiceService.save(invoice, Media.GAS, t, r);
        assertEquals(2, invoiceService.list(Media.GAS).size());

        List<Payment> paymentList = paymentService.getPaymentList(Media.GAS);

        assertEquals(6, paymentList.size());

        assertEquals(28.27, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(68.13, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(103.61, paymentList.get(5).getPaymentAmount(), DELTA);

       list = readingDetailsService.getUnresolved( r, Media.GAS);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-10-01"), list.get(0).getReadingDate());

    }

    @Test
    @Transactional
    public void addForFirstReadingWithSeparateWaterHeating() throws InvalidDivisionException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.GAS);
        Settings setings = settingsService.getSettings();
        setings.setWaterHeatingSystem(WaterHeatingSystem.INDIVIDUAL_GAS);
        settingsService.save(setings);
        assertEquals(2, list.size());
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(0), Media.GAS);

        invoiceService.save(invoice, Media.GAS, t, r);
        assertEquals(2, invoiceService.list(Media.GAS).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.GAS);

        assertEquals(6, paymentList.size());

        assertEquals(71.43, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(78.57, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(50.00, paymentList.get(5).getPaymentAmount(), DELTA);

       list = readingDetailsService.getUnresolved( r, Media.GAS);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-10-01"), list.get(0).getReadingDate());

    }

    @Test
    @Transactional
    public void remove() {
        invoiceService.delete(1L);
        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.GAS);
        assertEquals(2, list.size());
    }


    @Test
    @Ignore("not implemented yet")
    public void shouldNotBeAbleToInsertInvoiceWithSameDateResidenceAndMedia() {}


    @Transactional
    @Test(expected = InvalidDivisionException.class)
    public void prepareForRegistrationWithException() throws InvalidDivisionException {
        Apartment ap = new Apartment(78, "1234", "dummy", residenceService.getById(1L));
        apartmentService.save(ap);
        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.GAS);
        assertEquals(2, list.size());
    }

    @Transactional
    @Test
    public void prepareForRegistration() throws InvalidDivisionException {
        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.GAS);
        assertEquals(2, list.size());
    }


}
