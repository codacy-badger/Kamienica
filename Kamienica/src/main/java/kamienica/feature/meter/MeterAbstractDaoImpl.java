package kamienica.feature.meter;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

import kamienica.core.dao.AbstractDao;

public class MeterAbstractDaoImpl<T extends MeterAbstract> extends AbstractDao<T> {

	public boolean ifMainExists() {

		Criteria crit = getSession().createCriteria(persistentClass);
		crit.add(Restrictions.eq("main", true));
		crit.setProjection(Projections.rowCount());

		Long count = (Long) crit.uniqueResult();
		return (count >= 1) ? true : false;
	}


	@SuppressWarnings("unchecked")
	public Set<Long> getIdListForActiveMeters() {
		Criteria criteria = createEntityCriteria().setProjection(Projections.property("id"));
		criteria.add(Restrictions.ge("deactivation", new LocalDate()));
		return new HashSet<Long>(criteria.list());
		
	}
	
		
}
