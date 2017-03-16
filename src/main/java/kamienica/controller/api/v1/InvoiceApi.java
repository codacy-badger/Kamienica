package kamienica.controller.api.v1;

import kamienica.core.message.ApiErrorResponse;
import kamienica.feature.invoice.IInvoiceService;
import kamienica.feature.residence.IResidenceService;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceApi extends AbstractApi {

    private final IInvoiceService invoiceService;
    private final IResidenceService residenceService;

    @Autowired
    public InvoiceApi(IInvoiceService invoiceService, IResidenceService residenceService) {
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

        final Residence r = residenceService.getById(residenceId);
        invoice.setResidence(r);
        invoice.setMedia(Media.ENERGY);
        invoiceService.save(invoice);

        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @RequestMapping(value = "/GAS", method = RequestMethod.POST)
    public ResponseEntity<?> saveGas(@Valid @RequestBody final Invoice invoice, final BindingResult result, @RequestParam("residence") final Long residenceId) {
        if (result.hasErrors()) {
            final ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        final Residence r = residenceService.getById(residenceId);
        invoice.setResidence(r);
        invoice.setMedia(Media.GAS);
        invoiceService.save(invoice);

        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @RequestMapping(value = "/WATER", method = RequestMethod.POST)
    public ResponseEntity<?> saveWater(@Valid @RequestBody final Invoice invoice, final BindingResult result, @RequestParam("residence") final Long residenceId) {
        if (result.hasErrors()) {
            final ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        final Residence r = residenceService.getById(residenceId);
        invoice.setResidence(r);
        invoice.setMedia(Media.ENERGY);
        invoiceService.save(invoice);

        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @RequestMapping(value = "/{media}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable final Media media, final Long id) {
        invoiceService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
