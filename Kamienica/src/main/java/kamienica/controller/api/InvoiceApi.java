package kamienica.controller.api;

import kamienica.core.enums.Media;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.message.ApiErrorResponse;
import kamienica.feature.division.DivisionService;
import kamienica.feature.invoice.InvoiceService;
import kamienica.model.Invoice;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceApi {

	private static final String WRONG_DIVISION = "Nieprawidłowy algorytm podziału";
	@Autowired
	private InvoiceService service;
	@Autowired
	private DivisionService divisionService;

	@RequestMapping(value = "/{media}", method = RequestMethod.GET)
	public ResponseEntity<?> getList(@PathVariable final Media media) {
		if (!divisionService.isDivisionCorrect()) {
			final String wrongDivision = "Algorytm podziału jest nieaktualny.";
			return new ResponseEntity<>(wrongDivision, HttpStatus.NO_CONTENT);
		}

		List<? extends Invoice> list = service.getList(media);
		if (list.isEmpty()) {
			return new ResponseEntity<List<? extends Invoice>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<? extends Invoice>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/ENERGY", method = RequestMethod.POST)
	public ResponseEntity<?> saveEnergy(@Valid @RequestBody final InvoiceEnergy invoice, final BindingResult result) {

		if (result.hasErrors()) {
			final ApiErrorResponse message = new ApiErrorResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			service.save(invoice, Media.ENERGY);
		} catch (InvalidDivisionException e) {
			return new ResponseEntity<>(WRONG_DIVISION, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(invoice, HttpStatus.OK);
	}

	@RequestMapping(value = "/GAS", method = RequestMethod.POST)
	public ResponseEntity<?> saveGas(@Valid @RequestBody final InvoiceGas invoice, final BindingResult result) {
		if (result.hasErrors()) {
			final ApiErrorResponse message = new ApiErrorResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			service.save(invoice, Media.GAS);
		} catch (InvalidDivisionException e) {
			return new ResponseEntity<>(WRONG_DIVISION, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(invoice, HttpStatus.OK);
	}

	@RequestMapping(value = "/WATER", method = RequestMethod.POST)
	public ResponseEntity<?> saveWater(@Valid @RequestBody final InvoiceWater invoice, final BindingResult result) {
		if (result.hasErrors()) {
			final ApiErrorResponse message = new ApiErrorResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			service.save(invoice, Media.WATER);
		} catch (InvalidDivisionException e) {
			return new ResponseEntity<>(WRONG_DIVISION, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(invoice, HttpStatus.OK);
	}

	@RequestMapping(value = "/{media}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable final Media media, final Long id) {
		service.delete(id, media);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
