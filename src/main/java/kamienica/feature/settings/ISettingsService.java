package kamienica.feature.settings;

import kamienica.model.entity.Settings;

public interface ISettingsService {

	Settings getSettings();

	void save(Settings settings);

}
