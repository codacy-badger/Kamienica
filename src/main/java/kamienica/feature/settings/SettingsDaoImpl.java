package kamienica.feature.settings;

import kamienica.model.entity.Settings;
import kamienica.model.jpa.dao.BasicDao;
import org.springframework.stereotype.Repository;

@Repository("settingsDao")
public class SettingsDaoImpl extends BasicDao<Settings> implements ISettingsDao {

}
