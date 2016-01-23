package kamienica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.MeterEnergyDAO;
import kamienica.dao.MeterGasDAO;
import kamienica.dao.MeterWaterDAO;
import kamienica.model.MeterEnergy;
import kamienica.model.MeterGas;
import kamienica.model.MeterWater;

@Service
@Transactional
public class MeterServiceImpl implements MeterService {

	@Autowired
	MeterEnergyDAO energy;

	@Autowired
	MeterGasDAO gas;

	@Autowired
	MeterWaterDAO water;

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
	public void deleteEnergyByID(int id) {
		energy.deleteEnergyByID(id);

	}

	@Override
	public void deleteGasByID(int id) {
		gas.deleteGasByID(id);

	}

	@Override
	public void deleteWaterByID(int id) {
		water.deleteWaterByID(id);

	}

	@Override
	public MeterEnergy getEnergyByID(int id) {
		return energy.getById(id);
	}

	@Override
	public MeterGas getGasByID(int id) {
		return gas.getById(id);
	}

	@Override
	public MeterWater getWaterByID(int id) {
		return water.getById(id);
	}

}
