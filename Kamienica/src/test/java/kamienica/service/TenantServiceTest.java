package kamienica.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantService;

public class TenantServiceTest extends AbstractServiceTest {

	@Autowired
	TenantService service;

	@Test
	public void getList() {
		List<Tenant> list = service.getList();

		assertEquals(5, list.size());
	}

	@Test
	public void getActiveTenants() {

		List<Tenant> list = service.getCurrentTenants();
		assertEquals(3, list.size());
	}

	@Test
	public void loadByMail() {
		Tenant tenant = service.loadByMail("folik@wp.pl");
		assertNotNull(tenant);
		assertEquals("Maciej Folik", tenant.getFullName());
	}

}
