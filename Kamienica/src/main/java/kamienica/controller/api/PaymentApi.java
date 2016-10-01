package kamienica.controller.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kamienica.core.util.Media;
import kamienica.feature.payment.Payment;
import kamienica.feature.payment.PaymentService;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentApi {

	@Autowired
	PaymentService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList() {

		List<? extends Payment> energy = service.getPaymentList(Media.ENERGY);
		List<? extends Payment> gas = service.getPaymentList(Media.GAS);
		List<? extends Payment> water = service.getPaymentList(Media.WATER);
//		Map<String, List<? extends Payment>> map = new HashMap<>();
//		map.put("energy", energy);
//		map.put("water", water);
//		map.put("gas", gas);
		List<List<? extends Payment>> list = Arrays.asList(energy, gas, water);

		if (list.isEmpty()) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<List<? extends Payment>>>(list, HttpStatus.OK);

	}

}
