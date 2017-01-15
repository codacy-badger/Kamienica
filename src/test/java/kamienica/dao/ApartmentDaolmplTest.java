package kamienica.dao;

import kamienica.configuration.JUnitConfig;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.model.Apartment;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {JUnitConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ApartmentDaolmplTest {

	@Autowired
    ApartmentDao apartemtnDao;

	@Test
	@Ignore
	public void findById() {
		//TODO work out dao layer

		Apartment ap = apartemtnDao.getById(1L);

	}


}
