package kamienica.core.util;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import kamienica.model.entity.SecurityUser;
import kamienica.model.entity.Tenant;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class SecurityDetails {

    public static Tenant getLoggedTenant() {
        SecurityUser su = getPrincipal();
        return su.getTenant();
    }

    public static Apartment getApartmentForLoggedTenant() {
        SecurityUser su = getPrincipal();
        return su.getTenant().fetchApartment();
    }

    public static List<Residence> getResidencesForOwner() {
        SecurityUser su = getPrincipal();
        return su.getResidencesOwned();
    }

    public static void removeResidenceFromPrincipal(final Residence r) {
        final List<Residence> residences = getPrincipal().getResidencesOwned();
        for (int i = 0; i < residences.size(); i++) {
            if (residences.get(i).equals(r)) {
                residences.remove(i);
                break;
            }
        }

    }

    private static SecurityUser getPrincipal() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
