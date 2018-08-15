package kamienica.configuration;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringSecurityTestConfig.class, JUnitConfig.class})
@ContextConfiguration(classes = {JUnitConfig.class})
@DirtiesContext
@TestPropertySource(locations="classpath:test.properties")
public class ContextTest {



    private static final String HIBERNATE_BEAN = "dataSource";
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        final Set<String> result = Sets.newHashSet(applicationContext.getBeanDefinitionNames());
        assertTrue(result.contains(HIBERNATE_BEAN));
    }
}
