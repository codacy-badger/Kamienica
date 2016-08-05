package kamienica.feature.settings;

public interface SettingsService {

	public Settings getSettings();

	public void delete();

	public void save(Settings settings);

}
