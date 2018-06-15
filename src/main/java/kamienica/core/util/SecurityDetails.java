package kamienica.core.util;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import kamienica.model.entity.SecurityUser;
import kamienica.model.entity.Tenant;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class SecurityDetails {

    public static Tenant getLoggedTenant() {
        final SecurityUser su = getPrincipal();
        return su.getTenant();
    }

    public static Apartment getApartmentForLoggedTenant() {
        final SecurityUser su = getPrincipal();
        return su.getTenant().fetchApartment();
    }

    public static List<Residence> getResidencesForOwner() {
        final SecurityUser su = getPrincipal();
        return su.getResidencesOwned();
    }

    public static void removeResidenceFromPrincipal(final Residence r) {
        final List<Residence> residences = getPrincipal().getResidencesOwned();
        residences.remove(r);
    }

    private static SecurityUser getPrincipal() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static void checkIfOwnsResidence(final Residence residence) {
        final SecurityUser su = getPrincipal();

        su.getResidencesOwned().stream()
                .filter(x -> x.getId().equals(residence.getId()))
                .findFirst()
                .orElseThrow(() -> new SecurityException("Resource does not belong to the logged user"));
    }
}
