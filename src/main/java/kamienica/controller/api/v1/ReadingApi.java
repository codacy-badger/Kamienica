package kamienica.controller.api.v1;

import kamienica.feature.reading.IReadingService;
import kamienica.feature.readingdetails.IReadingDetailsService;
import kamienica.feature.residence.IResidenceService;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/readings")
public class ReadingApi {


    private final IReadingService readingService;
    private final IResidenceService residenceService;
    private final IReadingDetailsService detailsService;

    @Autowired
    public ReadingApi(IReadingService readingService, IResidenceService residenceService, IReadingDetailsService detailsService) {
        this.readingService = readingService;
        this.residenceService = residenceService;
        this.detailsService = detailsService;
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
        List<ReadingDetails> list = detailsService.getUnresolved(r, media);

        if (list.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
