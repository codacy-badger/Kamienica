package kamienica.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.settings.SettingsService;

public class SettingsServiceTest extends AbstractServiceTest {

	@Autowired
	SettingsService service;

	@Test
	public void getById() {
		assertNotNull(service.getSettings());

	}

}
