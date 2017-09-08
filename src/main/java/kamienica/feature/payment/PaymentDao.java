package kamienica.feature.payment;

import kamienica.model.entity.Invoice;
import kamienica.model.entity.Payment;
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
    public void deleteForInvoice(Invoice invoice) {
        String sql = String.format("delete from %s where invoice_id = :inv", getTabName());
        Query query = getSession().createSQLQuery(sql).addEntity(persistentClass).setLong("inv", invoice.getId());
        query.executeUpdate();
    }
}
