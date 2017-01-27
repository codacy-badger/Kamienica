package kamienica.controller.api;

import kamienica.core.enums.Media;
import kamienica.feature.reading.ReadingService;
import kamienica.model.Reading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/readings")
public class ReadingApi {

    @Autowired
    private ReadingService service;

    @RequestMapping(value = "/{media}", method = RequestMethod.GET)
    public ResponseEntity<?> getList(@PathVariable Media media) {

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
