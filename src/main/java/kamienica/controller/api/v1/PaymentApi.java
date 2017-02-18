package kamienica.controller.api.v1;

import kamienica.model.enums.Media;
import kamienica.feature.payment.IPaymentService;
import kamienica.model.entity.Payment;
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

    @Autowired
    private IPaymentService service;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getList2(@RequestParam("media") Media media) {

        List<? extends Payment> list = service.getPaymentList(media);

        if (list.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<? extends Payment>>(list, HttpStatus.OK);

    }
}
