package kamienica.dao;

import org.springframework.stereotype.Repository;

import kamienica.model.MeterWater;

@Repository("meterWaterDao")
public class MeterWaterDAOIml extends AbstractDao<Integer, MeterWater> implements DaoInterface<MeterWater> {

}
