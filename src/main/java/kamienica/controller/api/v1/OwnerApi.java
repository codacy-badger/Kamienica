package kamienica.controller.api.v1;


import kamienica.feature.ownerdata.IOwnerDataService;
import kamienica.model.entity.OwnerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owner")
public class OwnerApi {

    private final IOwnerDataService ownerDataService;

    @Autowired
    public OwnerApi(final IOwnerDataService ownerDataService) {
        this.ownerDataService = ownerDataService;
    }

    @GetMapping
    public ResponseEntity<OwnerData> userPayment() {
        final OwnerData data = ownerDataService.getMainData();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
