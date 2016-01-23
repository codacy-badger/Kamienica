package kamienica.dao;

import java.util.List;

import kamienica.model.MeterWater;

public interface MeterWaterDAO {

	public void save(MeterWater meter);

	public void update(MeterWater meter);

	public List<MeterWater> getList();

	public MeterWater getById(int id);

	public void deleteWaterByID(int id);

}
