package kamienica.feature.invoice;

import java.util.List;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.BasicDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("invoiceDao")
public class InvoiceDao extends BasicDao<Invoice> implements IInvoiceDao {


    @Override
    public List<Invoice> getList(Residence r, Media m) {
        final Criterion forResidence = Restrictions.eq("residence", r);
        final Criterion forMedia = Restrictions.eq("media", m);
        return findByCriteria(forResidence, forMedia);
    }

    @Override
    public Invoice getLatest(Residence r, Media m) {
        final Criterion forResidence = Restrictions.eq("residence", r);
        final Criterion forMedia = Restrictions.eq("media", Media.WATER);
        final DetachedCriteria latestForSpecificMediaAndResidence = DetachedCriteria.forClass(Invoice.class)
            .add(forMedia)
            .add(forResidence)
            .setProjection(Projections.max("invoiceDate") );
        final Criterion max = Property.forName("invoiceDate").eq(latestForSpecificMediaAndResidence);

        return findOneByCriteria(max, forMedia);
    }
}
