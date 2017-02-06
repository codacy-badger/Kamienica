package kamienica.controller.api;

import kamienica.core.enums.Media;
import kamienica.feature.reading.ReadingService;
import kamienica.model.Reading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/readings")
public class ReadingApi {


    private final ReadingService service;

    @Autowired
    public ReadingApi(ReadingService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{media}", method = RequestMethod.GET)
    public ResponseEntity<?> getList(@PathVariable Media media, @RequestParam(value = "residence_id", required = false) Long id) {

        List<? extends Reading> list = service.getListForOwner(media);
        if (list.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/map/{media}", method = RequestMethod.GET)
    public ResponseEntity<?> getMappedList(@PathVariable Media media) {

        List<? extends Reading> list = service.getList(media);
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
