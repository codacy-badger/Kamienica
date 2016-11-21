package kamienica.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kamienica.core.util.ApiResponse;
import kamienica.core.util.Media;
import kamienica.core.util.Message;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.meter.Meter;
import kamienica.feature.meter.MeterEnergy;
import kamienica.feature.meter.MeterGas;
import kamienica.feature.meter.MeterService;
import kamienica.feature.meter.MeterWater;

@RestController
@RequestMapping("/api/v1/meters")
public class MeterApi extends AbstractApi {

	@Autowired
	MeterService service;

	@RequestMapping(value = "/{media}", method = RequestMethod.GET)
	public ResponseEntity<?> getList(@PathVariable Media media, @RequestParam(required = false) LocalDate date) {

		List<? extends Meter> list = service.getList(media);
		if (list.isEmpty()) {
			return new ResponseEntity<List<Apartment>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<? extends Meter>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/ENERGY", method = RequestMethod.POST)
	public ResponseEntity<?> createEnergy(@Valid @RequestBody MeterEnergy meter, BindingResult result) {
		if (result.hasErrors()) {
			ApiResponse message = new ApiResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<ApiResponse>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			service.save(meter, Media.ENERGY);
		} catch (ConstraintViolationException e) {
			result.rejectValue("serialNumber", "error.serialNumber", DUPLICATE_VALUE);
			Map<String, String> test = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				test.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(test, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Meter>(meter, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/ENERGY/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEnergy(@PathVariable("id") Long id, @Valid @RequestBody MeterEnergy meter, BindingResult result) {
		if (result.hasErrors()) {
			ApiResponse message = new ApiResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<ApiResponse>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			service.update(meter, Media.ENERGY);
		} catch (ConstraintViolationException e) {
			result.rejectValue("serialNumber", "error.serialNumber", DUPLICATE_VALUE);
			Map<String, String> test = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				test.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(test, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Meter>(meter, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/GAS", method = RequestMethod.POST)
	public ResponseEntity<?> createGas( @Valid @RequestBody MeterGas meter, BindingResult result) {
		if (result.hasErrors()) {
			ApiResponse message = new ApiResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<ApiResponse>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			service.save(meter, Media.GAS);
		} catch (ConstraintViolationException e) {
			result.rejectValue("serialNumber", "error.serialNumber", DUPLICATE_VALUE);
			Map<String, String> test = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				test.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(test, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Meter>(meter, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/GAS/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateGas(@PathVariable("id") Long id, @Valid @RequestBody MeterGas meter, BindingResult result) {
		if (result.hasErrors()) {
			ApiResponse message = new ApiResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<ApiResponse>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			service.update(meter, Media.GAS);
		} catch (ConstraintViolationException e) {
			result.rejectValue("serialNumber", "error.serialNumber", DUPLICATE_VALUE);
			Map<String, String> test = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				test.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(test, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Meter>(meter, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/WATER", method = RequestMethod.POST)
	public ResponseEntity<?> createWater(@Valid @RequestBody MeterWater meter, BindingResult result) {
		if (result.hasErrors()) {
			ApiResponse message = new ApiResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<ApiResponse>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			service.save(meter, Media.WATER);
		} catch (ConstraintViolationException e) {
			result.rejectValue("serialNumber", "error.serialNumber", DUPLICATE_VALUE);
			Map<String, String> test = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				test.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(test, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Meter>(meter, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/WATER/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateWater(@Valid @RequestBody MeterWater meter, BindingResult result) {
		if (result.hasErrors()) {
			ApiResponse message = new ApiResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<ApiResponse>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			service.update(meter, Media.WATER);
		} catch (ConstraintViolationException e) {
			result.rejectValue("serialNumber", "error.serialNumber", DUPLICATE_VALUE);
			Map<String, String> test = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				test.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(test, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Meter>(meter, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{media}/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Message> deleteUser(@PathVariable("media") Media media, @PathVariable("id") Long id) {
		Message message = new Message("OK", null);
		try {
			service.delete(id, media);
		} catch (Exception e) {
			message.setMessage(CONSTRAINT_VIOLATION);
			message.setException(e.toString());
			return new ResponseEntity<Message>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	
}
