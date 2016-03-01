package kamienica.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.ReadingEnergyDAO;
import kamienica.dao.ReadingGasDAO;
import kamienica.dao.ReadingWaterDAO;
import kamienica.model.Apartment;
import kamienica.model.InvoiceGas;
import kamienica.model.ReadingEnergy;
import kamienica.model.ReadingGas;
import kamienica.model.ReadingWater;

@Service
@Transactional
public class ReadingServiceImpl implements ReadingService {

	@Autowired
	ReadingEnergyDAO energy;
	@Autowired
	ReadingWaterDAO water;
	@Autowired
	ReadingGasDAO gas;

	@Override
	public List<ReadingEnergy> getReadingEnergy() {
		return energy.getList();
	}

	@Override
	public List<ReadingEnergy> getReadingEnergyForTenant(Apartment aparmtent) {
		return energy.getListForTenant(aparmtent);
	}

	@Override
	public List<ReadingGas> getReadingGasForTenant(Apartment aparmtent) {
		return gas.getListForTenant(aparmtent);
	}

	@Override
	public List<ReadingWater> getReadingWaterForTenant(Apartment aparmtent) {
		return water.getListForTenant(aparmtent);
	}

	@Override
	public List<ReadingGas> getReadingGas() {
		return gas.getList();
	}

	@Override
	public List<ReadingWater> getReadingWater() {
		return water.getList();
	}

	@Override
	public void deleteReadingEnergy(int id) {
		energy.deleteById(id);

	}

	@Override
	public void deleteReadingGas(int id) {
		gas.deleteById(id);

	}

	@Override
	public void deleteReadingWater(int id) {
		water.deleteById(id);

	}

	@Override
	public HashMap<Integer, ReadingEnergy> getLatestEnergyReadings() {
		return energy.getLatestReadingsMap();
	}

	@Override
	public List<ReadingEnergy> getPreviousReadingEnergy(String date) {

		return energy.getPrevious(date);
	}

	@Override
	public List<ReadingGas> getPreviousReadingGas(String date) {
		return gas.getPrevious(date);
	}

	@Override
	public List<ReadingWater> getPreviousReadingWater(String date) {
		return water.getPrevious(date);
	}

	@Override
	public List<ReadingEnergy> getReadingEnergyByDate(String date) {

		return energy.getByDate(date);
	}

	@Override
	public HashMap<Integer, ReadingGas> getLatestGasReadings() {

		return gas.getLatestReadingsMap();
	}

	@Override
	public HashMap<Integer, ReadingWater> getLatestWaterReadings() {
		return water.getLatestReadingsMap();
	}

	@Override
	public List<ReadingGas> getReadingGasByDate(String date) {
		return gas.getByDate(date);
	}

	@Override
	public List<ReadingWater> getReadingWaterByDate(String date) {
		return water.getByDate(date);
	}



	@Override
	public void saveGasList(List<ReadingGas> reading) {
		for (ReadingGas i : reading) {
			gas.save(i);
		}
	}

	@Override
	public void saveWaterList(List<ReadingWater> reading) {
		for (ReadingWater i : reading) {
			water.save(i);
		}

	}

	@Override
	public void saveEnergyList(List<ReadingEnergy> reading) {

		energy.saveList(reading);

	}

	@Override
	public void updateEnergy(ReadingEnergy reading) {
		energy.update(reading);

	}

	@Override
	public void updateGas(ReadingGas reading) {
		gas.update(reading);

	}

	@Override
	public void updateWater(ReadingWater reading) {
		water.update(reading);

	}

	@Override
	public ReadingEnergy getEnergyById(int id) {
		return energy.getById(id);
	}

	@Override
	public ReadingGas getGasById(int id) {
		return gas.getById(id);
	}

	@Override
	public ReadingWater getWaterById(int id) {
		return water.getById(id);
	}

	@Override
	public List<ReadingEnergy> getUnresolvedReadingsEnergy() {
		return energy.getUnresolvedReadings();
	}

	@Override
	public List<ReadingGas> getUnresolvedReadingsGas() {
		return gas.getUnresolvedReadings();
	}

	@Override
	public List<ReadingWater> getUnresolvedReadingsWater() {
		return water.getUnresolvedReadings();
	}

	@Override
	public HashMap<String, List<ReadingWater>> getWaterReadingsForGasConsumption(InvoiceGas invoice) {
		return water.getWaterReadingForGasConsumption2(invoice);
	}

	@Override
	public void deleteReadingEnergyList(List<ReadingEnergy> list) {
		for (ReadingEnergy reading : list) {
			energy.deleteById(reading.getId());
		}

	}

	@Override
	public void deleteReadingGasList(List<ReadingGas> list) {
		for (ReadingGas reading : list) {
			gas.deleteById(reading.getId());
		}

	}

	@Override
	public void deleteReadingWaterList(List<ReadingWater> list) {
		for (ReadingWater reading : list) {
			water.deleteById(reading.getId());
		}

	}

}
