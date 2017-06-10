package kamienica.controller.api.v1;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.payment.IPaymentService;
import kamienica.feature.owner.IOwnerUserDataService;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class AdminUserRestController {

    private final IPaymentService paymentService;
    private final  IOwnerUserDataService ownerUserDataService;

    @Autowired
    public AdminUserRestController(IPaymentService paymentService, IOwnerUserDataService ownerUserDataService) {
        this.paymentService = paymentService;
        this.ownerUserDataService = ownerUserDataService;
    }


    @RequestMapping(value = "/user/{media}/readings", method = RequestMethod.GET)
    public ResponseEntity<?> getReadings(@PathVariable(value = "media") Media media) {
        final Apartment ap = SecurityDetails.getLoggedTenant().fetchApartment();
        final List<Reading> readings = ownerUserDataService.getReadingsForTenant(ap, media);

        if (readings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(readings, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{media}/payments", method = RequestMethod.GET)
    public ResponseEntity<?> userPayment(@PathVariable(value = "media") Media media) {
        final Tenant tenant = SecurityDetails.getLoggedTenant();
        final List<Payment> list = paymentService.getPaymentForTenant(tenant, media);

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
