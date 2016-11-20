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
import kamienica.feature.invoice.Invoice;
import kamienica.feature.invoice.InvoiceService;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceRestController {

	@Autowired
	InvoiceService service;

	@RequestMapping(value = "/{media}", method = RequestMethod.GET)
	public ResponseEntity<?> getList(@PathVariable Media media) {

		List<? extends Invoice> list = service.getList(media);
		if (list.isEmpty()) {
			return new ResponseEntity<List<? extends Invoice>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<? extends Invoice>>(list, HttpStatus.OK);
	}

}
