package kamienica.controller.api.v1;

import kamienica.feature.reading.IReadingService;
import kamienica.feature.readingdetails.IReadingDetailsService;
import kamienica.feature.residence.IResidenceService;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/readings")
public class ReadingApi {


    private final IReadingService readingService;
    private final IResidenceService residenceService;
    private final IReadingDetailsService readingDetailsService;

    //TODO validate so that only readings belonging to an owner can be saved deleted etc
    @Autowired
    public ReadingApi(IReadingService readingService, IResidenceService residenceService, IReadingDetailsService detailsService) {
        this.readingService = readingService;
        this.residenceService = residenceService;
        this.readingDetailsService = detailsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getList(@RequestParam String media, @RequestParam Long residence_id) {

        final Residence residence = residenceService.getById(residence_id);
        final List<Reading> list = readingService.getList(residence, Media.valueOf(media));

        if (list.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@Valid @RequestBody final ReadingForm readingForm) {
//        validate()
        System.out.println("-------------------SAVING-----------------------------------");
        readingService.save(readingForm);
        return new ResponseEntity<>(readingForm, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> delete(@Valid @RequestBody final ReadingForm readingForm) {
//        validate()
        System.out.println("-------------------SAVING-----------------------------------");
        readingService.update(readingForm);
        return new ResponseEntity<>(readingForm, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam String media, @RequestParam Long residence_id) {
//        validate()
        final Residence res = residenceService.getById(residence_id);
        readingService.deleteLatestReadings(res, Media.valueOf(media));
//        readingService.delete(readingForm);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @RequestMapping(value = "/unresolved/{media}", method = RequestMethod.GET)
    public ResponseEntity<?> getListForInvoice(@PathVariable final Media media, @RequestParam(value = "residence_id", required = false) final Long id) {

        final List<ReadingDetails> list;
        if (id != null) {
            final Residence r = residenceService.getById(id);
            list = readingDetailsService.getUnresolved(r, media);
        } else {
            list = readingDetailsService.getUnresolved(media);
        }

        if (list.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
