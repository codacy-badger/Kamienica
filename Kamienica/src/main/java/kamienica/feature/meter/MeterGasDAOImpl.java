package kamienica.feature.meter;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;

@Repository("meterGasDao")
public class MeterGasDAOImpl extends AbstractDao<Long, MeterGas> implements MeterDao<MeterGas> {

	
	@Override
	public boolean ifMainExists() {

		Criteria crit = getSession().createCriteria(MeterGas.class);
		crit.add(Restrictions.eq("main", true));
		crit.setProjection(Projections.rowCount());
		Long count = (Long) crit.uniqueResult();
		return (count >= 1) ? true : false;
	}

}
