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

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/*
	 * This method extracts the roles of currently logged-in user and returns
	 * appropriate URL according to his/her role.
	 */
	protected String determineTargetUrl(Authentication authentication) {
		String url;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		if (isAdmin(roles) || isOwner(roles)) {
			url = "/Admin/home";
		} else if (isTenant(roles)) {
			url = "/User/userHome";
		} else {
			url = "/403";
		}
		return url;
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

	@Override
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	@Override
	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}
