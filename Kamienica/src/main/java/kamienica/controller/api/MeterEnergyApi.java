package kamienica.controller.api;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kamienica.core.util.Media;
import kamienica.feature.meter.MeterAbstract;
import kamienica.feature.meter.MeterService;

@RestController
@RequestMapping("/api/v1/meters")
public class MeterRestController {

	@Autowired
	MeterService service;

	@RequestMapping(value = "/{media}", method = RequestMethod.GET)
	public ResponseEntity<List<? extends MeterAbstract>> getList(@PathVariable Media media,
			@RequestParam(required = false) LocalDate date) {

		List<? extends MeterAbstract> list;
		switch (media) {
		case ENERGY:
			list = service.getList(Media.ENERGY);
			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends MeterAbstract>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<? extends MeterAbstract>>(list, HttpStatus.OK);

		case GAS:
			list = service.getList(Media.GAS);
			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends MeterAbstract>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<? extends MeterAbstract>>(list, HttpStatus.OK);

		case WATER:
			list = service.getList(Media.WATER);
			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends MeterAbstract>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<? extends MeterAbstract>>(list, HttpStatus.OK);

		default:
			return new ResponseEntity<List<? extends MeterAbstract>>(HttpStatus.NO_CONTENT);
		}

	}
}
