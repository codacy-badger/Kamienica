package kamienica.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kamienica.core.Media;
import kamienica.feature.payment.PaymentAbstract;
import kamienica.feature.payment.PaymentService;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentRestController {

	@Autowired
	PaymentService service;

	@RequestMapping(value = "/{media}", method = RequestMethod.GET)
	public ResponseEntity<List<? extends PaymentAbstract>> getList(@PathVariable Media media) {

		List<? extends PaymentAbstract> list;
		switch (media) {
		case ENERGY:

			list = service.getPaymentEnergyList();

			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends PaymentAbstract>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<? extends PaymentAbstract>>(list, HttpStatus.OK);
		case GAS:

			list = service.getPaymentGasList();

			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends PaymentAbstract>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<? extends PaymentAbstract>>(list, HttpStatus.OK);
		case WATER:

			list = service.getPaymentWaterList();

			if (list.isEmpty()) {
				return new ResponseEntity<List<? extends PaymentAbstract>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<? extends PaymentAbstract>>(list, HttpStatus.OK);

		default:
			return new ResponseEntity<List<? extends PaymentAbstract>>(HttpStatus.NO_CONTENT);
		}

	}

}
