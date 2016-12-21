package kamienica.feature.settings;

import kamienica.model.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	private SettingsDao dao;

	@Override
	public Settings getSettings() {
		List<Settings> list = dao.getList();
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public void delete() {

	}

	@Override
	public void save(Settings settings) {
		if (settings.getId() == null) {
			dao.save(settings);
		} else {
			dao.update(settings);
		}

	}

	@Override
	public boolean isDivisionCorrect() {

		return dao.isDivisionCorrect();
	}

	@Override
	public void changeDivisionState(boolean state) {
		dao.changeDivisionState(state);

	}

}
