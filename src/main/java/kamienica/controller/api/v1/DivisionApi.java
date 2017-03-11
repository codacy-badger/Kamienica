package kamienica.controller.api.v1;

import kamienica.feature.division.IDivisionService;
import kamienica.model.entity.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/divisions")
public class DivisionApi extends AbstractApi {

	@Autowired
	private IDivisionService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList() {
		final List<Division> list = service.getList();
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
