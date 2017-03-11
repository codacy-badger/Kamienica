package kamienica.feature.user_admin;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;

import java.util.HashMap;
import java.util.List;

public interface IOwnerUserDataService {

    HashMap<String, Object> getMainData();

    List<Reading> getReadingsForTenant(Apartment apartment, Media media);

    List<Reading> getReadingEnergyForTenant(Apartment apartment);

    List<Reading> getReadingWaterForTenant(Apartment apartment);

    List<Reading> getReadingGasForTenant(Apartment apartment);

    Tenant getLoggedTenant();

}
