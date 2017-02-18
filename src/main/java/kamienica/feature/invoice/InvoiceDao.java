package kamienica.feature.invoice;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.enums.PaymentStatus;
import kamienica.model.jpa.dao.BasicDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("invoiceDao")
public class InvoiceDao extends BasicDaoImpl<Invoice> implements IInvoiceDao {

    private static final String INVOICE_DAYS = "SELECT DATEDIFF(CURDATE()  ,I.date) FROM %s I order by date desc limit 1";
    private static final String SET_RESOLVMENT = "update %s set status =  :stat  where id = :id";

    @Override
    @SuppressWarnings("unchecked")
    public List<Invoice> getUnpaidInvoices() {
        Criteria crit = createEntityCriteria()
                .add(Restrictions.eqProperty("status", PaymentStatus.UNPAID.getPaymentStatus()))
                .add(Restrictions.isNotNull("baseReading"));
        return crit.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Invoice getLastResolved() {
        Criteria crit = createEntityCriteria()
                .add(Restrictions.eqProperty("status", PaymentStatus.PAID.getPaymentStatus()))
                .setProjection(Projections.max("readingDate"));
        return (Invoice) crit.uniqueResult();

    }

    @Override
    public void setResolvement(Invoice invoice, PaymentStatus status) {
        String sql = String.format(SET_RESOLVMENT, getTabName());
        Query query = getSession().createSQLQuery(sql).addEntity(persistentClass)
                .setParameter("stat", status.getPaymentStatus()).setParameter("id", invoice.getId());
        query.executeUpdate();

    }

    @Override
    public int getDaysOfLastInvoice() {
        String sql = String.format(INVOICE_DAYS,
                getTabName());
        try {
            Query query = getSession().createSQLQuery(sql);
            return ((Number) query.uniqueResult()).intValue();
        } catch (Exception e) {
            return 9999;
        }
    }

    @Override
    public List<Invoice> getList(Residence r, Media m) {
        final Criterion forResidence = Restrictions.eq("residence", r);
        final Criterion forMedia = Restrictions.eq("media", m);

        return findByCriteria(forResidence, forMedia);
    }
}
