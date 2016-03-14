package kamienica.dao.invoice;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.model.Invoice;
import kamienica.model.InvoiceEnergy;
import kamienica.model.PaymentStatus;

@Repository("invoiceEnergy")
@Transactional
public class InvoiceEnergyDAOImpl extends AbstractDao<Integer, InvoiceEnergy> implements InvoiceEnergyDAO {



	@Override
	public InvoiceEnergy getLatest() {
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



}
