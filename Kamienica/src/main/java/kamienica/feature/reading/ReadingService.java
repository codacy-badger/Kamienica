package kamienica.feature.reading;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import kamienica.core.Media;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.invoice.InvoiceGas;

public interface ReadingService {

	public ReadingEnergy getEnergyById(Long id);

	public ReadingGas getGasById(Long id);

	public ReadingWater getWaterById(Long id);

	public void saveGasList(List<ReadingGas> reading, LocalDate localDate);

	public void saveWaterList(List<ReadingWater> reading, LocalDate localDate);

	public void saveEnergyList(List<ReadingEnergy> reading, LocalDate localDate);

	public void updateEnergy(ReadingEnergy reading);

	public void updateEnergyList(List<ReadingEnergy> readings);

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

	public void deleteReadingEnergy(Long id);

	public void deleteReadingGas(Long id);

	public void deleteReadingWater(Long id);

	public void deleteReadingEnergyList(List<ReadingEnergy> list);

	public void deleteReadingGasList(List<ReadingGas> list);

	public void deleteReadingWaterList(List<ReadingWater> list);

	public List<ReadingEnergy> getPreviousReadingEnergy(String date);

	public List<ReadingGas> getPreviousReadingGas(String date);

	public List<ReadingWater> getPreviousReadingWater(String date);

	public List<ReadingEnergy> getLatestEnergyReadings(Set<Long> meterIdList);

	public List<ReadingGas> getLatestGasReadings(Set<Long> meterIdList);

	public List<ReadingWater> getLatestWaterReadings(Set<Long> meterIdList);

	public HashMap<String, List<ReadingWater>> getWaterReadingsForGasConsumption(InvoiceGas invoice);

	public List<ReadingEnergy> getUnresolvedReadingsEnergy();

	public List<ReadingGas> getUnresolvedReadingsGas();

	public List<ReadingWater> getUnresolvedReadingsWater();

	public int countLatestGasDays();

	public int countLatestWaterDays();

	public int countLatestEnergyDays();

	public List<? extends ReadingAbstract> getReadingsForTenant(Apartment apartment, Media media);

	public Set<Long> getEnergyIdList();

	// public List<ReadingAbstract> getReadingsForTenant2(Apartment apartment,
	// Media media);

	// public <T extends ReadingAbstract> List<T> getReadingsForTenant(Apartment
	// apartment, Media media);
}
