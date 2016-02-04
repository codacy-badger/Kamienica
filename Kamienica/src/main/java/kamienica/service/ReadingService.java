package kamienica.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import kamienica.model.Apartment;
import kamienica.model.PaymentAbstract;
import kamienica.model.ReadingAbstract;
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

	public List<ReadingEnergy> getLatestEnergyReadingsList();

	public List<ReadingGas> getLatestGasReadingsList();

	public List<ReadingWater> getLatestWaterReadingsList();
	
	

	public List<ReadingEnergy> getPreviousReadingEnergy(String date);

	public List<ReadingGas> getPreviousReadingGas(String date);

	public List<ReadingWater> getPreviousReadingWater(String date);

	public HashMap<Integer, ReadingEnergy> getLatestEnergyReadings();

	public HashMap<Integer, ReadingGas> getLatestGasReadings();

	public HashMap<Integer, ReadingWater> getLatestWaterReadings();

	public List<Date> getEnergyReadingDatesForPayment(PaymentAbstract payment);

	public List<Date> getWaterReadingDatesForPayment(PaymentAbstract payment);

	public List<Date> getGasReadingDatesForPayment(PaymentAbstract payment);

	public List<ReadingWater> getWaterReadingsForGasConsumption(ReadingAbstract reading);
	
	public List<ReadingEnergy> getUnresolvedReadingsEnergy();
	
	public List<ReadingGas> getUnresolvedReadingsGas();
	
	public List<ReadingWater> getUnresolvedReadingsWater();
}
