package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.enums.Media;
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

public class InvoiceEnergyServiceTest extends ServiceTest {

    private Tenant t;
    private Residence r;


    @Before
    public void initData() {
        t = tenantService.getById(RES_1_OWNER_ID);
        r = residenceService.getById(RESIDENCE_ID);
    }

    @Test
    public void getList() {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(tenantService.getById(1L));
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        assertEquals(1, invoiceService.list(Media.ENERGY).size());

    }

    @Transactional
    @Test
    public void add() {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(tenantService.getById(1L));
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.ENERGY);
        assertEquals(2, list.size());

        Invoice invoice = new Invoice("112233", TODAY, 200, r, list.get(1), Media.ENERGY);

        invoiceService.save(invoice, Media.ENERGY, t, r);
        assertEquals(2, invoiceService.list(Media.ENERGY).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.ENERGY);

        assertEquals(6, paymentList.size());
        assertEquals(30.30, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(48.48, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(121.212, paymentList.get(5).getPaymentAmount(), DELTA);

         list = readingDetailsService.getUnresolved( r, Media.ENERGY);
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-07-01"), list.get(0).getReadingDate());
    }

    @Transactional
    @Test
    public void addForFirstReading() throws InvalidDivisionException {
        mockStatic(SecurityDetails.class);
        when(SecurityDetails.getLoggedTenant()).thenReturn(tenantService.getById(1L));
        when(SecurityDetails.getResidencesForOwner()).thenReturn(getMockedResidences());

        List<ReadingDetails> details = readingDetailsService.getUnresolved( r, Media.ENERGY);
        assertEquals(2, details.size());

        Invoice invoice = new Invoice("34", new LocalDate(), 200, r, details.get(0), Media.ENERGY);
        invoiceService.save(invoice, Media.ENERGY, t, r);
        assertEquals(2, invoiceService.list(Media.ENERGY).size());
        List<Payment> paymentList = paymentService.getPaymentList(Media.ENERGY);

        assertEquals(6, paymentList.size());

        assertEquals(48.48, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(84.85, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(66.67, paymentList.get(5).getPaymentAmount(), DELTA);

        details = readingDetailsService.getUnresolved( r, Media.ENERGY);
        assertEquals(1, details.size());
        assertEquals(LocalDate.parse("2016-09-01"), details.get(0).getReadingDate());
    }

    @Transactional
    @Test
    public void remove() {
        invoiceService.delete(1L);
        List<ReadingDetails> list = readingDetailsService.getUnresolved( r, Media.ENERGY);
        assertEquals(3, list.size());

    }

    @Test
    @Ignore("not implemented yet")
    public void shouldNotBeAbleToInsertInvoiceWithSameDateResidenceAndMedia() {}

    }

