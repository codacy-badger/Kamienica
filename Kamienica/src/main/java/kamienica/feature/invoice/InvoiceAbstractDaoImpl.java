package kamienica.feature.invoice;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import kamienica.dao.AbstractDao;
import kamienica.feature.payment.PaymentStatus;

public class InvoiceAbstractDaoImpl<I extends Invoice> extends AbstractDao<I> {

	@SuppressWarnings("unchecked")
	public List<I> getUnpaidInvoices() {
		Criteria crit = createEntityCriteria()
				.add(Restrictions.eqProperty("status", PaymentStatus.UNPAID.getPaymentStatus()))
				.add(Restrictions.isNotNull("baseReading"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public I getLastResolved() {
		Criteria crit = createEntityCriteria()
				.add(Restrictions.eqProperty("status", PaymentStatus.PAID.getPaymentStatus()))
				.setProjection(Projections.max("readingDate"));
		return (I) crit.uniqueResult();

	}

	public void resolveInvoice(I invoice) {
		String sql = String.format("update %s set status =  :stat  where id = :id", getTabName());
		Query query = getSession().createSQLQuery(sql).addEntity(persistentClass)
				.setParameter("stat", PaymentStatus.PAID.getPaymentStatus()).setParameter("id", invoice.getId());
		query.executeUpdate();

	}

	public void unresolveInvoice(int id) {
		String sql = String.format("update %s set status =  :stat  where id = :id", getTabName());
		Query query = getSession().createSQLQuery(sql).addEntity(persistentClass)
				.setParameter("stat", PaymentStatus.UNPAID.getPaymentStatus()).setParameter("id", id);
		query.executeUpdate();

	}

	public void setResolvement(I invoice, PaymentStatus status) {
		String sql = String.format("update %s set status =  :stat  where id = :id", getTabName());
		Query query = getSession().createSQLQuery(sql).addEntity(persistentClass)
				.setParameter("stat", status.getPaymentStatus()).setParameter("id", invoice.getId());
		query.executeUpdate();

	}

	public int getDaysOfLastInvoice() {
		String sql = String.format("SELECT DATEDIFF(CURDATE()  ,I.date) FROM %s I order by date desc limit 1",
				getTabName());
		try {
			Query query = getSession().createSQLQuery(sql);
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			return 9999;
		}
	}
}
