package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.util.SecurityDetails;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.exception.InvalidDivisionException;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class InvoiceWaterServiceTest extends ServiceTest {

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

        assertEquals(1, invoiceService.list(Media.WATER).size());

    }


    @Test
    @Transactional
    public void add() throws InvalidDivisionException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        List<Reading> list = readingService.getUnresolvedReadings(Media.WATER, r);
        assertEquals(60, list.get(1).getValue(), 0);
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(1).getReadingDetails(), Media.WATER);

        invoiceService.save(invoice, Media.WATER, t, r);
        when(SecurityDetails.getLoggedTenant()).thenReturn(tenantService.getById(1L));
        assertEquals(2, invoiceService.list(Media.WATER).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.WATER);

        assertEquals(6, paymentList.size());

        assertEquals(38.09, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(52.38, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(109.52, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingService.getUnresolvedReadings(Media.WATER, r);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-07-01"), list.get(0).getReadingDetails().getReadingDate());
    }

    @Test
    @Transactional
    public void addForFirstReading() throws InvalidDivisionException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(getOwner());
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());
        List<Reading> list = readingService.getUnresolvedReadings(Media.WATER, r);

        assertEquals(33, list.get(0).getValue(), 0);
        assertEquals(60, list.get(1).getValue(), 1);
        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(0).getReadingDetails(), Media.WATER);

        invoiceService.save(invoice, Media.WATER, t, r);
        when(SecurityDetails.getLoggedTenant()).thenReturn(tenantService.getById(1L));
        assertEquals(2, invoiceService.list(Media.WATER).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.WATER);

        assertEquals(6, paymentList.size());

        assertEquals(36.36, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(72.72, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(90.90, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingService.getUnresolvedReadings(Media.WATER, r);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-09-01"), list.get(0).getReadingDetails().getReadingDate());
    }

    @Test
    @Transactional
    public void remove() {
        invoiceService.delete(1L);
        List<Reading> list = readingService.getUnresolvedReadings(Media.WATER, r);
        assertEquals(3, list.size());

    }

//    @Transactional
//    @Test
//    @Ignore
//    public void update() {
//        Invoice invoice = new Invoice("23423423", new LocalDate(), 400,
//                readingService.getById(6L, Media.WATER));
//        invoice.setId(1L);
//        List<Payment> oldList = paymentService.getPaymentByInvoice(invoice, Media.WATER);
//
//        invoice.setTotalAmount(400.0);
//        invoiceService.update(invoice, Media.WATER);
//
//        List<Payment> newList = paymentService.getPaymentByInvoice(invoice, Media.WATER);
//
//        for (int i = 0; i < newList.size(); i++) {
//            double test = newList.get(i).getPaymentAmount() / oldList.get(i).getPaymentAmount();
//            assertEquals(2, test, 0);
//        }
//
//    }


    @Transactional
    @Test(expected = InvalidDivisionException.class)
    public void prepareForRegistrationWithException() throws InvalidDivisionException {
        Apartment ap = new Apartment(78, "1234", "dummy", residenceService.getById(1L));
        apartmentService.save(ap);
        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(r, Media.WATER);
        assertEquals(2, list.size());
        assertEquals(33, list.get(0).getValue(), 0);
        assertEquals(56, list.get(1).getValue(), 0);
    }

    @Test
    public void prepareForRegistration() throws InvalidDivisionException {
        // apService.deleteByID(5L);
        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(r, Media.WATER);

        assertEquals(2, list.size());
        assertEquals(33, list.get(0).getValue(), 0);
        assertEquals(60, list.get(1).getValue(), 0);
    }

    @Transactional
    @Test(expected = InvalidDivisionException.class)
    public void shouldThrowInvalidDivisionExceptionWhilePreparing() throws InvalidDivisionException {
        divisionService.deleteAll();
        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(r, Media.WATER);
        assertEquals(0, list.size());
    }

}
