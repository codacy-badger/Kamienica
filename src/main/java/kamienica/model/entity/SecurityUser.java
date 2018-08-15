package kamienica.model.entity;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {

    private static final long serialVersionUID = 1L;
    private final Tenant tenant;
    private final List<Residence> residencesOwned;

    public SecurityUser(Tenant tenant, boolean enabled, Collection<? extends GrantedAuthority> authorities, List<Residence> residencesOwned) {
        super(tenant.getEmail(), tenant.getPassword(), enabled, true, true, true, authorities);
        this.tenant = tenant;
        this.residencesOwned = residencesOwned;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public List<Residence> getResidencesOwned() {
        return residencesOwned;
    }

    public void addResidence(final Residence residence) {
        this.residencesOwned.add(residence);
    }

    public void removeResidence(final Residence residence) {
        residencesOwned.remove(residence);
    }

}
