package kamienica.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import kamienica.model.MyUser;
import kamienica.model.Tenant;
import kamienica.model.UserStatus;

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

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + tenant.getRole()));
		MyUser myUser = new MyUser(tenant.getEmail(), tenant.getPassword(), tenant.getApartment(), tenant.getFullName(),
				tenant.getStatus().equals(UserStatus.ACTIVE.getUserStatus()), true, true, true, authorities);

		return myUser;
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

}
