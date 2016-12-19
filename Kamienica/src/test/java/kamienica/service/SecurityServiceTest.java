package kamienica.service;

import kamienica.feature.tenant.TenantDao;
import kamienica.feature.user_admin.SecurityService;
import kamienica.model.Tenant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by macfol on 12/3/16.
 */
public class SecurityServiceTest extends AbstractServiceTest {

    private static final String USER_LOGIN = "folik@wp.pl";
    private static final String USER_PASSWD = "witaj";
    private static final SimpleGrantedAuthority ADMIN =  new SimpleGrantedAuthority("ROLE_ADMIN" );

    @Autowired
    private SecurityService securityService;
    @Autowired
    private TenantDao tenantDao;

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
        final UserDetails result = securityService.loadUserByUsername("dummy");
    }

    @Transactional
    @Test
    public void changePassword(){
        securityService.changePassword(USER_LOGIN, "witaj", "nowe");
        final Tenant tenant = tenantDao.loadByMail(USER_LOGIN);
        assertEquals("nowe", tenant.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void changePasswordWithIncorrectCredentials(){
        securityService.changePassword(USER_LOGIN, "dummy", "nowe");
        final Tenant tenant = tenantDao.loadByMail(USER_LOGIN);
        assertEquals("nowe", tenant.getPassword());
    }
}
