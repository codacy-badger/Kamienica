package kamienica.controller.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kamienica.feature.division.Division;
import kamienica.feature.division.DivisionService;

@RestController
@RequestMapping("/api/v1/divisions")
public class DivisionRestController {

	@Autowired
	DivisionService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Division>> getList() {
		List<Division> list = service.getList();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Division>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Division>>(list, HttpStatus.OK);
	}

}
