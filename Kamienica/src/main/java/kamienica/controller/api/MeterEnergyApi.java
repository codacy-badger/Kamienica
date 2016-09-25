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
import kamienica.feature.apartment.Apartment;
import kamienica.feature.meter.MeterAbstract;
import kamienica.feature.meter.MeterService;

@RestController
@RequestMapping("/api/v1/meters")
public class MeterEnergyApi {

	@Autowired
	MeterService service;

	@RequestMapping(value="/{media}",method = RequestMethod.GET)
	public ResponseEntity<?> getList(@PathVariable Media media,
			@RequestParam(required = false) LocalDate date) {

		List<? extends MeterAbstract> list = service.getList(media);
		if (list.isEmpty()) {
			return new ResponseEntity<List<Apartment>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<? extends MeterAbstract>>(list, HttpStatus.OK);
	}
}
