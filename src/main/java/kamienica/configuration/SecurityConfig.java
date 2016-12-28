package kamienica.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("superuser").password("override").roles("ADMIN");
		auth.userDetailsService(userDetailsService);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// added to handle local characters
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);

		http.authorizeRequests()
				.antMatchers("/", "/index").permitAll()
				.antMatchers("/Admin/**").access("hasRole('OWNER') or hasRole('ADMIN')")
				.antMatchers("/api/**").access("hasRole('OWNER') or hasRole('TENANT') or hasRole('ADMIN')")
				.antMatchers("/User/**").access("hasRole('OWNER') or hasRole('TENANT') or hasRole('ADMIN')")
				.and().formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")
				.successHandler(customSuccessHandler)
				.and().csrf()
				.and().exceptionHandling().accessDeniedPage("/403");

		// added to make rest part work
		// more on link:
		// https://spring.io/guides/tutorials/spring-security-and-angular-js/
		http.httpBasic().and().authorizeRequests().antMatchers("/api/**").access("hasRole('OWNER') or hasRole('TENANT') or hasRole('ADMIN')")
				.and().csrf().disable();


	}
}
