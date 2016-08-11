package kamienica.feature.settings;

import kamienica.core.dao.DaoInterface;


public interface SettingsDao extends DaoInterface<Settings> {

	public boolean isDivisionCorrect();

	public void changeDivisionState(boolean state);
}
