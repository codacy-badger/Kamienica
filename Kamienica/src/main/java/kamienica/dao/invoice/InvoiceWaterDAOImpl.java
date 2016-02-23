package kamienica.dao.invoice;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.model.Invoice;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentStatus;

@Repository("invoiceWater")
@Transactional
public class InvoiceWaterDAOImpl extends AbstractDao<Integer, InvoiceWater> implements InvoiceWaterDAO {

	public void deleteByID(int id) {
		Query query = getSession().createSQLQuery("delete from invoicewater where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();

	}

	//
	//
	//
	// public Invoice getWaterByID(int id) {
	// Session session = this.sessionfactory.getCurrentSession();
	// session.beginTransaction();
	// Invoice out = (InvoiceWater) session.get(InvoiceWater.class, new
	// Integer(id));
	// session.close();
	// return out;
	// }

	public InvoiceWater getLatest() {
		Query query = getSession().createSQLQuery(
				"select * from kamienica.invoicewater where date = (select MAX(date) from kamienica.invoicewater)");
		return (InvoiceWater) query.uniqueResult();

	}

	// @SuppressWarnings("unchecked")
	// public List<InvoiceWater> getInvoicesForPayment(PaymentWater payment) {
	//
	// String sql = "";
	// if (payment.getId() < 1) {
	// sql = "select * from kamienica.invoicewater order by date asc";
	// } else {
	// sql = "select * from kamienica.invoicewater where date >= (select date
	// from kamienica.invoicewater where id = "
	// + payment.getInvoice().getId() + ") order by date asc ";
	// }
	// Query query =
	// getSession().createSQLQuery(sql).addEntity(InvoiceWater.class);
	// return query.list();
	// }

	@SuppressWarnings("unchecked")
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
		Query query = getSession().createSQLQuery("select * from invoicewater where status =  :stat and baseReading_id is not null order by date asc")
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
		Query query = getSession()
				.createSQLQuery(
						"update invoicewater set status = :stat where id = :id")
				.addEntity(InvoiceWater.class).setParameter("stat", PaymentStatus.UNPAID.getPaymentStatus())
				.setParameter("id", id);
		query.executeUpdate();
	}
}
