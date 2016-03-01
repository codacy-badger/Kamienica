package kamienica.service;

import java.util.HashMap;
import java.util.List;

import kamienica.model.Apartment;
import kamienica.model.InvoiceGas;
import kamienica.model.ReadingEnergy;
import kamienica.model.ReadingGas;
import kamienica.model.ReadingWater;

public interface ReadingService {

	public ReadingEnergy getEnergyById(int id);

	public ReadingGas getGasById(int id);

	public ReadingWater getWaterById(int id);

	public void saveGasList(List<ReadingGas> reading);

	public void saveWaterList(List<ReadingWater> reading);

	public void saveEnergyList(List<ReadingEnergy> reading);

	public void updateEnergy(ReadingEnergy reading);

	public void updateGas(ReadingGas reading);

	public void updateWater(ReadingWater reading);

	public List<ReadingEnergy> getReadingEnergy();

	public List<ReadingEnergy> getReadingEnergyForTenant(Apartment apartment);

	public List<ReadingEnergy> getReadingEnergyByDate(String date);

	public List<ReadingGas> getReadingGas();

	public List<ReadingGas> getReadingGasByDate(String date);

	public List<ReadingWater> getReadingWater();

	public List<ReadingWater> getReadingWaterForTenant(Apartment apartment);

	public List<ReadingGas> getReadingGasForTenant(Apartment apartment);

	public List<ReadingWater> getReadingWaterByDate(String date);

	public void deleteReadingEnergy(int id);

	public void deleteReadingGas(int id);

	public void deleteReadingWater(int id);

	public void deleteReadingEnergyList(List<ReadingEnergy> list);

	public void deleteReadingGasList(List<ReadingGas> list);

	public void deleteReadingWaterList(List<ReadingWater> list);

	public List<ReadingEnergy> getPreviousReadingEnergy(String date);

	public List<ReadingGas> getPreviousReadingGas(String date);

	public List<ReadingWater> getPreviousReadingWater(String date);

	public HashMap<Integer, ReadingEnergy> getLatestEnergyReadings();

	public HashMap<Integer, ReadingGas> getLatestGasReadings();

	public HashMap<Integer, ReadingWater> getLatestWaterReadings();

	public HashMap<String, List<ReadingWater>> getWaterReadingsForGasConsumption(InvoiceGas invoice);

	public List<ReadingEnergy> getUnresolvedReadingsEnergy();

	public List<ReadingGas> getUnresolvedReadingsGas();

	public List<ReadingWater> getUnresolvedReadingsWater();
}
