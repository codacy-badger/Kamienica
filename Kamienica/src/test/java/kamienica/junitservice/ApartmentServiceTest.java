package kamienica.junitservice;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;

public class ApartmentServiceTest extends AbstractServiceTest {

	@Autowired
	ApartmentService service;

	@Test
	public void getList() {
		List<Apartment> list = service.getList();
		assertEquals(4, list.size());
	}

	@Override
	public void getById() {
		Apartment apartment = service.getById(3L);
		assertEquals(2, apartment.getApartmentNumber());

	}

	@Override
	public void add() {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Override
	public void remove() {
		service.deleteByID(5L);
		assertEquals(4, service.getList().size());

	}

	@Override
	public void update() {
		Apartment ap = service.getById(4L);
		ap.setDescription("test");
		service.update(ap);
		assertEquals("test", service.getById(4L).getDescription());

	}

	@Test(expected = Exception.class)
	@Override
	public void addWithValidationError() {
		Apartment ap = new Apartment(78L, 1, "1234", "cośtam");
		service.save(ap);

	}

}
