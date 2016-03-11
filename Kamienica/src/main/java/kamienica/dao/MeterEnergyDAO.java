package kamienica.dao;

import java.util.List;

import kamienica.model.MeterEnergy;

public interface MeterEnergyDAO {


	public void save(MeterEnergy meter);
	
	public void update(MeterEnergy meter);
	
	public List<MeterEnergy> getList();
	
	public MeterEnergy getById(int id);

	public void deleteEnergyByID(int id);

}
