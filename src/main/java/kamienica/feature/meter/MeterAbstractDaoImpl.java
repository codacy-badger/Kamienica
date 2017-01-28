package kamienica.feature.meter;

import kamienica.core.daoservice.BasicDaoImpl;
import kamienica.core.util.SecurityDetails;
import kamienica.model.Meter;
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
	public Set<Long> getIdListForActiveMeters() {
		Criteria criteria = createEntityCriteria().setProjection(Projections.property("id"));
		criteria.add(Restrictions.gt("deactivation", new LocalDate()));
		criteria.add(Restrictions.in("residence", SecurityDetails.getResidencesForOwner()));
		return new HashSet<>(criteria.list());
		
	}


}
