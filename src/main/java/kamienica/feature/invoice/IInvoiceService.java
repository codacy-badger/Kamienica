package kamienica.feature.invoice;

import kamienica.model.enums.Media;
import kamienica.model.exception.InvalidDivisionException;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;

import java.util.List;
import java.util.Map;

public interface IInvoiceService {

    List<Reading> getUnpaidReadingForNewIncvoice(Residence r, Media media) throws InvalidDivisionException;

    void save(Invoice invoice, Media media, Tenant tenant, Residence r);

    void list(Residence r, Map<String, Object> model, Media media);

    List<Invoice> list( Media media);

    void delete(Long id);

    Invoice getByID(Long id);

}
