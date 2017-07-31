package kamienica.feature.division;

import kamienica.model.entity.Division;
import kamienica.model.entity.Invoice;

import java.util.List;

public interface IDivisionService {

    List<Division> createDivisionForResidence(Invoice invoice);
}
