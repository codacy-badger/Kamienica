package kamienica.feature.invoice;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;

import java.util.List;
import java.util.Map;

public interface IInvoiceService {

    void save(Invoice invoice);

    List<Invoice> list( Media media);

    void delete(Long id);

    Invoice getByID(Long id);

}
