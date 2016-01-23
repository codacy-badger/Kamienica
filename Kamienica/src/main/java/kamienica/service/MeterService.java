package kamienica.service;

import java.util.List;

import kamienica.model.MeterEnergy;
import kamienica.model.MeterGas;
import kamienica.model.MeterWater;

public interface MeterService {

	public void saveGas(MeterGas meter);

	public void saveWater(MeterWater meter);

	public void saveEnergy(MeterEnergy meter);

	public void updateGas(MeterGas meter);

	public void updateWater(MeterWater meter);

	public void updateEnergy(MeterEnergy meter);

	public List<MeterEnergy> getEnergyList();

	public List<MeterGas> getGasList();

	public List<MeterWater> getWaterList();

	public void deleteEnergyByID(int id);

	public void deleteGasByID(int id);

	public void deleteWaterByID(int id);

	public MeterEnergy getEnergyByID(int id);

	public MeterGas getGasByID(int id);

	public MeterWater getWaterByID(int id);
}
