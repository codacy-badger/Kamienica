package kamienica.feature.settings;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import kamienica.core.dao.AbstractDao;

@Repository("settingsDao")
public class SettingsDaoImpl extends AbstractDao<Settings> implements SettingsDao {

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
