package kamienica.feature.owner;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Residence;
import kamienica.model.entity.SecurityUser;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class OwnerUserDataService implements IOwnerUserDataService {

    private final IApartmentDao apartmentDao;

    @Autowired
    public OwnerUserDataService(final IApartmentDao apartmentDao) {
        this.apartmentDao = apartmentDao;
    }



    @Override
    public List<Reading> getReadingsForTenant(Apartment apartment, Media media) {
        return null;
    }


    public Tenant getLoggedTenant() {
        SecurityUser su = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return su.getTenant();
    }
}
