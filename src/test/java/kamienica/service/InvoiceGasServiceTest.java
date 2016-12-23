package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.core.enums.Media;
import kamienica.core.enums.WaterHeatingSystem;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.feature.payment.Payment;
import kamienica.feature.reading.Reading;
import kamienica.feature.reading.ReadingGas;
import kamienica.model.Apartment;
import kamienica.model.InvoiceGas;
import kamienica.model.Settings;
import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InvoiceGasServiceTest extends DatabaseTest {

    @Test
    public void getList() {
        assertEquals(1, invoiceService.getList(Media.GAS).size());
    }

    @Test
    @Transactional
    public void add() throws InvalidDivisionException {
        List<ReadingGas> list = readingService.getUnresolvedReadingsGas();
        assertEquals(196, list.get(1).getValue(), 0);
        InvoiceGas invoice = new InvoiceGas("112233", "test", new LocalDate(), 200, list.get(1));

        invoiceService.save(invoice, Media.GAS);
        assertEquals(2, invoiceService.getList(Media.GAS).size());
        List<? extends Payment> paymentList = paymentService.getPaymentList(Media.GAS);

        assertEquals(6, paymentList.size());

        assertEquals(43.39, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(64.55, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(92.06, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingService.getUnresolvedReadingsGas();
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-07-29"), list.get(0).getReadingDate());
    }

    @Test
    @Transactional
    public void addForFirstReadingWithSharedWaterHeating() throws InvalidDivisionException {
        List<ReadingGas> list = readingService.getUnresolvedReadingsGas();
        assertEquals(114, list.get(0).getValue(), 0);
        InvoiceGas invoice = new InvoiceGas("112233", "test", new LocalDate(), 200, list.get(0));

        invoiceService.save(invoice, Media.GAS);
        assertEquals(2, invoiceService.getList(Media.GAS).size());
        List<? extends Payment> paymentList = paymentService.getPaymentList(Media.GAS);

        assertEquals(6, paymentList.size());

        assertEquals(28.27, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(68.13, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(103.61, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingService.getUnresolvedReadingsGas();
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-10-01"), list.get(0).getReadingDate());

    }

    @Test
    @Transactional
    public void addForFirstReadingWithSeparateWaterHeating() throws InvalidDivisionException {
        List<ReadingGas> list = readingService.getUnresolvedReadingsGas();
        Settings setings = settingsService.getSettings();
        setings.setWaterHeatingSystem(WaterHeatingSystem.INDIVIDUAL_GAS);
        settingsService.save(setings);
        assertEquals(114, list.get(0).getValue(), 0);
        InvoiceGas invoice = new InvoiceGas("112233", "test", new LocalDate(), 200, list.get(0));

        invoiceService.save(invoice, Media.GAS);
        assertEquals(2, invoiceService.getList(Media.GAS).size());
        List<? extends Payment> paymentList = paymentService.getPaymentList(Media.GAS);

        assertEquals(6, paymentList.size());

        assertEquals(71.43, paymentList.get(3).getPaymentAmount(), DELTA);
        assertEquals(78.57, paymentList.get(4).getPaymentAmount(), DELTA);
        assertEquals(50.00, paymentList.get(5).getPaymentAmount(), DELTA);

        list = readingService.getUnresolvedReadingsGas();
        assertEquals(1, list.size());
        assertEquals(LocalDate.parse("2016-10-01"), list.get(0).getReadingDate());

    }

    @Test
    @Transactional
    public void remove() {
        invoiceService.delete(1L, Media.GAS);
        List<ReadingGas> list = readingService.getUnresolvedReadingsGas();
        assertEquals(3, list.size());
    }

    @Transactional
    @Ignore
    @Test
    public void update() {
        InvoiceGas invoice = new InvoiceGas("23423423", "test", new LocalDate(), 400,
                readingService.getById(6L, Media.GAS));
        invoice.setId(1L);
        List<? extends Payment> oldList = paymentService.getPaymentList(Media.GAS);

        invoice.setTotalAmount(400.0);
        invoiceService.update(invoice, Media.GAS);

        List<? extends Payment> newList = paymentService.getPaymentList(Media.GAS);

        for (int i = 0; i < newList.size(); i++) {
            double test = newList.get(i).getPaymentAmount() / oldList.get(i).getPaymentAmount();
            assertEquals(2, test, 0);
        }
    }

    @Transactional
    @Test(expected = InvalidDivisionException.class)
    public void prepareForRegistrationWithException() throws InvalidDivisionException {
        Apartment ap = new Apartment(78, "1234", "dummy");
        apartmentService.save(ap);
        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(Media.GAS);
        assertEquals(2, list.size());
        assertEquals(11, list.get(0).getValue(), 0);
        assertEquals(31, list.get(1).getValue(), 0);
    }

    @Transactional
    @Test
    public void prepareForRegistration() throws InvalidDivisionException {
        // apService.deleteByID(5L);
        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(Media.GAS);
        assertEquals(2, list.size());
        assertEquals(114, list.get(0).getValue(), 0);
        assertEquals(196, list.get(1).getValue(), 0);

    }

    @Transactional
    @Test(expected = InvalidDivisionException.class)
    public void shouldThrowInvalidDivisionExceptionWhilePreparing() throws InvalidDivisionException {
        divisionService.deleteAll();

        List<Reading> list = invoiceService.getUnpaidReadingForNewIncvoice(Media.GAS);
        assertEquals(0, list.size());
    }

}
