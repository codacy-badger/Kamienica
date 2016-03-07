package kamienica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReadingEnergyDaoTest extends EntityDaoImplTest {

	@Autowired
	ReadingEnergyDAO dao;

	@Test
	public void getUnresolvedReadings() {
		Assert.assertEquals(dao.getUnresolvedReadings().size(), 2);
	}
//	@Test
//	public void test() {
//		dao.
//		Assert.assertEquals(dao.getUnresolvedReadings().size(), 2);
//	}
}
