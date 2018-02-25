package kamienica.feature.invoice;

import kamienica.model.entity.Invoice;
import kamienica.model.enums.Media;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;

import java.util.List;

public interface IInvoiceService {

    void save(Invoice invoice) throws UsageCalculationException, NegativeConsumptionValue;

    List<Invoice> list( Media media);

    void delete(Long id);

    void delete(Invoice invoice);

    Invoice getByID(Long id);

}
