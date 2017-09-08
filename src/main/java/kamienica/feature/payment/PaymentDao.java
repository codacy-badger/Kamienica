package kamienica.feature.payment;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.BasicDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("paymentDao")
public class PaymentDao extends BasicDao<Payment> implements IPaymentDao {

    @Override
    public List<Payment> getByInvoice(Invoice invoice) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("invoice", invoice.getId()));
        //noinspection unchecked
        return crit.list();
    }

    @Override
    public List<Payment> getByReading(Reading reading) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("readingDate", reading.getReadingDetails().getReadingDate()));
        //noinspection unchecked
        return crit.list();
    }

    @Override
    public List<Payment> getList(final Media media) {
        Criteria c = createEntityCriteria();
        c.addOrder(Order.asc("paymentDate"));
        c.addOrder(Order.asc("tenant"));
        c.createCriteria("invoice").add(Restrictions.eq("media", media));
        //noinspection unchecked
        return c.list();
    }

    @Override
    public List<Payment> getPaymentForTenant(final Tenant tenant, final Media media) {
        Criteria c = createEntityCriteria();
        c.add(Restrictions.eq("tenant", tenant));
        c.addOrder(Order.asc("paymentDate")).addOrder(Order.asc("tenant"));
        c.createCriteria("invoice").add(Restrictions.eq("media", media));
        //noinspection unchecked
        return c.list();
    }

    @Override
    public Payment getLatestPayment() {
        //TODO change the logic here and add test!!!
        @SuppressWarnings("unchecked") List<Payment> list = getSession().createCriteria(persistentClass).addOrder(Order.desc("readingDate"))
                .list();
        if (list.isEmpty()) {
            return new Payment();
        }
        return list.get(0);

    }

    @Override
    public void deleteForInvoice(Invoice invoice) {
        String sql = String.format("delete from %s where invoice_id = :inv", getTabName());
        Query query = getSession().createSQLQuery(sql).addEntity(persistentClass).setLong("inv", invoice.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteByDate(String date) {
        String sql = String.format("delete from %s where paymentdate >= :date", getTabName());
        Query query = getSession().createSQLQuery(sql).addEntity(persistentClass).setString("date", date);
        query.executeUpdate();
    }
}
