package kamienica.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kamienica.feature.settings.Settings;
import kamienica.feature.settings.SettingsService;

public class SettingServiceTest extends AbstractServiceTest {

	@Autowired
	SettingsService service;

	@Override
	public void getList() {
		Settings settings = service.getSettings();
		System.out.println(settings);
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

	@Override
	public void getById() {
		// TODO Auto-generated method stub

	}

	@Override
	public void add() {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addWithValidationError() {
		// TODO Auto-generated method stub

	}

}
