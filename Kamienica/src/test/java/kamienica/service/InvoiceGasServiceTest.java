package kamienica.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.util.Media;
import kamienica.core.util.WaterHeatingSystem;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;
import kamienica.feature.division.DivisionService;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.invoice.InvoiceService;
import kamienica.feature.payment.PaymentGas;
import kamienica.feature.payment.PaymentService;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingService;
import kamienica.feature.settings.Settings;
import kamienica.feature.settings.SettingsService;

public class InvoiceGasServiceTest extends AbstractServiceTest {

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
	@Autowired
	SettingsService settingsService;

	@Test
	public void getList() {
		assertEquals(1, invoiceService.getGasInvoiceList().size());

	}

	

	@Test
	@Transactional
	public void add() {
		List<ReadingGas> list = readingService.getUnresolvedReadingsGas();
		assertEquals(196, list.get(1).getValue(), 0);
		InvoiceGas invoice = new InvoiceGas("112233", "test", new LocalDate(), 200, list.get(1));

		invoiceService.save(invoice, Media.GAS);
		assertEquals(2, invoiceService.getGasInvoiceList().size());
		List<PaymentGas> paymentList = paymentService.getPaymentGasList();

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
	public void addForFirstReadingWithSharedWaterHeating() {
		List<ReadingGas> list = readingService.getUnresolvedReadingsGas();
		assertEquals(114, list.get(0).getValue(), 0);
		InvoiceGas invoice = new InvoiceGas("112233", "test", new LocalDate(), 200, list.get(0));

		invoiceService.save(invoice, Media.GAS);
		assertEquals(2, invoiceService.getGasInvoiceList().size());
		List<PaymentGas> paymentList = paymentService.getPaymentGasList();

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
	public void addForFirstReadingWithSeparateWaterHeating() {
		List<ReadingGas> list = readingService.getUnresolvedReadingsGas();
		Settings setings = settingsService.getSettings();
		setings.setWaterHeatingSystem(WaterHeatingSystem.INDIVIDUAL_GAS);
		settingsService.save(setings);
		assertEquals(114, list.get(0).getValue(), 0);
		InvoiceGas invoice = new InvoiceGas("112233", "test", new LocalDate(), 200, list.get(0));

		invoiceService.save(invoice, Media.GAS);
		assertEquals(2, invoiceService.getGasInvoiceList().size());
		List<PaymentGas> paymentList = paymentService.getPaymentGasList();

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
				(ReadingGas) readingService.getById(6L, Media.GAS));
		invoice.setId(1L);
		List<PaymentGas> oldList = paymentService.getPaymentGasByInvoice(invoice);

		invoice.setTotalAmount(400.0);
		System.out.println(invoice);
		invoiceService.update(invoice, Media.GAS);

		List<PaymentGas> newList = paymentService.getPaymentGasByInvoice(invoice);

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
		List<ReadingAbstract> list = invoiceService.prepareForRegistration(Media.GAS);
		assertEquals(2, list.size());
		assertEquals(11, list.get(0).getValue(), 0);
		assertEquals(31, list.get(1).getValue(), 0);
	}

	@Transactional
	@Test
	public void prepareForRegistration() throws InvalidDivisionException {
		// apService.deleteByID(5L);
		List<ReadingAbstract> list = invoiceService.prepareForRegistration(Media.GAS);
		assertEquals(2, list.size());
		assertEquals(114, list.get(0).getValue(), 0);
		assertEquals(196, list.get(1).getValue(), 0);

	}

	@Transactional
	@Test(expected = InvalidDivisionException.class)
	public void shouldThrowInvalidDivisionExceptionWhilePreparing() throws InvalidDivisionException {
		divisionService.deleteAll();

		List<ReadingAbstract> list = invoiceService.prepareForRegistration(Media.GAS);
		assertEquals(0, list.size());
	}

}
