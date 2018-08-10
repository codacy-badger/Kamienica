package kamienica.core.util;

import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import kamienica.model.entity.SecurityUser;
import kamienica.model.entity.Tenant;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class SecurityDetails {

    private static final String SECURITY_ERROR_MSG = "Resource does not belong to the logged user";

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

    public static Residence getResidenceForOwner(final Long id) {
        final SecurityUser su = getPrincipal();
        return su.getResidencesOwned().stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new SecurityException(SECURITY_ERROR_MSG));
    }

    public static void addNewlyCreatedResidenceToSecurityContext(final Residence residence) {
        final SecurityUser su = getPrincipal();
        su.addResidence(residence);
    }

    public static void removeResidenceFromPrincipal(final Residence r) {
        final SecurityUser su = getPrincipal();
        su.removeResidence(r);
    }

    private static SecurityUser getPrincipal() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static void checkIfOwnsResidence(final Residence residence) {
        final SecurityUser su = getPrincipal();

        su.getResidencesOwned().stream()
                .filter(x -> x.getId().equals(residence.getId()))
                .findFirst()
                .orElseThrow(() -> new SecurityException(SECURITY_ERROR_MSG));
    }
}
