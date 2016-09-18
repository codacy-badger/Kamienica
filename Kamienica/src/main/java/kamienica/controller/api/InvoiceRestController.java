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
import kamienica.feature.invoice.Invoice;
import kamienica.feature.invoice.InvoiceEnergy;
import kamienica.feature.invoice.InvoiceService;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceRestController {

	@Autowired
	InvoiceService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<? extends Invoice>> getList() {

		List<InvoiceEnergy> list = service.getEnergyInvoiceList();
		if (list == null) {
			return new ResponseEntity<List<? extends Invoice>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<? extends Invoice>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{media}", method = RequestMethod.GET)
	public ResponseEntity<List<? extends Invoice>> getList(@PathVariable Media media,
			@RequestParam(required = false) LocalDate date) {

		List<? extends Invoice> list;
		switch (media) {
		case ENERGY:
			list = service.getEnergyInvoiceList();
			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends Invoice>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<? extends Invoice>>(list, HttpStatus.OK);

		case GAS:
			list = service.getGasInvoiceList();
			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends Invoice>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<? extends Invoice>>(list, HttpStatus.OK);

		case WATER:
			list = service.getWaterInvoiceList();
			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends Invoice>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<? extends Invoice>>(list, HttpStatus.OK);

		default:
			return new ResponseEntity<List<? extends Invoice>>(HttpStatus.NO_CONTENT);
		}

	}

}
