package kamienica.feature.settings;

import kamienica.dao.DaoInterface;


public interface SettingsDao extends DaoInterface<Settings> {

	public boolean isDivisionCorrect();

	public void changeDivisionState(boolean state);
}
