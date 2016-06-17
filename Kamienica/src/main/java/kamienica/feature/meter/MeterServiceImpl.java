package kamienica.feature.meter;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.Media;
import kamienica.dao.DaoInterface;

@Service
@Transactional
public class MeterServiceImpl implements MeterService {

	@Autowired
	DaoInterface<MeterEnergy> energy;

	@Autowired
	DaoInterface<MeterGas> gas;

	@Autowired
	DaoInterface<MeterWater> water;

	@Override
	public void saveGas(MeterGas meter) {
		gas.save(meter);

	}

	@Override
	public void saveWater(MeterWater meter) {
		water.save(meter);

	}

	@Override
	public void saveEnergy(MeterEnergy meter) {
		energy.save(meter);

	}

	@Override
	public void updateGas(MeterGas meter) {
		gas.update(meter);

	}

	@Override
	public void updateWater(MeterWater meter) {
		water.update(meter);

	}

	@Override
	public void updateEnergy(MeterEnergy meter) {
		energy.update(meter);

	}

	@Override
	public List<MeterEnergy> getEnergyList() {

		return energy.getList();
	}

	@Override
	public List<MeterGas> getGasList() {
		return gas.getList();
	}

	@Override
	public List<MeterWater> getWaterList() {
		return water.getList();
	}

	@Override
	public void deleteEnergyByID(Long id) {
		energy.deleteById(id);

	}

	@Override
	public void deleteGasByID(Long id) {
		gas.deleteById(id);

	}

	@Override
	public void deleteWaterByID(Long id) {
		water.deleteById(id);

	}

	@Override
	public MeterEnergy getEnergyByID(Long id) {
		return energy.getById(id);
	}

	@Override
	public MeterGas getGasByID(Long id) {
		return gas.getById(id);
	}

	@Override
	public MeterWater getWaterByID(Long id) {
		return water.getById(id);
	}

	@Override
	public Set<Long> getIdList(Media media) {
		switch (media) {
		case ENERGY:
			return energy.getIdList();
		case WATER:
			return water.getIdList();
		case GAS:
			return gas.getIdList();

		default:
			return null;
		}
	}

}
