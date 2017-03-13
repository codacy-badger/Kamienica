package kamienica.dao;

import kamienica.configuration.DaoTest;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.model.entity.Apartment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;


public class ApartmentDaolmplTest extends DaoTest {

	@Autowired
	IApartmentDao apartemtnDao;

	@Test
	public void findById() {
		Apartment ap = apartemtnDao.getById(1L);
		assertNotNull(ap);
	}


}
