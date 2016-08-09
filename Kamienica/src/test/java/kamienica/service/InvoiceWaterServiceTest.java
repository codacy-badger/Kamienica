package kamienica.service;

import static org.junit.Assert.assertEquals;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.Media;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;
import kamienica.feature.division.DivisionService;
import kamienica.feature.invoice.InvoiceWater;
import kamienica.feature.invoice.InvoiceService;
import kamienica.feature.payment.PaymentWater;
import kamienica.feature.payment.PaymentService;
import kamienica.feature.reading.ReadingWater;
import kamienica.feature.reading.ReadingService;

public class InvoiceWaterServiceTest extends AbstractServiceTest {

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
	@Override
	public void getList() {
		assertEquals(1, invoiceService.getWaterInvoiceList().size());

	}

	@Override
	public void getById() {
		// TODO Auto-generated method stub

	}

	@Test
	@Transactional
	@Override
	public void add() {
		List<ReadingWater> list = readingService.getUnresolvedReadingsWater();
		assertEquals(60, list.get(1).getValue(), 0);
		InvoiceWater invoice = new InvoiceWater("112233", "test", new LocalDate(), 200, list.get(1));

		invoiceService.save(invoice, Media.WATER);
		assertEquals(2, invoiceService.getWaterInvoiceList().size());
		List<PaymentWater> paymentList = paymentService.getPaymentWaterList();

		assertEquals(6, paymentList.size());

		assertEquals(38.09, paymentList.get(3).getPaymentAmount(), DELTA);
		assertEquals(52.38, paymentList.get(4).getPaymentAmount(), DELTA);
		assertEquals(109.52, paymentList.get(5).getPaymentAmount(), DELTA);

		list = readingService.getUnresolvedReadingsWater();
		assertEquals(1, list.size());
		assertEquals(LocalDate.parse("2016-07-01"), list.get(0).getReadingDate());
	}

	@Test
	@Transactional
	public void addForFirstReading() {
		List<ReadingWater> list = readingService.getUnresolvedReadingsWater();

		assertEquals(33, list.get(0).getValue(), 0);
		assertEquals(60, list.get(1).getValue(), 1);
		InvoiceWater invoice = new InvoiceWater("112233", "test", new LocalDate(), 200, list.get(0));

		invoiceService.save(invoice, Media.WATER);
		assertEquals(2, invoiceService.getWaterInvoiceList().size());
		List<PaymentWater> paymentList = paymentService.getPaymentWaterList();
		
		assertEquals(6, paymentList.size());

		assertEquals(36.36, paymentList.get(3).getPaymentAmount(), DELTA);
		assertEquals(72.72, paymentList.get(4).getPaymentAmount(), DELTA);
		assertEquals(90.90, paymentList.get(5).getPaymentAmount(), DELTA);

		list = readingService.getUnresolvedReadingsWater();
		assertEquals(1, list.size());
		assertEquals(LocalDate.parse("2016-09-01"), list.get(0).getReadingDate());
	}

	@Test
	@Transactional
	@Override
	public void remove() {
		invoiceService.delete(1L, Media.WATER);
		List<ReadingWater> list = readingService.getUnresolvedReadingsWater();
		assertEquals(3, list.size());

	}

	@Transactional
	@Override
	@Ignore
	@Test
	public void update() {
		InvoiceWater invoice = new InvoiceWater("23423423", "test", new LocalDate(), 400,
				(ReadingWater) readingService.getById(6L, Media.WATER));
		invoice.setId(1L);
		List<PaymentWater> oldList = paymentService.getPaymentWaterByInvoice(invoice);

		invoice.setTotalAmount(400.0);
		invoiceService.update(invoice, Media.WATER);

		List<PaymentWater> newList = paymentService.getPaymentWaterByInvoice(invoice);

		for (int i = 0; i < newList.size(); i++) {
			double test = newList.get(i).getPaymentAmount() / oldList.get(i).getPaymentAmount();
			assertEquals(2, test, 0);
		}

	}

	@Override
	public void addWithValidationError() {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Test(expected = InvalidDivisionException.class)
	public void prepareForRegistrationWithException() throws InvalidDivisionException {
		Apartment ap = new Apartment(78, "1234", "dummy");
		apService.save(ap);
		List<ReadingWater> list = invoiceService.prepareForRegistration(Media.WATER);
		assertEquals(2, list.size());
		assertEquals(33, list.get(0).getValue(), 0);
		assertEquals(56, list.get(1).getValue(), 0);
	}

	@Test
	public void prepareForRegistration() throws InvalidDivisionException {
		// apService.deleteByID(5L);
		List<ReadingWater> list = invoiceService.prepareForRegistration(Media.WATER);

		assertEquals(2, list.size());
		assertEquals(33, list.get(0).getValue(), 0);
		assertEquals(60, list.get(1).getValue(), 0);
	}

	@Transactional
	@Test(expected = InvalidDivisionException.class)
	public void shouldThrowInvalidDivisionExceptionWhilePreparing() throws InvalidDivisionException {
		System.out.println(divisionService.isDivisionCorrect());
		divisionService.deleteAll();
		System.out.println(divisionService.isDivisionCorrect());
		List<ReadingWater> list = invoiceService.prepareForRegistration(Media.WATER);
		assertEquals(0, list.size());
	}

}
