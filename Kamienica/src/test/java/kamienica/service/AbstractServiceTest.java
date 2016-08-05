package kamienica.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kamienica.configuration.JUnitConfig;

@ContextConfiguration(classes = { JUnitConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractServiceTest {

	/**
	 * difference factor for calculated data
	 */
	protected final double DELTA = 0.35;

	@Test
	public abstract void getList();

	@Test
	public abstract void getById();

	@Test
	@Transactional
	public abstract void add();

	@Test
	public abstract void remove();

	@Test
	@Transactional
	public abstract void update();

	@Test
	@Transactional
	public abstract void addWithValidationError();

}
