package kamienica.feature.invoice;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.PaymentStatus;
import kamienica.model.jpa.dao.BasicDao;

import java.util.List;

public interface IInvoiceDao extends BasicDao<Invoice> {


    List<Invoice> getList(Residence r, Media m);
}
