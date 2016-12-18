package kamienica.service;

import kamienica.core.enums.Media;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.feature.apartment.ApartmentService;
import kamienica.feature.division.DivisionService;
import kamienica.feature.invoice.InvoiceService;
import kamienica.feature.payment.Payment;
import kamienica.feature.payment.PaymentService;
import kamienica.feature.reading.Reading;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingService;
import kamienica.model.Apartment;
import kamienica.model.InvoiceEnergy;
import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InvoiceEnergyServiceTest extends AbstractServiceTest {

	@Autowired
	PaymentService paymentService;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	ReadingService readingService;
	@Autowired
	DivisionService divisionService;
	@Autowired
	ApartmentService apService;

	@Test
	public void getList() {
		assertEquals(1, invoiceService.getList(Media.ENERGY).size());

	}

	@Transactional
	@Test
	public void add() throws InvalidDivisionException {
		List<ReadingEnergy> list = readingService.getUnresolvedReadingsEnergy();
		assertEquals(true, divisionService.isDivisionCorrect());
		assertEquals(31, list.get(1).getValue(), 0);
		InvoiceEnergy invoice = new InvoiceEnergy("112233", "test", new LocalDate(), 200, list.get(1));

		invoiceService.save(invoice, Media.ENERGY);
		assertEquals(2, invoiceService.getList(Media.ENERGY).size());
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
		List<ReadingEnergy> list = readingService.getUnresolvedReadingsEnergy();
		assertEquals(11, list.get(0).getValue(), 0);

		InvoiceEnergy invoice = new InvoiceEnergy("112233", "test", new LocalDate(), 200, list.get(0));
		invoiceService.save(invoice, Media.ENERGY);
		assertEquals(2, invoiceService.getList(Media.ENERGY).size());
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

	@Transactional
	@Ignore
	@Test
	public void update() {
		InvoiceEnergy invoice = new InvoiceEnergy("23423423", "test", new LocalDate(), 400,
                readingService.getById(6L, Media.ENERGY));
		invoice.setId(1L);

		List<? extends Payment> oldList = paymentService.getPaymentList(Media.ENERGY);

		invoice.setTotalAmount(400.0);
		invoiceService.update(invoice, Media.ENERGY);

		List<? extends Payment> newList = paymentService.getPaymentList(Media.ENERGY);

		for (int i = 0; i < newList.size(); i++) {
			double test = newList.get(i).getPaymentAmount() / oldList.get(i).getPaymentAmount();
			assertEquals(2, test, 0);
		}

	}

	@Transactional
	@Test(expected = InvalidDivisionException.class)
	public void prepareForRegistrationWithException() throws InvalidDivisionException {
		Apartment ap = new Apartment(78, "1234", "dummy");
		apService.save(ap);
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
