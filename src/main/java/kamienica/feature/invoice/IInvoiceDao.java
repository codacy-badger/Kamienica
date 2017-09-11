package kamienica.feature.invoice;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.IBasicDao;

import java.util.List;

public interface IInvoiceDao extends IBasicDao<Invoice> {

    List<Invoice> getList(Residence r, Media m);
}
