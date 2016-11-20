package kamienica.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kamienica.core.util.Media;
import kamienica.feature.reading.Reading;
import kamienica.feature.reading.ReadingService;

@RestController
@RequestMapping("/api/v1/readings")
public class ReadingApi {

	@Autowired
	ReadingService service;

	@RequestMapping(value = "/{media}", method = RequestMethod.GET)
	public ResponseEntity<?> getList(@PathVariable Media media) {

		List<? extends Reading> list = service.getList(media);
			if (list.isEmpty()) {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<? extends Reading>>(list, HttpStatus.OK);
	}

}
