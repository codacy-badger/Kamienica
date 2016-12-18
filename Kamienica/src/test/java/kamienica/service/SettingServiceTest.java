package kamienica.service;

import kamienica.feature.settings.SettingsService;
import kamienica.model.Settings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

public class SettingServiceTest extends AbstractServiceTest {

	@Autowired
	SettingsService service;

	@Test
	public void getList() {
		Settings settings = service.getSettings();

		assertEquals(true, settings.isCorrectDivision());

	}

	@Test
	@Transactional
	public void changeResolvementState() {
		service.changeDivisionState(false);
		assertEquals(false, service.isDivisionCorrect());
		service.changeDivisionState(true);
		assertEquals(true, service.isDivisionCorrect());
	}

}
