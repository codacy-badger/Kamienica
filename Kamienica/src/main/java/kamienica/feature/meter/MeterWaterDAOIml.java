package kamienica.feature.meter;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;

@Repository("meterWaterDao")
public class MeterWaterDAOIml extends AbstractDao<MeterWater> implements MeterDao<MeterWater> {

	@Override
	public boolean ifMainExists() {

		Criteria crit = getSession().createCriteria(MeterWater.class);
		crit.add(Restrictions.eq("main", true));
		crit.setProjection(Projections.rowCount());
		Long count = (Long) crit.uniqueResult();
		return (count >= 1) ? true : false;
	}

}
