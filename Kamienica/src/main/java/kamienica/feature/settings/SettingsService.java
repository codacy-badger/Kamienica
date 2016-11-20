package kamienica.feature.settings;

public interface SettingsService {

	Settings getSettings();

	void delete();

	void save(Settings settings);

	boolean isDivisionCorrect();

	void changeDivisionState(boolean state);
}
