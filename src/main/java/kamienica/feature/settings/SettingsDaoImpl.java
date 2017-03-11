package kamienica.feature.settings;

import kamienica.model.entity.Settings;
import kamienica.model.jpa.dao.BasicDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("settingsDao")
public class SettingsDaoImpl extends BasicDaoImpl<Settings> implements ISettingsDao {



}
