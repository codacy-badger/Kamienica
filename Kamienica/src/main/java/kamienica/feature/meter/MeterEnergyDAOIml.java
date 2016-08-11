package kamienica.feature.meter;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;

@Repository("meterEnergyDao")
public class MeterEnergyDAOIml extends AbstractDao<Long, MeterEnergy> implements MeterDao<MeterEnergy> {

	@Override
	public boolean ifMainExists() {

		Criteria crit = getSession().createCriteria(MeterEnergy.class);
		crit.add(Restrictions.eq("main", true));
		crit.setProjection(Projections.rowCount());

		Long count = (Long) crit.uniqueResult();
		return (count >= 1) ? true : false;
	}

}
