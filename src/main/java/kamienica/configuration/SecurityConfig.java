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

    private final UserDetailsService userDetailsService;
    private final CustomSuccessHandler customSuccessHandler;
    private final BasicAuthenticationPoint basicAuthenticationPoint;

    @Autowired
    public SecurityConfig(final UserDetailsService userDetailsService, final CustomSuccessHandler customSuccessHandler, BasicAuthenticationPoint basicAuthenticationPoint) {
        this.userDetailsService = userDetailsService;
        this.customSuccessHandler = customSuccessHandler;
        this.basicAuthenticationPoint = basicAuthenticationPoint;
    }

    @Autowired
    public void configureGlobalSecurity(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("superuser").password("override").roles("ADMIN");
        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // added to handle local characters
        final CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);



//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/", "/api/**").permitAll()
//                .anyRequest().authenticated();
//        http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);

        http.authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/Admin/**").access("hasRole('OWNER') or hasRole('ADMIN')")
                .antMatchers("/api/**").access("hasRole('OWNER') or hasRole('TENANT') or hasRole('ADMIN')")
                .antMatchers("/User/**").access("hasRole('OWNER') or hasRole('TENANT') or hasRole('ADMIN')")
                .and().formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")
                .successHandler(customSuccessHandler)
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/403");
        http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);
//         added to make rest part work
//         more on link:
//         https://spring.io/guides/tutorials/spring-security-and-angular-js/
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/**")
                .access("hasRole('OWNER') or hasRole('TENANT') or hasRole('ADMIN')")
                .and()
                .csrf()
                .disable();


    }
}
