package kamienica.feature.meter;

import java.util.List;
import java.util.Set;

import org.springframework.validation.BindingResult;

import kamienica.core.util.Media;

public interface MeterService {

	// public void saveGas(MeterGas meter);
	//
	// public void saveWater(MeterWater meter);
	//
	// public void saveEnergy(MeterEnergy meter);

	<T extends MeterAbstract> void save(T meter, Media media);

	<T extends MeterAbstract> void update(T meter, Media media);

	void delete(Long id, Media media);

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

	<T extends MeterAbstract> List<T> getList(Media media);

	// public void deleteEnergyByID(Long id);
	//
	// public void deleteGasByID(Long id);
	//
	// public void deleteWaterByID(Long id);

	<T extends MeterAbstract> T getById(Long id, Media media);

	// public MeterEnergy getEnergyByID(Long id);
	//
	// public MeterGas getGasByID(Long id);
	//
	// public MeterWater getWaterByID(Long id);
	
	<T extends MeterAbstract> void deactivateMeter(T meter, Media media);

	Set<Long> getIdList(Media media);

	Set<Long> getIdListForActiveMeters(Media media);

	boolean ifMainExists(Media media);

	<T extends MeterAbstract> void validateMeter(BindingResult result, Media media, T meter);

}
