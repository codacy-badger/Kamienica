package kamienica.controller.api.v1;

import kamienica.feature.payment.IPaymentService;
import kamienica.model.entity.Payment;
import kamienica.model.enums.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentApi {

    private final IPaymentService service;

    @Autowired
    public PaymentApi(IPaymentService service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getList(@RequestParam("media") final Media media, @RequestParam("residence") final Long residenceId) {
        final List<Payment> list = service.getPaymentList(media, residenceId);
        return new ResponseEntity<List<? extends Payment>>(list, HttpStatus.OK);

    }
}
