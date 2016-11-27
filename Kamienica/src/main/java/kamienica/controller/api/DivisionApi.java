package kamienica.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kamienica.model.Division;
import kamienica.feature.division.DivisionService;

@RestController
@RequestMapping("/api/v1/divisions")
public class DivisionApi extends AbstractApi {

	@Autowired
	private DivisionService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList() {
		final List<Division> list = service.getList();
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
