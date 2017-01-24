package kamienica.feature.settings;

import kamienica.core.daoservice.BasicDao;
import kamienica.model.Settings;


public interface SettingsDao extends BasicDao<Settings> {

	boolean isDivisionCorrect();

	void changeDivisionState(boolean state);
}
