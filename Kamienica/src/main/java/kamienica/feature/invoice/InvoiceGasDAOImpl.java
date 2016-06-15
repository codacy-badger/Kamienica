package kamienica.feature.invoice;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.feature.payment.PaymentStatus;

@Repository("invoiceGas")
@Transactional
public class InvoiceGasDAOImpl extends AbstractDao<Integer, InvoiceGas> implements InvoiceDao<InvoiceGas> {

	@Override
	public InvoiceGas getLatest() {
		Query query = getSession().createSQLQuery(
				"select * from kamienica.invoicegas where date = (select MAX(date) from kamienica.invoicegas)");
		return (InvoiceGas) query.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceGas> getInvoicesForCalulation(Invoice invoice) {
		Query query = getSession()
				.createSQLQuery(
						"select * from kamienica.invoicegas where status = :status and date <= :date and baseReading_id is not null order by date asc")
				.addEntity(InvoiceGas.class).setParameter("date", invoice.getDate())
				.setParameter("status", PaymentStatus.UNPAID.getPaymentStatus());
		return query.list();
	}

	@Override
	public List<InvoiceGas> getUnpaidInvoices() {
		Query query = getSession()
				.createSQLQuery(
						"select * from invoicegas where status =  :stat  and baseReading_id is not null order by date asc")
				.addEntity(InvoiceGas.class).setParameter("stat", PaymentStatus.UNPAID.getPaymentStatus());
		@SuppressWarnings("unchecked")
		List<InvoiceGas> invoice = query.list();
		return invoice;
	}

	@Override
	public InvoiceGas getLastResolved() {
		Query query = getSession()
				.createSQLQuery("select * from invoicegas where status =  :stat  order by date desc limit 1")
				.addEntity(InvoiceGas.class).setParameter("stat", PaymentStatus.PAID.getPaymentStatus());
		return (InvoiceGas) query.uniqueResult();
	}

	@Override
	public void resolveInvoice(InvoiceGas invoice) {
		Query query = getSession().createSQLQuery("update invoiceGas set status =  :stat  where id = :id")
				.addEntity(InvoiceGas.class).setParameter("stat", PaymentStatus.PAID.getPaymentStatus())
				.setParameter("id", invoice.getId());
		query.executeUpdate();

	}

	@Override
	public void unresolveInvoice(int id) {
		Query query = getSession().createSQLQuery("update invoicegas set status = :stat where id = :id")
				.addEntity(InvoiceGas.class).setParameter("stat", PaymentStatus.UNPAID.getPaymentStatus())
				.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public int getDaysOfLastInvoice() {
		try {
			Query query = getSession().createSQLQuery(
					"SELECT DATEDIFF(CURDATE()  ,invoicegas.date) FROM invoicegas order by date desc limit 1");
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception e) {
			return 0;
		}
	}

}
