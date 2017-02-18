package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Tenant;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Transactional
public class SecurityServiceTest extends ServiceTest {

    private static final String USER_PASSWD = "witaj";
    private static final SimpleGrantedAuthority ADMIN =  new SimpleGrantedAuthority("ROLE_OWNER" );

    @Test
    public void loginWithCorrectCredentials() {
        final UserDetails result = securityService.loadUserByUsername(FIRST_OWNER_MAIL);
        assertEquals(true, result.isAccountNonExpired());
        assertEquals(true, result.isAccountNonLocked());
        assertEquals(true, result.isCredentialsNonExpired());
        assertEquals(true, result.isEnabled());
        assertEquals(USER_PASSWD, result.getPassword());
        assertEquals(FIRST_OWNER_MAIL, result.getUsername());

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
        securityService.changePassword(FIRST_OWNER_MAIL, "witaj", "nowe");
        final Tenant tenant = tenantService.loadByMail(FIRST_OWNER_MAIL);
        assertEquals("nowe", tenant.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void changePasswordWithIncorrectCredentials(){
        securityService.changePassword(FIRST_OWNER_MAIL, "dummy", "nowe");
        final Tenant tenant = tenantService.loadByMail(FIRST_OWNER_MAIL);
        assertEquals("nowe", tenant.getPassword());
    }
}
