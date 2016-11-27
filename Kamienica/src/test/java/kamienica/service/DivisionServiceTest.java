package kamienica.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kamienica.model.Division;
import kamienica.feature.division.DivisionService;

public class DivisionServiceTest extends AbstractServiceTest {

	@Autowired
	DivisionService service;

	@Test
	public void getList() {
		List<Division> list = service.getList();
		assertEquals(12, list.size());
	}



}
