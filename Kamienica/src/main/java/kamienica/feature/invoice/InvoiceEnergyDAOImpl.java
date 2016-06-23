package kamienica.feature.invoice;

import java.util.List;



import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.AbstractDao;
import kamienica.feature.payment.PaymentStatus;

@Repository("invoiceEnergy")
@Transactional
public class InvoiceEnergyDAOImpl extends AbstractDao<Long, InvoiceEnergy> implements InvoiceDao<InvoiceEnergy> {

	@Override
	public InvoiceEnergy getLatest() {
//		List<InvoiceEnergy> list = getSession().createQuery(
//				"select * from kamienica.invoiceenergy where date = (select MAX(date) from kamienica.invoiceenergy)").list();
		Query query = getSession().createSQLQuery(
				"select * from kamienica.invoiceenergy where date = (select MAX(date) from kamienica.invoiceenergy)");
		return (InvoiceEnergy) query.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceEnergy> getInvoicesForCalulation(Invoice invoice) {
		Query query = getSession()
				.createSQLQuery(
						"select * from kamienica.invoiceenergy where status = :status and date <= :date and baseReading_id is not null order by date asc")
				.addEntity(InvoiceEnergy.class).setParameter("date", invoice.getDate())
				.setParameter("status", PaymentStatus.UNPAID.getPaymentStatus());
		return query.list();

	}

	@Override
	public List<InvoiceEnergy> getUnpaidInvoices() {
		Query query = getSession()
				.createSQLQuery(
						"select * from invoiceenergy where status =  :stat and baseReading_id is not null order by date asc")
				.addEntity(InvoiceEnergy.class).setParameter("stat", PaymentStatus.UNPAID.getPaymentStatus());
		@SuppressWarnings("unchecked")
		List<InvoiceEnergy> invoice = query.list();
		return invoice;
	}

	@Override
	public InvoiceEnergy getLastResolved() {
		Query query = getSession()
				.createSQLQuery("select * from invoiceenergy where status =  :stat  order by date desc limit 1")
				.addEntity(InvoiceEnergy.class).setParameter("stat", PaymentStatus.PAID.getPaymentStatus());
		return (InvoiceEnergy) query.uniqueResult();
	}

	@Override
	public void resolveInvoice(InvoiceEnergy invoice) {
		Query query = getSession().createSQLQuery("update invoiceenergy set status =  :stat  where id = :id")
				.addEntity(InvoiceEnergy.class).setParameter("stat", PaymentStatus.PAID.getPaymentStatus())
				.setParameter("id", invoice.getId());
		query.executeUpdate();

	}

	@Override
	public void unresolveInvoice(int id) {
		Query query = getSession().createSQLQuery("update invoiceenergy set status = :stat where id = :id")
				.addEntity(InvoiceEnergy.class).setParameter("stat", PaymentStatus.UNPAID.getPaymentStatus())
				.setParameter("id", id);
		query.executeUpdate();

	}

	@Override
	public int getDaysOfLastInvoice() {
		try {
			Query query = getSession().createSQLQuery(
					"SELECT DATEDIFF(CURDATE()  ,invoiceenergy.date) FROM invoiceenergy order by date desc limit 1");
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			return 0;
		}
	}

}
