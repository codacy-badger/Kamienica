package kamienica.feature.invoice;

import java.util.List;



import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kamienica.dao.AbstractDao;
import kamienica.feature.payment.PaymentStatus;

@Repository("invoiceEnergy")
public class InvoiceEnergyDAOImpl extends AbstractDao<InvoiceEnergy> implements InvoiceAbstractDao<InvoiceEnergy> {




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
