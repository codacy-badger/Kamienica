package kamienica.feature.meter;

import kamienica.model.MeterWater;
import org.springframework.stereotype.Repository;

@Repository("meterWaterDao")
public class MeterWaterDAOIml extends MeterAbstractDaoImpl<MeterWater> implements MeterDao<MeterWater> {

//	@Override
//	public boolean ifMainExists() {
//
//		Criteria crit = getSession().createCriteria(MeterWater.class);
//		crit.add(Restrictions.eq("main", true));
//		crit.setProjection(Projections.rowCount());
//		
//		Long count = (Long) crit.uniqueResult();
//		return (count >= 1) ? true : false;
//	}

}
