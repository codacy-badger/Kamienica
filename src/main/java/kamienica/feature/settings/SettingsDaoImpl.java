package kamienica.feature.settings;

import kamienica.model.jpa.dao.BasicDaoImpl;
import kamienica.model.entity.Settings;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

@Repository("settingsDao")
public class SettingsDaoImpl extends BasicDaoImpl<Settings> implements ISettingsDao {

	@Override
	public boolean isDivisionCorrect() {
		Criteria crit = createEntityCriteria();
		crit.setProjection(Projections.property("correctDivision"));
		return (boolean) crit.uniqueResult();
	}

	@Override
	public void changeDivisionState(boolean state) {
		Query query = getSession().createSQLQuery("update settings set correctDivision = :state where id > 0");
		query.setBoolean("state", state);
		query.executeUpdate();

	}
	

}
