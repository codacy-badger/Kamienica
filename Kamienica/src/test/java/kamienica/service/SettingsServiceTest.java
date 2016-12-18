package kamienica.service;

import kamienica.feature.settings.SettingsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class SettingsServiceTest extends AbstractServiceTest {

	@Autowired
	SettingsService service;

	@Test
	public void getById() {
		assertNotNull(service.getSettings());

	}

}
