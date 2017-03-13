package kamienica.configuration;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = {JUnitConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DaoTest {
}
