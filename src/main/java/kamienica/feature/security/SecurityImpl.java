package kamienica.feature.security;

import java.util.ArrayList;
import java.util.List;
import kamienica.feature.residence.IResidenceService;
import kamienica.feature.tenant.ITenantService;
import kamienica.model.entity.Residence;
import kamienica.model.entity.SecurityUser;
import kamienica.model.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityImpl implements Security {

    private static final String ERROR_MSG = "Login or Passowords are invalid";
    private final ITenantService tenantService;
    private final IResidenceService residenceService;

    @Autowired
    public SecurityImpl(final ITenantService tenantService, final IResidenceService residenceService) {
        this.tenantService = tenantService;
        this.residenceService = residenceService;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final Tenant tenant = findTenant(email);
        final List<GrantedAuthority> authorities = new ArrayList<>();
        final List<Residence> residencesOwned = getResidnecesForOwner(tenant);

        authorities.add(new SimpleGrantedAuthority("ROLE_" + tenant.getRole()));
        return new SecurityUser(tenant, isActive(tenant), authorities, residencesOwned);
    }

    @Override
    public void changePassword(final String mail, final String oldPassowrd, final String newPwassword)
            throws UsernameNotFoundException {
        final Tenant tenant = findTenant(mail);
        checkOldPassword(tenant, oldPassowrd);
        comparePasswords(oldPassowrd, newPwassword);

        tenant.setPassword(newPwassword);
        tenantService.update(tenant);
    }

    private void checkOldPassword(Tenant tenant, String oldPassword) {
        if (!tenant.getPassword().equals(oldPassword)) {
            throw new UsernameNotFoundException(ERROR_MSG);
        }
    }

    private void comparePasswords(final String oldPassowrd, final String newPwassword) {
        if (newPwassword.equals(oldPassowrd)) {
            throw new UsernameNotFoundException("Nowe hasło musi być inne niż stare");
        }

    }

    private List<Residence> getResidnecesForOwner(final Tenant t) {
       return residenceService.listForFirstLogin(t);
    }

    private Tenant findTenant(final String email) {
        final Tenant tenant = tenantService.loadByMail(email);
        if (tenant == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return tenant;
    }

    private boolean isActive(Tenant tenant) {
        return tenant.checkIsActive();
    }
}
