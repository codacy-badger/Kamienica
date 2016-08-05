package kamienica.feature.meter;

import java.util.List;
import java.util.Set;

import org.springframework.validation.BindingResult;

import kamienica.core.Media;

public interface MeterService {

	// public void saveGas(MeterGas meter);
	//
	// public void saveWater(MeterWater meter);
	//
	// public void saveEnergy(MeterEnergy meter);

	public <T extends MeterAbstract> void save(T meter, Media media);

	public <T extends MeterAbstract> void update(T meter, Media media);

	public void delete(Long id, Media media);

	// public void updateGas(MeterGas meter);
	//
	// public void updateWater(MeterWater meter);
	//
	// public void updateEnergy(MeterEnergy meter);

	// public List<MeterEnergy> getEnergyList();
	//
	// public List<MeterGas> getGasList();
	//
	// public List<MeterWater> getWaterList();

	public <T extends MeterAbstract> List<T> getList(Media media);

	// public void deleteEnergyByID(Long id);
	//
	// public void deleteGasByID(Long id);
	//
	// public void deleteWaterByID(Long id);

	public <T extends MeterAbstract> T getById(Long id, Media media);

	// public MeterEnergy getEnergyByID(Long id);
	//
	// public MeterGas getGasByID(Long id);
	//
	// public MeterWater getWaterByID(Long id);

	public Set<Long> getIdList(Media media);

	public boolean ifMainExists(Media media);

	public<T extends MeterAbstract> void validateMeter(BindingResult result, Media media, T meter);

}
