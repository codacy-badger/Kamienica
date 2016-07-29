package kamienica.junitservice;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kamienica.configuration.JUnitConfig;

@ContextConfiguration(classes = { JUnitConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractServiceTest{

	
	public abstract void getList();
	
	public abstract void getById();
	
	public abstract void add();
	
	public abstract void remove();
	
	public abstract void update();

}
