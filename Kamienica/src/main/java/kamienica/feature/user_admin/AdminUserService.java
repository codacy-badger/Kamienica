package kamienica.feature.user_admin;

import java.util.HashMap;
import java.util.List;

import kamienica.core.Media;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;

public interface AdminUserService {

	public HashMap<String, Object> getMainData();

	public List<? extends ReadingAbstract> getReadingsForTenant(Apartment apartment, Media media);

	public List<ReadingEnergy> getReadingEnergyForTenant(Apartment apartment);

	public List<ReadingWater> getReadingWaterForTenant(Apartment apartment);

	public List<ReadingGas> getReadingGasForTenant(Apartment apartment);

}
