package kamienica.feature.invoice;

import kamienica.model.entity.Invoice;
import kamienica.model.enums.Media;

import java.util.List;

public interface IInvoiceService {

    void save(Invoice invoice);

    List<Invoice> list( Media media, Long residenceID);

    void delete(Invoice invoice);

    Invoice getByID(Long id);

}
