package kamienica.feature.user_admin;

import kamienica.core.enums.Media;
import kamienica.model.Reading;
import kamienica.model.ReadingEnergy;
import kamienica.model.ReadingGas;
import kamienica.model.ReadingWater;
import kamienica.model.Apartment;

import java.util.HashMap;
import java.util.List;

public interface AdminUserService {

    HashMap<String, Object> getMainData();

    List<? extends Reading> getReadingsForTenant(Apartment apartment, Media media);

    List<ReadingEnergy> getReadingEnergyForTenant(Apartment apartment);

    List<ReadingWater> getReadingWaterForTenant(Apartment apartment);

    List<ReadingGas> getReadingGasForTenant(Apartment apartment);

}
