package kamienica.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kamienica.configuration.JUnitConfig;

@ContextConfiguration(classes = { JUnitConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractServiceTest {

	/**
	 * difference factor for calculated data
	 */
	protected final double DELTA = 0.5;

}
