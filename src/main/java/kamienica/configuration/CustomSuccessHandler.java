package kamienica.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(final HttpServletRequest request, final HttpServletResponse response,
						  final Authentication authentication) throws IOException {

		final String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/*
	 * This method extracts the roles of currently logged-in user and returns
	 * appropriate URL according to his/her role.
	 */
	private String determineTargetUrl(Authentication authentication) {
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		final List<String> roles = new ArrayList<>();

		for (final GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (isAdmin(roles) || isOwner(roles)) {
			return "/views/owner/home.html";
		} else if (isTenant(roles)) {
			return"/views/user/home.html";
		} else {
			return "/403.html";
		}
	}

	private boolean isTenant(List<String> roles) {
        return roles.contains("ROLE_TENANT");
    }
	private boolean isOwner(List<String> roles) {
        return roles.contains("ROLE_OWNER");
    }
	private boolean isAdmin(List<String> roles) {
        return roles.contains("ROLE_ADMIN");
    }

}
