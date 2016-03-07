package kamienica.dao;

import java.util.Date;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import kamienica.model.Tenant;

public class TenantDaoImplTest extends EntityDaoImplTest {

	@Autowired
	TenantDao dao;

// 
//    @Override
//    protected IDataSet getDataSet() throws Exception {
//      IDataSet[] datasets = new IDataSet[] {
//              new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Apartment.xml")),
//              new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Tenant.xml"))
//      };
//      return new CompositeDataSet(datasets);
//    }
   
	
	@Test
	public void findById() {
		Assert.assertNotNull(dao.getById(1));
		Assert.assertNull(dao.getById(5));
	}

	@Test
	public void saveTenant() {
		dao.save(getSample());
		Assert.assertEquals(dao.getList().size(), 5);
	}

	@Test
	public void deleteById() {
		dao.delete(1);
		Assert.assertEquals(dao.getList().size(), 3);
	}

	@Test
	public void deleteInvalidId() {
		dao.delete(8);
		Assert.assertEquals(dao.getList().size(), 4);
	}

	@Test
	public void findAll() {
		Assert.assertEquals(dao.getList().size(), 4);
	}

	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void saveDuplicateEmail() {
		dao.save(getDuplcate());
		Assert.assertEquals(dao.getList().size(), 4);
	}

	public Tenant getSample() {
		Tenant ap = new Tenant();
		ap.setFirstName("erstse");
		ap.setLastName("dfgdfg");
		ap.setEmail("gdf@wp.pl");
		ap.setFirstName("ab");
		ap.setApartment(null);
		ap.setMovementDate(new Date());
		ap.setPhone("345");
		ap.setRole("ADMIN");
		ap.setStatus("ACTIVE");
		ap.setPassword("witaj");
		return ap;
	}

	public Tenant getDuplcate() {
		Tenant ap = new Tenant();
		ap.setFirstName("erstse");
		ap.setLastName("dfgdfg");
		ap.setEmail("c@wp.pl");
		ap.setFirstName("ab");
		ap.setApartment(null);
		ap.setMovementDate(new Date());
		ap.setPhone("345");
		ap.setRole("ADMIN");
		ap.setStatus("ACTIVE");
		ap.setPassword("witaj");
		return ap;
	}

}
