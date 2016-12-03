package kamienica.feature.user_admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import kamienica.core.enums.Status;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantService;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	TenantService userDAO;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Tenant tenant = userDAO.loadByMail(email);
		if (tenant == null) {
			throw new UsernameNotFoundException("Username not found");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + tenant.getRole()));

		return new SecurityUser(tenant, tenant.getEmail(), tenant.getPassword(), tenant.getApartment(),
				tenant.getStatus().equals(Status.ACTIVE), true, true, true, authorities);
	}

	public void changePassword(String mail, String oldPassowrd, String newPwassword) throws UsernameNotFoundException {
		Tenant tenant = userDAO.loadByMail(mail);
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

}
