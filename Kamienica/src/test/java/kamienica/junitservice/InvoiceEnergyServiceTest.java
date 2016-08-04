package kamienica.junitservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import kamienica.feature.invoice.InvoiceEnergy;
import kamienica.feature.invoice.InvoiceService;
import kamienica.feature.payment.PaymentEnergy;
import kamienica.feature.payment.PaymentService;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingService;

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
	@Override
	public void getList() {
		assertEquals(1, invoiceService.getEnergyInvoiceList().size());

	}

	@Override
	public void getById() {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Override
	public void add() {
		List<ReadingEnergy> list = readingService.getUnresolvedReadingsEnergy();
		assertEquals(31, list.get(1).getValue(), 0);
		InvoiceEnergy invoice = new InvoiceEnergy("112233", "test", new LocalDate(), 200, list.get(1));
		try {
			invoice.setBaseReading(list.get(1));
		} catch (Exception e) {
			fail();
		}
		invoiceService.save(invoice, Media.ENERGY);
		assertEquals(2, invoiceService.getEnergyInvoiceList().size());
		List<PaymentEnergy> paymentList = paymentService.getPaymentEnergyList();
		assertEquals(6, paymentList.size());

		assertEquals(30.30, paymentList.get(3).getPaymentAmount(), DELTA);
		assertEquals(48.48, paymentList.get(4).getPaymentAmount(), DELTA);
		assertEquals(121.212, paymentList.get(5).getPaymentAmount(), DELTA);

		list = readingService.getUnresolvedReadingsEnergy();
		assertEquals(1, list.size());
		assertEquals(LocalDate.parse("2016-07-01"), list.get(0).getReadingDate());
	}

	@Transactional
	@Override
	public void remove() {
		invoiceService.delete(1L, Media.ENERGY);
		List<ReadingEnergy> list = readingService.getUnresolvedReadingsEnergy();
		assertEquals(3, list.size());

	}

	@Transactional
	@Override
	@Ignore
	@Test
	public void update() {
		InvoiceEnergy invoice = new InvoiceEnergy("23423423", "test", new LocalDate(), 400,
				(ReadingEnergy) readingService.getById(6L, Media.ENERGY));
		invoice.setId(1L);
		List<PaymentEnergy> oldList = paymentService.getEnergyByInvoice(invoice);

		invoice.setTotalAmount(400.0);
		System.out.println(invoice);
		invoiceService.update(invoice, Media.ENERGY);

		List<PaymentEnergy> newList = paymentService.getEnergyByInvoice(invoice);

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
		List<ReadingAbstract> list = invoiceService.prepareForRegistration(Media.ENERGY);
		assertEquals(2, list.size());
		assertEquals(11, list.get(0).getValue(), 0);
		assertEquals(31, list.get(1).getValue(), 0);
	}

	@Test
	public void prepareForRegistration() throws InvalidDivisionException {
		apService.deleteByID(5L);
		List<ReadingAbstract> list = invoiceService.prepareForRegistration(Media.ENERGY);
		assertEquals(2, list.size());
		assertEquals(11, list.get(0).getValue(), 0);
		assertEquals(31, list.get(1).getValue(), 0);
	}

	@Transactional
	@Test(expected = InvalidDivisionException.class)
	public void shouldThrowInvalidDivisionExceptionWhilePreparing() throws InvalidDivisionException {
		divisionService.deleteAll();
		List<ReadingAbstract> list = invoiceService.prepareForRegistration(Media.ENERGY);
		assertEquals(0, list.size());
	}

}
