package kamienica.controller.api;

import kamienica.controller.ControllerMessages;
import kamienica.core.enums.Media;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.message.ApiErrorResponse;
import kamienica.feature.invoice.InvoiceService;
import kamienica.feature.residence.ResidenceService;
import kamienica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceApi extends AbstractApi {


    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ResidenceService residenceService;

    @RequestMapping(value = "/{media}", method = RequestMethod.GET)
    public ResponseEntity<?> getList(@PathVariable final Media media) {

        List<? extends Invoice> list = invoiceService.getList(media);
        if (list.isEmpty()) {
            return new ResponseEntity<List<? extends Invoice>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<? extends Invoice>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/ENERGY", method = RequestMethod.POST)
    public ResponseEntity<?> saveEnergy(@Valid @RequestBody final InvoiceEnergy invoice, final BindingResult result, @RequestParam("residence") final Long residenceId) {

        if (result.hasErrors()) {
            final ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            final Tenant t = ownerUserDataService.getLoggedTenant();
            final Residence r = residenceService.getById(residenceId);
            invoiceService.save(invoice, Media.ENERGY, t, r);
        } catch (InvalidDivisionException e) {
            return new ResponseEntity<>(ControllerMessages.WRONG_DIVISION, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @RequestMapping(value = "/GAS", method = RequestMethod.POST)
    public ResponseEntity<?> saveGas(@Valid @RequestBody final InvoiceGas invoice, final BindingResult result, @RequestParam("residence") final Long residenceId) {
        if (result.hasErrors()) {
            final ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            final Tenant t = ownerUserDataService.getLoggedTenant();
            final Residence r = residenceService.getById(residenceId);
            invoiceService.save(invoice, Media.GAS, t, r);
        } catch (InvalidDivisionException e) {
            return new ResponseEntity<>(ControllerMessages.WRONG_DIVISION, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @RequestMapping(value = "/WATER", method = RequestMethod.POST)
    public ResponseEntity<?> saveWater(@Valid @RequestBody final InvoiceWater invoice, final BindingResult result, @RequestParam("residence") final Long residenceId) {
        if (result.hasErrors()) {
            final ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            final Residence r = residenceService.getById(residenceId);
            final Tenant t = ownerUserDataService.getLoggedTenant();
            invoiceService.save(invoice, Media.WATER, t,r);
        } catch (InvalidDivisionException e) {
            return new ResponseEntity<>(ControllerMessages.WRONG_DIVISION, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @RequestMapping(value = "/{media}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable final Media media, final Long id) {
        invoiceService.delete(id, media);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
