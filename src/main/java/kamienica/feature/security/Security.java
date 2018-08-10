package kamienica.feature.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface Security extends UserDetailsService {

    void changePassword(final String mail, final String oldPassword, final String newPwassword);
}
