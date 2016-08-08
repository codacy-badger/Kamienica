package kamienica.feature.meter;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import kamienica.core.dao.AbstractDao;

public class MeterAbstractDaoImpl<T extends MeterAbstract> extends AbstractDao<T> {

	public boolean ifMainExists() {

		Criteria crit = getSession().createCriteria(persistentClass);
		crit.add(Restrictions.eq("main", true));
		crit.setProjection(Projections.rowCount());

		Long count = (Long) crit.uniqueResult();
		return (count >= 1) ? true : false;
	}
}
