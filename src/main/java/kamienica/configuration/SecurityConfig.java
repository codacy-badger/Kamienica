package kamienica.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final CustomSuccessHandler customSuccessHandler;
//    private final BasicAuthenticationPoint basicAuthenticationPoint;

//    @Autowired
//    public SecurityConfig(final UserDetailsService userDetailsService/*, final CustomSuccessHandler customSuccessHandler, BasicAuthenticationPoint basicAuthenticationPoint*/) {
//        this.userDetailsService = userDetailsService;
////        this.customSuccessHandler = customSuccessHandler;
////        this.basicAuthenticationPoint = basicAuthenticationPoint;
//    }
//
////    @Autowired
////    public void configureGlobalSecurity(final AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication().withUser("superuser").password("override").roles("ADMIN");
////        auth.userDetailsService(userDetailsService);
////
////    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/", "/index.html", "login.html").permitAll()
//                .antMatchers("/owner/**").access("hasRole('OWNER') or hasRole('ADMIN')")
//                .antMatchers("/api/**").access("hasRole('OWNER') or hasRole('TENANT') or hasRole('ADMIN')")
//                .antMatchers("/user/**").access("hasRole('OWNER') or hasRole('TENANT') or hasRole('ADMIN')")
//                .and().csrf()
//                .and()
//                .formLogin()
////                .loginPage("/login.html")
////                .loginProcessingUrl("/perform_login")
////                .failureUrl("/login.html?error")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/index.html")
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//
//        //TODO add csrf protection https://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//
////        http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);
////         added to make rest part work
////         more on link:
////         https://spring.io/guides/tutorials/spring-security-and-angular-js/
////        http.httpBasic()
////                .and()
////                .authorizeRequests()
////                .antMatchers("/api/**")
////                .access("hasRole('OWNER') or hasRole('TENANT') or hasRole('ADMIN')")
////                .and()
////                .csrf()
////                .disable();
//
//
//    }
//
//    @Autowired
//    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("superuser").password("override").roles("ADMIN");
//        auth.userDetailsService(userDetailsService)
//                /*.passwordEncoder(passwordEncoder())*/;
//    }


    @Autowired
    public SecurityConfig(final UserDetailsService userDetailsService, final CustomSuccessHandler customSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.customSuccessHandler = customSuccessHandler;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/*").authenticated()
                .antMatchers("/views/owner/*").access("hasRole('OWNER')")
                .antMatchers("/views/user/*").access("hasRole('OWNER') or hasRole('USER')")
                .antMatchers("/swagger-ui.html").access("hasRole('ADMIN') or hasRole('OWNER')")
                .antMatchers("/h2-console/*").access("hasRole('ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/perform_login")
                .failureUrl("/login.html?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(customSuccessHandler)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        //TODO add csrf protection https://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("superuser").password("override").roles("ADMIN");
        auth.userDetailsService(userDetailsService);


//                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
