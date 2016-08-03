package kamienica.junitservice;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
import kamienica.feature.payment.PaymentService;
import kamienica.feature.reading.ReadingAbstract;

public class InvoiceEnergyServiceTest extends AbstractServiceTest {

	@Autowired
	PaymentService paymentService;
	@Autowired
	InvoiceService service;
	@Autowired
	DivisionService divisionService;
	@Autowired
	ApartmentService apService;

	@Test
	@Override
	public void getList() {
		assertEquals(1, service.getEnergyInvoiceList().size());

	}

	@Override
	public void getById() {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Override
	public void add() {
		InvoiceEnergy invoice = service.getEnergyByID(3L);
		service.save(invoice, Media.ENERGY);
		assertEquals(2, service.getEnergyInvoiceList().size());

		assertEquals(6, paymentService.getPaymentEnergyList().size());

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

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
		List<ReadingAbstract> list = service.prepareForRegistration(Media.ENERGY);
		assertEquals(2, list.size());
		assertEquals(11, list.get(0).getValue(), 0);
		assertEquals(31, list.get(1).getValue(), 0);
	}

	@Test
	public void prepareForRegistration() throws InvalidDivisionException {
		apService.deleteByID(5L);
		List<ReadingAbstract> list = service.prepareForRegistration(Media.ENERGY);
		assertEquals(2, list.size());
		assertEquals(11, list.get(0).getValue(), 0);
		assertEquals(31, list.get(1).getValue(), 0);
	}

	@Transactional
	@Test(expected = InvalidDivisionException.class)
	public void shouldThrowInvalidDivisionExceptionWhilePreparing() throws InvalidDivisionException {
		divisionService.deleteAll();
		List<ReadingAbstract> list = service.prepareForRegistration(Media.ENERGY);
		assertEquals(0, list.size());
	}

}
