package kamienica.feature.user_admin;

import kamienica.core.enums.Status;
import kamienica.feature.tenant.TenantService;
import kamienica.model.Apartment;
import kamienica.model.Residence;
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
public class SecurityService implements UserDetailsService {

    @Autowired
    private TenantService userDAO;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final Tenant tenant = userDAO.loadByMail(email);
        if (tenant == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + tenant.getRole()));

        final Apartment ap = tenant.getApartment();
        final Residence res = ap.getResidence();

        return new SecurityUser(tenant, tenant.getEmail(), tenant.getPassword(), ap, res,
                isActive(tenant), true, true, true, authorities);
    }


    public void changePassword(final String mail, final String oldPassowrd, final String newPwassword) throws UsernameNotFoundException {
        final Tenant tenant = userDAO.loadByMail(mail);
        if (tenant == null || !tenant.getPassword().equals(oldPassowrd)) {
            throw new UsernameNotFoundException("Username not found");
        } else {
            tenant.setPassword(newPwassword);
            userDAO.updateTenant(tenant);
        }

    }

    public SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private boolean isActive(Tenant tenant) {
        return tenant.getStatus().equals(Status.ACTIVE);
    }
}
