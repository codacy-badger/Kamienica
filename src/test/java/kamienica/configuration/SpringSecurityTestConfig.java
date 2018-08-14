package kamienica.configuration;

import kamienica.feature.residence.IResidenceService;
import kamienica.feature.security.SecurityImpl;
import kamienica.feature.tenant.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;

@TestConfiguration
public class SpringSecurityTestConfig {

  @Autowired
  ITenantService tenantService;
  @Autowired
  IResidenceService residenceService;

  @Bean
  @Primary
  public UserDetailsService userDetailsService() {
    return new SecurityImpl(tenantService, residenceService);
  }
}
