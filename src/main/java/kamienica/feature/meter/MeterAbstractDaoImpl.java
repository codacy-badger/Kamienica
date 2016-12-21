package kamienica.feature.meter;

import kamienica.core.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

public class MeterAbstractDaoImpl<T extends Meter> extends AbstractDao<T> {

	public boolean ifMainExists() {

		Criteria crit = getSession().createCriteria(persistentClass);
		crit.add(Restrictions.eq("main", true));
		crit.setProjection(Projections.rowCount());

		Long count = (Long) crit.uniqueResult();
		return (count >= 1);
	}


	@SuppressWarnings("unchecked")
	public Set<Long> getIdListForActiveMeters() {
		Criteria criteria = createEntityCriteria().setProjection(Projections.property("id"));
		criteria.add(Restrictions.gt("deactivation", new LocalDate()));
		return new HashSet<Long>(criteria.list());
		
	}
	
		
}