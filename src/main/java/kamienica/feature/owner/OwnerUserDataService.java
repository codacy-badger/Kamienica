package kamienica.feature.owner;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.SecurityUser;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerUserDataService implements IOwnerUserDataService {


    @Override
    public List<Reading> getReadingsForTenant(Apartment apartment, Media media) {
        return null;
    }


    public Tenant getLoggedTenant() {
        SecurityUser su = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return su.getTenant();
    }
}
