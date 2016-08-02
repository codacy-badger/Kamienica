package kamienica.controller.json;

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

import kamienica.core.Media;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.reading.ReadingService;

@RestController
@RequestMapping("/api/v1/readings")
public class ReadingsRestController {

	@Autowired
	ReadingService service;

	@RequestMapping(value = "/{media}", method = RequestMethod.GET)
	public ResponseEntity<List<? extends ReadingAbstract>> getList(@PathVariable Media media,
			@RequestParam(required = false) LocalDate date) {

		List<? extends ReadingAbstract> list;
		switch (media) {
		case ENERGY:
			if (date == null) {
				list = service.getReadingEnergy();
			} else {
				list = service.getReadingEnergyByDate(date);
			}
			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends ReadingAbstract>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<? extends ReadingAbstract>>(list, HttpStatus.OK);
		case GAS:
			if (date == null) {
				list = service.getReadingGas();
			} else {
				list = service.getReadingGasByDate(date.toString());
			}
			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends ReadingAbstract>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<? extends ReadingAbstract>>(list, HttpStatus.OK);
		case WATER:
			if (date == null) {
				list = service.getReadingWater();
			} else {
				list = service.getReadingWaterByDate(date.toString());
			}
			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends ReadingAbstract>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<? extends ReadingAbstract>>(list, HttpStatus.OK);

		default:
			return new ResponseEntity<List<? extends ReadingAbstract>>(HttpStatus.NO_CONTENT);
		}

	}

//	@RequestMapping(value = "/{media}{date}", method = RequestMethod.GET)
//	public ResponseEntity<List<? extends ReadingAbstract>> getListByDate(@PathVariable Media media,
//			@RequestParam LocalDate date) {
//		System.out.println("lista z datą");
//		List<? extends ReadingAbstract> list;
//		switch (media) {
//		case ENERGY:
//
//			if (list.isEmpty()) {
//				return new ResponseEntity<List<? extends ReadingAbstract>>(HttpStatus.NOT_FOUND);
//			}
//			return new ResponseEntity<List<? extends ReadingAbstract>>(list, HttpStatus.OK);
//		case GAS:
//			list = service.getReadingGasByDate(date.toString());
//			if (list.isEmpty()) {
//				return new ResponseEntity<List<? extends ReadingAbstract>>(HttpStatus.NOT_FOUND);
//			}
//			return new ResponseEntity<List<? extends ReadingAbstract>>(list, HttpStatus.OK);
//		case WATER:
//			list = service.getReadingWaterByDate(date.toString());
//			if (list.isEmpty()) {
//				return new ResponseEntity<List<? extends ReadingAbstract>>(HttpStatus.NOT_FOUND);
//			}
//			return new ResponseEntity<List<? extends ReadingAbstract>>(list, HttpStatus.OK);
//
//		default:
//			return new ResponseEntity<List<? extends ReadingAbstract>>(HttpStatus.NO_CONTENT);
//		}
//
//	}
}
