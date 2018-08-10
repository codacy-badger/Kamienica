package kamienica.controller.api.v1;

import kamienica.feature.user.IUserService;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserApi {

  private final IUserService userService;

  @Autowired
  public UserApi(IUserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/readings", method = RequestMethod.GET)
  public ResponseEntity<?> userReadings() {
    final List<Reading> list = userService.getReadings();
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @RequestMapping(value = "/payments", method = RequestMethod.GET)
  public ResponseEntity<?> userPayment() {
    final List<Payment> list = userService.getPayments();
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public ResponseEntity<?> userDetails() {
    final Tenant t = userService.getTenantInfo();
    return new ResponseEntity<>(t, HttpStatus.OK);
  }

  @RequestMapping(value = "/readingDetails", method = RequestMethod.GET)
  public ResponseEntity<?> userReadingDetails() {
    Map<String, ReadingDetails> map = userService.getLatestReadingDates();
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
}
