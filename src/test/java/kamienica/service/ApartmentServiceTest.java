package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.model.Apartment;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ApartmentServiceTest extends DatabaseTest {

	@Test
	public void getList() {
		List<Apartment> list = apartmentService.getList();
		assertEquals(4, list.size());
	}

	@Test
	public void getPaginatedList() {
		List<Apartment> list = apartmentService.paginatedList(4, 1);
		final long apartmentId = list.get(0).getId();
		assertEquals(4L, apartmentId);
	}

	@Test
	public void getById() {
		Apartment apartment = apartmentService.getById(3L);
		assertEquals(2, apartment.getApartmentNumber());

	}

	@Transactional
	@Test
	public void remove() {
		apartmentService.deleteByID(5L);
		assertEquals(4, apartmentService.getList().size());

	}

	@Transactional
	@Test
	public void update() {
		Apartment ap = apartmentService.getById(4L);
		ap.setDescription("test");
		apartmentService.update(ap);
		assertEquals("test", apartmentService.getById(4L).getDescription());

	}

	@Test(expected = Exception.class)
	public void addWithValidationError() {
		Apartment ap = new Apartment(78L, 1, "1234", "cośtam");
		apartmentService.save(ap);

	}

}
