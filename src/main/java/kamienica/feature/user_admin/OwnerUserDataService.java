package kamienica.feature.user_admin;

import kamienica.core.enums.Media;
import kamienica.model.*;

import java.util.HashMap;
import java.util.List;

public interface OwnerUserDataService {

    HashMap<String, Object> getMainData();

    List<? extends Reading> getReadingsForTenant(Apartment apartment, Media media);

    List<ReadingEnergy> getReadingEnergyForTenant(Apartment apartment);

    List<ReadingWater> getReadingWaterForTenant(Apartment apartment);

    List<ReadingGas> getReadingGasForTenant(Apartment apartment);

    SecurityUser getCurrentUser();

    Tenant getCurrentTenant();

}
