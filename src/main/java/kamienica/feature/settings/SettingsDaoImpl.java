package kamienica.feature.settings;

import kamienica.model.jpa.dao.BasicDaoImpl;
import kamienica.model.entity.Settings;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

@Repository("settingsDao")
public class SettingsDaoImpl extends BasicDaoImpl<Settings> implements ISettingsDao {



}
