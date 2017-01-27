package kamienica.feature.user_admin;

import kamienica.core.enums.Status;
import kamienica.feature.residence.ResidenceService;
import kamienica.feature.residenceownership.ResidenceOwnershipDao;
import kamienica.feature.tenant.TenantService;
import kamienica.model.Residence;
import kamienica.model.SecurityUser;
import kamienica.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityServiceImpl implements UserDetailsService {

    private static final String ERROR_MSG = "Login or Passowords are invalid";
    private final TenantService tenantService;
    private final ResidenceService residenceService;
    private final ResidenceOwnershipDao ownershipDao;

    @Autowired
    public SecurityServiceImpl(TenantService tenantService, ResidenceService residenceService, ResidenceOwnershipDao ownershipDao) {
        this.tenantService = tenantService;
        this.residenceService = residenceService;
        this.ownershipDao = ownershipDao;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final Tenant tenant = findTenant(email);

        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + tenant.getRole()));

        List<Residence> residencesOwned = getResidnecesForOwner(tenant);

        return new SecurityUser(tenant, isActive(tenant), authorities, residencesOwned);
    }

    public void changePassword(final String mail, final String oldPassowrd, final String newPwassword)
            throws UsernameNotFoundException {
        final Tenant tenant = findTenant(mail);
        checkOldPassword(tenant, oldPassowrd);
        comparePasswords(oldPassowrd, newPwassword);


        tenant.setPassword(newPwassword);
        tenantService.update(tenant);
    }

    private void checkOldPassword(Tenant tenant, String oldPassowrd) {
        if (!tenant.getPassword().equals(oldPassowrd)) {
            throw new UsernameNotFoundException(ERROR_MSG);
        }
    }

    private void comparePasswords(final String oldPassowrd, final String newPwassword) {
        if (newPwassword.equals(oldPassowrd)) {
            throw new UsernameNotFoundException("Nowe hasło musi być inne niż stare");
        }

    }

    public SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        return tenant.getStatus().equals(Status.ACTIVE);
    }
}
