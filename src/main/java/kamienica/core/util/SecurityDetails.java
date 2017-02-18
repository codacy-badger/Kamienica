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
        return su.getTenant().getApartment();
    }

    public static List<Residence> getResidencesForOwner() {
        SecurityUser su = getPrincipal();
        return su.getResidencesOwned();
    }

    private static SecurityUser getPrincipal() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
