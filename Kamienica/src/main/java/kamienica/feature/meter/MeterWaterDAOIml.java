package kamienica.feature.meter;

import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.dao.DaoInterface;

@Repository("meterWaterDao")
public class MeterWaterDAOIml extends AbstractDao<Long, MeterWater> implements DaoInterface<MeterWater> {

}
