package kamienica.feature.settings;

import kamienica.core.dao.DaoInterface;
import kamienica.model.Settings;


public interface SettingsDao extends DaoInterface<Settings> {

	boolean isDivisionCorrect();

	void changeDivisionState(boolean state);
}
