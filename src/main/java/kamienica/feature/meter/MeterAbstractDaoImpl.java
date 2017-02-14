package kamienica.feature.meter;

import kamienica.core.daoservice.BasicDaoImpl;
import kamienica.model.Meter;
import kamienica.model.Residence;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

public class MeterAbstractDaoImpl<T extends Meter> extends BasicDaoImpl<T> {

	public boolean ifMainExists() {

		Criteria crit = getSession().createCriteria(persistentClass);
		crit.add(Restrictions.eq("main", true));
		crit.setProjection(Projections.rowCount());

		Long count = (Long) crit.uniqueResult();
		return (count >= 1);
	}


	@SuppressWarnings("unchecked")
	public Set<Long> getIdListForActiveMeters(final Residence r) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.gt("deactivation", new LocalDate()));
		criteria.add(Restrictions.eq("residence", r));
		criteria.setProjection(Projections.property("id"));

		return new HashSet<>(criteria.list());
		
	}


}
