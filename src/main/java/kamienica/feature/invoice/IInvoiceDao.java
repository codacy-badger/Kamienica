package kamienica.feature.invoice;

import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.BasicDao;
import kamienica.model.enums.PaymentStatus;
import kamienica.model.entity.Invoice;

import java.util.List;

public interface IInvoiceDao extends BasicDao<Invoice> {

    List<Invoice> getUnpaidInvoices();

    Invoice getLastResolved();

    void setResolvement(Invoice invoice, PaymentStatus status);

    int getDaysOfLastInvoice();

    List<Invoice> getList(Residence r, Media m);
}
