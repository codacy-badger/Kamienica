package kamienica.feature.invoice;

import kamienica.model.InvoiceEnergy;
import org.springframework.stereotype.Repository;

@Repository("invoiceEnergy")
public class InvoiceEnergyDAOImpl extends InvoiceAbstractDaoImpl<InvoiceEnergy> implements InvoiceAbstractDao<InvoiceEnergy> {

}
