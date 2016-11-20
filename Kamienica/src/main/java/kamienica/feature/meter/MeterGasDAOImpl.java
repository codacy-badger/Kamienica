package kamienica.feature.meter;

import org.springframework.stereotype.Repository;

@Repository("meterGasDao")
public class MeterGasDAOImpl extends MeterAbstractDaoImpl<MeterGas> implements MeterDao<MeterGas> {

	
//	@Override
//	public boolean ifMainExists() {
//
//		Criteria crit = getSession().createCriteria(MeterGas.class);
//		crit.add(Restrictions.eq("main", true));
//		crit.setProjection(Projections.rowCount());
//		Long count = (Long) crit.uniqueResult();
//		return (count >= 1) ? true : false;
//	}

}
