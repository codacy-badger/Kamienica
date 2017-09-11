package kamienica.feature.settings;

import kamienica.model.entity.Residence;
import kamienica.model.entity.Settings;

import java.util.List;

public interface ISettingsService {

	Settings getSettings(Residence residence);

	List<Settings> list();

	void save(Settings settings);

}
