package kamienica.feature.settings;

import kamienica.model.entity.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SettingsServiceImpl implements ISettingsService {

	private final ISettingsDao dao;

	@Autowired
	public SettingsServiceImpl(ISettingsDao dao) {
		this.dao = dao;
	}

	@Override
	public Settings getSettings() {
		List<Settings> list = dao.getList();
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public void save(Settings settings) {
		if (settings.getId() == null) {
			dao.save(settings);
		} else {
			dao.update(settings);
		}

	}

}
