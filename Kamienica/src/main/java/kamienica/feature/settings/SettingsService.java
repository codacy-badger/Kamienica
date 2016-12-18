package kamienica.feature.settings;

import kamienica.model.Settings;

public interface SettingsService {

	Settings getSettings();

	void delete();

	void save(Settings settings);

	boolean isDivisionCorrect();

	void changeDivisionState(boolean state);
}
