package kamienica.feature.settings;

import kamienica.core.dao.DaoInterface;


public interface SettingsDao extends DaoInterface<Settings> {

	boolean isDivisionCorrect();

	void changeDivisionState(boolean state);
}
