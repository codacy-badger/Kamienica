package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.core.enums.Media;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.util.SecurityDetails;
import kamienica.model.*;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class InvoiceEnergyServiceTest extends ServiceTest {

    private  Tenant t;
    private Residence r;

    @Before
    public void initData() {
        t = tenantService.getTenantById(1L);
        r = residenceService.getById(1L);
    }

    @Test
    public void getList() {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(tenantService.getTenantById(1L));
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        assertEquals(1, invoiceService.list(Media.ENERGY).size());

    }

    @Transactional
    @Test
    public void add() throws InvalidDivisionException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(tenantService.getTenantById(1L));
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        List<ReadingEnergy> list = readingService.getUnresolvedReadingsEnergy();
        assertEquals(true, divisionService.isDivisionCorrect());
        assertEquals(31, list.get(1).getValue(), 0);
        InvoiceEnergy invoice = new InvoiceEnergy("112233", new LocalDate(), 200, list.get(1));

        invoiceService.save(invoice, Media.ENERGY, t, r);
        assertEquals(2, invoiceService.list(Media.ENERGY).size());
        List<? extends Payment> paymentList = paymentService.getPaymentList(Media.ENERGY);

        assertEquals(6, paymentList.size());
        assertEquals(30.30, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(48.48, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(121.212, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingService.getUnresolvedReadingsEnergy();
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-07-01"), list.get(0).getReadingDate());
    }

    @Transactional
    @Test
    public void addForFirstReading() throws InvalidDivisionException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(tenantService.getTenantById(1L));
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        List<ReadingEnergy> list = readingService.getUnresolvedReadingsEnergy();
        assertEquals(11, list.get(0).getValue(), 0);

        InvoiceEnergy invoice = new InvoiceEnergy("112233", new LocalDate(), 200, list.get(0));
        invoiceService.save(invoice, Media.ENERGY, t, r);
        assertEquals(2, invoiceService.list(Media.ENERGY).size());
        List<? extends Payment> paymentList = paymentService.getPaymentList(Media.ENERGY);

        assertEquals(6, paymentList.size());

        assertEquals(48.48, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(84.85, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(66.67, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingService.getUnresolvedReadingsEnergy();
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-09-01"), list.get(0).getReadingDate());
    }

    @Transactional
    @Test
    public void remove() {
        invoiceService.delete(1L, Media.ENERGY);
        List<ReadingEnergy> list = readingService.getUnresolvedReadingsEnergy();
        assertEquals(3, list.size());

    }


//    @Ignore
//    @Test
//    @Transactional
//    public void updateEnergy() {
//        final InvoiceEnergy invoiceEnergy = invoiceService.getEnergyByID(1L);
//        final List<Payment> oldPayments = (List<Payment>) paymentService.getPaymentList(Media.ENERGY);
//        invoiceEnergy.setTotalAmount(invoiceEnergy.getTotalAmount() * 2);
//        invoiceService.update(invoiceEnergy, Media.ENERGY);
//        final List<Payment> newPayments = (List<Payment>) paymentService.getPaymentList(Media.ENERGY);
//
//        for (int i = 0; i < newPayments.size(); i++) {
//            final double oldPayment = oldPayments.get(i).getPaymentAmount();
//            final double newPayment = newPayments.get(i).getPaymentAmount();
//
//            assertEquals(2, newPayment / oldPayment, DELTA);
//        }
//    }

    @Transactional
    @Test(expected = InvalidDivisionException.class)
    public void prepareForRegistrationWithException() throws InvalidDivisionException {
        Apartment ap = new Apartment(78, "1234", "dummy", residenceService.getById(1L));
        apartmentService.save(ap);
        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(Media.ENERGY);
        assertEquals(2, list.size());
        assertEquals(11, list.get(0).getValue(), 0);
        assertEquals(31, list.get(1).getValue(), 0);
    }

    @Test
    public void prepareForRegistration() throws InvalidDivisionException {
        // apService.deleteByID(5L);
        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(Media.ENERGY);
        assertEquals(2, list.size());
        assertEquals(11, list.get(0).getValue(), 0);
        assertEquals(31, list.get(1).getValue(), 0);
    }

    @Transactional
    @Test(expected = InvalidDivisionException.class)
    public void shouldThrowInvalidDivisionExceptionWhilePreparing() throws InvalidDivisionException {
        divisionService.deleteAll();
        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(Media.ENERGY);
        assertEquals(0, list.size());
    }

}
