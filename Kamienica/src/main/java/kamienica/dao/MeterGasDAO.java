package kamienica.dao;

import java.util.List;

import kamienica.model.MeterGas;

public interface MeterGasDAO {

	public void save(MeterGas meter);

	public void update(MeterGas meter);

	public List<MeterGas> getList();

	public MeterGas getById(int id);

	public void deleteGasByID(int id);
	
}
