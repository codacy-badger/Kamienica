package kamienica.feature.payment;

import kamienica.core.dao.AbstractDao;
import kamienica.feature.reading.Reading;
import kamienica.model.Invoice;
import kamienica.model.Tenant;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class PaymentAbstractDaoImpl<P extends Payment> extends AbstractDao<P> {

	@SuppressWarnings("unchecked")
	public List<P> getByInvoice(Invoice invoice) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("invoice", invoice.getId()));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<P> getByReading(Reading reading) {
		// QUERY QUERY = GETSESSION().CREATESQLQUERY("SELECT * FROM
		// PAYMENTENERGY WHERE READINGDATE =:DATE")
		// .ADDENTITY(persistentClass).SETPARAMETER("DATE",
		// READING.GETREADINGDATE());
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("readingDate", reading.getReadingDate()));
		return crit.list();
	}

	@Override
	public List<P> getList() {
		@SuppressWarnings("unchecked")
		List<P> list = getSession().createCriteria(persistentClass).addOrder(Order.asc("paymentDate"))
				.addOrder(Order.asc("tenant")).list();
		return list;
	}

	public List<P> getPaymentForTenant(Tenant tenant) {
		@SuppressWarnings("unchecked")
		List<P> list = getSession().createCriteria(persistentClass).add(Restrictions.eq("tenant", tenant))
				.addOrder(Order.asc("paymentDate")).addOrder(Order.asc("tenant")).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public P getLatestPayment() {
		List<P> list = getSession().createCriteria(persistentClass).addOrder(Order.desc("readingDate"))
				.list();
		if (list.isEmpty()) {
			return (P) new PaymentEnergy();
		}
		return list.get(0);

	}

	public void deleteForInvoice(Invoice invoice) {
		String sql = String.format("delete from %s where invoice_id = :inv", getTabName());
		Query query = getSession().createSQLQuery(sql).addEntity(persistentClass).setLong("inv", invoice.getId());
		query.executeUpdate();
	}

	public void deleteByDate(String date) {
		String sql = String.format("delete from %s where paymentdate >= :date", getTabName());
		Query query = getSession().createSQLQuery(sql).addEntity(persistentClass).setString("date", date);
		query.executeUpdate();
	}
}