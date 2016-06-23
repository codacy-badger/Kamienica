package kamienica.feature.meter;

import java.util.List;
import java.util.Set;

import kamienica.core.Media;

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

	public void deleteEnergyByID(Long id);

	public void deleteGasByID(Long id);

	public void deleteWaterByID(Long id);

	public MeterEnergy getEnergyByID(Long id);

	public MeterGas getGasByID(Long id);

	public MeterWater getWaterByID(Long id);
	
	public Set<Long> getIdList(Media media);
	

	public boolean ifMainExists(Media media);

}
