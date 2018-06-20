package kamienica.feature.invoice;

import java.util.List;
import kamienica.model.entity.Invoice;
import kamienica.model.enums.Media;

public interface IInvoiceService {

    void save(Invoice invoice);

    List<Invoice> list( Media media, Long residenceID);

    void delete(Long id);

    void delete(Invoice invoice);

    Invoice getByID(Long id);

}
