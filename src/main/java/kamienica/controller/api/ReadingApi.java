package kamienica.controller.api;

import kamienica.core.enums.Media;
import kamienica.feature.reading.ReadingService;
import kamienica.feature.residence.ResidenceService;
import kamienica.model.Reading;
import kamienica.model.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/readings")
public class ReadingApi {


    private final ReadingService service;
    private final ResidenceService residenceService;

    @Autowired
    public ReadingApi(ReadingService service, ResidenceService residenceService) {
        this.service = service;
        this.residenceService = residenceService;
    }

    @RequestMapping(value = "/{media}", method = RequestMethod.GET)
    public ResponseEntity<?> getList(@PathVariable Media media, @RequestParam(value = "residence_id", required = false) Long id) {

        final Residence r = residenceService.getById(id);

        List<? extends Reading> list = service.getList(r, media);
        if (list.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



    @RequestMapping(value = "/unresolved/{media}", method = RequestMethod.GET)
    public ResponseEntity<?> getListForInvoice(@PathVariable Media media) {

        List<?> list = service.getUnresolvedReadings(media);

        service.getList(media);
        if (list.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
