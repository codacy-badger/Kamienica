package kamienica.feature.meter;

import kamienica.model.MeterWater;
import org.springframework.stereotype.Repository;

@Repository("meterWaterDao")
public class MeterWaterDAOIml extends MeterAbstractDaoImpl<MeterWater> implements MeterDao<MeterWater> {


}
