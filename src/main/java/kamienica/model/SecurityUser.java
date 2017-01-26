package kamienica.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class SecurityUser extends User {

    private static final long serialVersionUID = 1L;
    private Tenant tenant;
    private List<Residence> residencesOwned;

    public SecurityUser(Tenant tenant, boolean enabled, Collection<? extends GrantedAuthority> authorities, List<Residence> residencesOwned) {
        super(tenant.getEmail(), tenant.getPassword(), enabled, true, true, true, authorities);
        this.tenant = tenant;
        this.residencesOwned = residencesOwned;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public List<Residence> getResidencesOwned() {
        return residencesOwned;
    }

    public void setResidencesOwned(List<Residence> residencesOwned) {
        this.residencesOwned = residencesOwned;
    }
}
