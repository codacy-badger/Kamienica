package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.enums.Media;
import kamienica.model.enums.WaterHeatingSystem;
import kamienica.model.exception.InvalidDivisionException;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.*;
import org.joda.time.LocalDate;
import org.junit.Before;
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
    public void add() throws InvalidDivisionException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        List<Reading> list = readingService.getUnresolvedReadings(Media.GAS, r);
        assertEquals(196, list.get(1).getValue(), 0);
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(1).getReadingDetails(), Media.GAS);

        invoiceService.save(invoice, Media.GAS, t, r);
        assertEquals(2, invoiceService.list(Media.GAS).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.GAS);

        assertEquals(6, paymentList.size());

        assertEquals(43.39, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(64.55, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(92.06, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingService.getUnresolvedReadings(Media.GAS, r);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-07-29"), list.get(0).getReadingDetails().getReadingDate());
    }

    @Test
    @Transactional
    public void addForFirstReadingWithSharedWaterHeating() throws InvalidDivisionException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());


        List<Reading> list = readingService.getUnresolvedReadings(Media.GAS, r);
        assertEquals(114, list.get(0).getValue(), 0);
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(0).getReadingDetails(), Media.GAS);

        invoiceService.save(invoice, Media.GAS, t, r);
        assertEquals(2, invoiceService.list(Media.GAS).size());

        List<Payment> paymentList = paymentService.getPaymentList(Media.GAS);

        assertEquals(6, paymentList.size());

        assertEquals(28.27, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(68.13, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(103.61, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingService.getUnresolvedReadings(Media.GAS, r);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-10-01"), list.get(0).getReadingDetails().getReadingDate());

    }

    @Test
    @Transactional
    public void addForFirstReadingWithSeparateWaterHeating() throws InvalidDivisionException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        List<Reading> list = readingService.getUnresolvedReadings(Media.GAS, r);
        Settings setings = settingsService.getSettings();
        setings.setWaterHeatingSystem(WaterHeatingSystem.INDIVIDUAL_GAS);
        settingsService.save(setings);
        assertEquals(114, list.get(0).getValue(), 0);
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(0).getReadingDetails(), Media.GAS);

        invoiceService.save(invoice, Media.GAS, t, r);
        assertEquals(2, invoiceService.list(Media.GAS).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.GAS);

        assertEquals(6, paymentList.size());

        assertEquals(71.43, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(78.57, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(50.00, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingService.getUnresolvedReadings(Media.GAS, r);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-10-01"), list.get(0).getReadingDetails().getReadingDate());

    }

    @Test
    @Transactional
    public void remove() {
        invoiceService.delete(1L);
        List<Reading> list = readingService.getUnresolvedReadings(Media.GAS, r);
        assertEquals(3, list.size());
    }

//    @Transactional
//    @Ignore
//    @Test
//    public void update() {
//        Invoice invoice = new Invoice("23423423", new LocalDate(), 400,
//                readingService.getById(6L, Media.GAS));
//        invoice.setId(1L);
//        List<? extends Payment> oldList = paymentService.getPaymentList(Media.GAS);
//
//        invoice.setTotalAmount(400.0);
//        invoiceService.update(invoice, Media.GAS);
//
//        List<? extends Payment> newList = paymentService.getPaymentList(Media.GAS);
//
//        for (int i = 0; i < newList.size(); i++) {
//            double test = newList.get(i).getPaymentAmount() / oldList.get(i).getPaymentAmount();
//            assertEquals(2, test, 0);
//        }
//    }

    @Transactional
    @Test(expected = InvalidDivisionException.class)
    public void prepareForRegistrationWithException() throws InvalidDivisionException {
        Apartment ap = new Apartment(78, "1234", "dummy", residenceService.getById(1L));
        apartmentService.save(ap);
        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(r, Media.GAS);
        assertEquals(2, list.size());
        assertEquals(11, list.get(0).getValue(), 0);
        assertEquals(31, list.get(1).getValue(), 0);
    }

    @Transactional
    @Test
    public void prepareForRegistration() throws InvalidDivisionException {
        // apService.deleteByID(5L);
        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(r, Media.GAS);
        assertEquals(2, list.size());
        assertEquals(114, list.get(0).getValue(), 0);
        assertEquals(196, list.get(1).getValue(), 0);

    }

    @Transactional
    @Test(expected = InvalidDivisionException.class)
    public void shouldThrowInvalidDivisionExceptionWhilePreparing() throws InvalidDivisionException {
        divisionService.deleteAll();

        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(r, Media.GAS);
        assertEquals(0, list.size());
    }

}
