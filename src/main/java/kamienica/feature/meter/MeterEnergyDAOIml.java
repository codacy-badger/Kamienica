package kamienica.feature.meter;

import kamienica.model.MeterEnergy;
import org.springframework.stereotype.Repository;

@Repository("meterEnergyDao")
public class MeterEnergyDAOIml extends MeterAbstractDaoImpl<MeterEnergy> implements MeterDao<MeterEnergy> {

//	@Override
//	public boolean ifMainExists() {
//
//		Criteria crit = getSession().createCriteria(MeterEnergy.class);
//		crit.add(Restrictions.eq("main", true));
//		crit.setProjection(Projections.rowCount());
//
//		Long count = (Long) crit.uniqueResult();
//		return (count >= 1) ? true : false;
//	}

}
