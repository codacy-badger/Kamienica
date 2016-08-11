package kamienica.feature.invoice;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.AbstractDao;
import kamienica.feature.payment.PaymentStatus;

@Repository("invoiceWater")
@Transactional
public class InvoiceWaterDAOImpl extends AbstractDao<Long, InvoiceWater> implements InvoiceDao<InvoiceWater> {

	@Override
	public InvoiceWater getLatest() {
		Query query = getSession().createSQLQuery(
				"select * from kamienica.invoicewater where date = (select MAX(date) from kamienica.invoicewater)");
		return (InvoiceWater) query.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceWater> getInvoicesForCalulation(Invoice invoice) {
		Query query = getSession()
				.createSQLQuery(
						"select * from kamienica.invoiceWater where status = :status and date <= :date and baseReading_id is not null order by date asc")
				.addEntity(InvoiceWater.class).setParameter("date", invoice.getDate())
				.setParameter("status", PaymentStatus.UNPAID.getPaymentStatus());
		return query.list();

	}

	@Override
	public List<InvoiceWater> getUnpaidInvoices() {
		Query query = getSession()
				.createSQLQuery(
						"select * from invoicewater where status =  :stat and baseReading_id is not null order by date asc")
				.addEntity(InvoiceWater.class).setParameter("stat", PaymentStatus.UNPAID.getPaymentStatus());
		@SuppressWarnings("unchecked")
		List<InvoiceWater> invoice = query.list();
		return invoice;
	}

	@Override
	public InvoiceWater getLastResolved() {
		Query query = getSession()
				.createSQLQuery("select * from invoiceWater where status =  :stat  order by date desc limit 1")
				.addEntity(InvoiceWater.class).setParameter("stat", PaymentStatus.PAID.getPaymentStatus());
		return (InvoiceWater) query.uniqueResult();
	}

	@Override
	public void resolveInvoice(InvoiceWater invoice) {
		Query query = getSession().createSQLQuery("update invoiceWater set status =  :stat  where id = :id")
				.addEntity(InvoiceWater.class).setParameter("stat", PaymentStatus.PAID.getPaymentStatus())
				.setParameter("id", invoice.getId());
		query.executeUpdate();
	}

	@Override
	public void unresolveInvoice(int id) {
		Query query = getSession().createSQLQuery("update invoicewater set status = :stat where id = :id")
				.addEntity(InvoiceWater.class).setParameter("stat", PaymentStatus.UNPAID.getPaymentStatus())
				.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public int getDaysOfLastInvoice() {
		try {
			Query query = getSession().createSQLQuery(
					"SELECT DATEDIFF(CURDATE()  ,invoicewater.date) FROM invoicewater order by date desc limit 1");
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			return 0;
		}
	}
}
