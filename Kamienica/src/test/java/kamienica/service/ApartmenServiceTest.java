package kamienica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.dao.EntityDaoImplTest;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;

public class ApartmenServiceTest extends EntityDaoImplTest {

	@Autowired
	ApartmentService service;

	@Test
	public void getList() {
		Assert.assertEquals(service.getList().size(), 5);
	}

	@Test
	public void getById() {
		Apartment apartment = service.getById(1L);
		Assert.assertNotNull(apartment);
		Assert.assertEquals(apartment.getDescription(), "Część Wspólna");
	}

	@Rollback
	@Test(dependsOnMethods = { "getList" })
	public void deleteById() {
		service.deleteByID(5L);
		Assert.assertEquals(service.getList().size(), 4);
	}

}
