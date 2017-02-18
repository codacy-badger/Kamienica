package kamienica.controller.api.v1;

import kamienica.model.enums.Media;
import kamienica.feature.reading.IReadingService;
import kamienica.feature.residence.ResidenceService;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/readings")
public class ReadingApi {


    private final IReadingService readingService;
    private final ResidenceService residenceService;

    @Autowired
    public ReadingApi(IReadingService readingService, ResidenceService residenceService) {
        this.readingService = readingService;
        this.residenceService = residenceService;
    }

    @RequestMapping(value = "/{media}", method = RequestMethod.GET)
    public ResponseEntity<?> getList(@PathVariable Media media, @RequestParam(value = "residence_id", required = false) Long id) {

        final Residence r = residenceService.getById(id);

        List<Reading> list = readingService.getList(r, media);
        if (list.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



    @RequestMapping(value = "/unresolved/{media}", method = RequestMethod.GET)
    public ResponseEntity<?> getListForInvoice(@PathVariable final Media media, @RequestParam("residence_id") final Long id) {
        final Residence r = residenceService.getById(id);
        List<Reading> list = readingService.getUnresolvedReadings(media, r);

        if (list.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
