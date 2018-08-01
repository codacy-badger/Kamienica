package kamienica.controller.api.v1;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.invoice.IInvoiceService;
import kamienica.model.entity.Invoice;
import kamienica.model.enums.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceApi {

    private final IInvoiceService invoiceService;

    @Autowired
    public InvoiceApi(final IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<?> getList(@RequestParam("media") final Media media, @RequestParam("residence") final Long residenceId) {
        final List<Invoice> list = invoiceService.list(media, residenceId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody final Invoice invoice) {
        invoiceService.save(invoice);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")  final Long id) {
        final Invoice invoice = invoiceService.getByID(id);
        SecurityDetails.checkIfOwnsResidence(invoice.getResidence());
        invoiceService.delete(invoice);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
