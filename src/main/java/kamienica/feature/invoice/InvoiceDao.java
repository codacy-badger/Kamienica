package kamienica.feature.invoice;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.BasicDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("invoiceDao")
public class InvoiceDao extends BasicDao<Invoice> implements IInvoiceDao {


    @Override
    public List<Invoice> getList(Residence r, Media m) {
        final Criterion forResidence = Restrictions.eq("residence", r);
        final Criterion forMedia = Restrictions.eq("media", m);
        return findByCriteria(forResidence, forMedia);
    }
}
