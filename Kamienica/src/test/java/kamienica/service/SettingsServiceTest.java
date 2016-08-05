package kamienica.service;

import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;

import kamienica.feature.settings.SettingsService;

public class SettingsServiceTest extends AbstractServiceTest {

	@Autowired
	SettingsService service;

	@Override
	public void getList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getById() {
		assertNotNull(service.getSettings());

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
