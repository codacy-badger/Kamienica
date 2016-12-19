package kamienica.service;

import kamienica.feature.apartment.ApartmentService;
import kamienica.model.Apartment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ApartmentServiceTest extends AbstractServiceTest {

	@Autowired
	ApartmentService service;

	@Test
	public void getList() {
		List<Apartment> list = service.getList();
		assertEquals(4, list.size());
	}
	
	@Test
	public void getPaginatedList() {
		List<Apartment> list = service.paginatedList(4, 1);
		final long apartmentId = list.get(0).getId();
		assertEquals(4L, apartmentId);
	}

	@Test
	public void getById() {
		Apartment apartment = service.getById(3L);
		assertEquals(2, apartment.getApartmentNumber());

	}

	@Transactional
	@Test
	public void remove() {
		service.deleteByID(5L);
		assertEquals(4, service.getList().size());

	}

	@Transactional
	@Test
	public void update() {
		Apartment ap = service.getById(4L);
		ap.setDescription("test");
		service.update(ap);
		assertEquals("test", service.getById(4L).getDescription());

	}

	@Test(expected = Exception.class)
	public void addWithValidationError() {
		Apartment ap = new Apartment(78L, 1, "1234", "cośtam");
		service.save(ap);

	}

}
