package kamienica.feature.owner;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;

import java.util.HashMap;
import java.util.List;

public interface IOwnerUserDataService {

    HashMap<String, Object> getMainData();

    List<Reading> getReadingsForTenant(Apartment apartment, Media media);

    Tenant getLoggedTenant();

}
