package kamienica.controller.api.v1;

import kamienica.controller.ControllerMessages;
import kamienica.core.message.ApiErrorResponse;
import kamienica.feature.invoice.IInvoiceService;
import kamienica.feature.residence.ResidenceService;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import kamienica.model.exception.InvalidDivisionException;
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

    private final IInvoiceService invoiceService;
    private final ResidenceService residenceService;

    @Autowired
    public InvoiceApi(IInvoiceService invoiceService, ResidenceService residenceService) {
        this.invoiceService = invoiceService;
        this.residenceService = residenceService;
    }

    @RequestMapping(value = "/{media}", method = RequestMethod.GET)
    public ResponseEntity<?> getList(@PathVariable final Media media) {

        List<? extends Invoice> list = invoiceService.list(media);
        if (list.isEmpty()) {
            return new ResponseEntity<List<? extends Invoice>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<? extends Invoice>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/ENERGY", method = RequestMethod.POST)
    public ResponseEntity<?> saveEnergy(@Valid @RequestBody final Invoice invoice, final BindingResult result, @RequestParam("residence") final Long residenceId) {

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
    public ResponseEntity<?> saveGas(@Valid @RequestBody final Invoice invoice, final BindingResult result, @RequestParam("residence") final Long residenceId) {
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
    public ResponseEntity<?> saveWater(@Valid @RequestBody final Invoice invoice, final BindingResult result, @RequestParam("residence") final Long residenceId) {
        if (result.hasErrors()) {
            final ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            final Residence r = residenceService.getById(residenceId);
            final Tenant t = ownerUserDataService.getLoggedTenant();
            invoiceService.save(invoice, Media.WATER, t, r);
        } catch (InvalidDivisionException e) {
            return new ResponseEntity<>(ControllerMessages.WRONG_DIVISION, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @RequestMapping(value = "/{media}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable final Media media, final Long id) {
        invoiceService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
