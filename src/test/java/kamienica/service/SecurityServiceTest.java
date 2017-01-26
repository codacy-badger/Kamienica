package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.Tenant;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SecurityServiceTest extends ServiceTest {

    private static final String USER_LOGIN = "folik@wp.pl";
    private static final String USER_PASSWD = "witaj";
    private static final SimpleGrantedAuthority ADMIN =  new SimpleGrantedAuthority("ROLE_OWNER" );

    @Test
    public void loginWithCorrectCredentials() {
        final UserDetails result = securityService.loadUserByUsername(USER_LOGIN);
        assertEquals(true, result.isAccountNonExpired());
        assertEquals(true, result.isAccountNonLocked());
        assertEquals(true, result.isCredentialsNonExpired());
        assertEquals(true, result.isEnabled());
        assertEquals(USER_PASSWD, result.getPassword());
        assertEquals(USER_LOGIN, result.getUsername());

        final Collection<? extends GrantedAuthority> authoritiesList = result.getAuthorities();
        assertTrue(authoritiesList.contains(ADMIN));

    }

    @Test(expected = UsernameNotFoundException.class)
    public void loginWithInCorrectCredentials() {
        securityService.loadUserByUsername("dummy");
    }

    @Transactional
    @Test
    public void changePassword(){
        securityService.changePassword(USER_LOGIN, "witaj", "nowe");
        final Tenant tenant = tenantService.loadByMail(USER_LOGIN);
        assertEquals("nowe", tenant.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void changePasswordWithIncorrectCredentials(){
        securityService.changePassword(USER_LOGIN, "dummy", "nowe");
        final Tenant tenant = tenantService.loadByMail(USER_LOGIN);
        assertEquals("nowe", tenant.getPassword());
    }
}
