package kamienica.feature.user_admin;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.tenant.Tenant;

public class SecurityUser extends User {

	private Tenant tenant;
	private Apartment apartment;



	public SecurityUser(Tenant tenant, String username, String password, Apartment apartment, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.apartment = apartment;

		this.tenant = tenant;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	@Override
	public String toString() {
		return "SecurityUser [tenant=" + tenant + ", apartment=" + apartment + "]";
	}

}
