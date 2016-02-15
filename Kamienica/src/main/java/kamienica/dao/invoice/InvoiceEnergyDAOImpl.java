package kamienica.dao.invoice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.model.Invoice;
import kamienica.model.InvoiceEnergy;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentStatus;

@Repository("invoiceEnergy")
@Transactional
public class InvoiceEnergyDAOImpl extends AbstractDao<Integer, InvoiceEnergy> implements InvoiceEnergyDAO {

	public void deleteByID(int id) {
		Query query = getSession().createSQLQuery("delete from invoiceenergy where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();

	}

	//
	//
	//
	// public Invoice getEnergyByID(int id) {
	// Session session = this.sessionfactory.getCurrentSession();
	// session.beginTransaction();
	// Invoice out = (InvoiceEnergy) session.get(InvoiceEnergy.class, new
	// Integer(id));
	// session.close();
	// return out;
	// }

	public InvoiceEnergy getLatest() {
		Query query = getSession().createSQLQuery(
				"select * from kamienica.invoiceenergy where date = (select MAX(date) from kamienica.invoiceenergy)");
		return (InvoiceEnergy) query.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<InvoiceEnergy> getInvoicesForPayment(PaymentEnergy payment) {

		String sql = "";
		if (payment.getId() < 1) {
			sql = "select * from kamienica.invoiceenergy order by date asc";
		} else {
			sql = "select * from kamienica.invoiceenergy where date >= (select date from kamienica.invoiceenergy where id = "
					+ payment.getInvoice().getId() + ") order by date asc ";
		}
		Query query = getSession().createSQLQuery(sql).addEntity(InvoiceEnergy.class);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceEnergy> getInvoicesForCalulation(Invoice invoice) {
		Query query = getSession()
				.createSQLQuery(
						"select * from kamienica.invoiceenergy where status = :status and date <= :date order by date asc")
				.addEntity(InvoiceEnergy.class).setParameter("date", invoice.getDate())
				.setParameter("status", PaymentStatus.UNPAID.getPaymentStatus());
		return query.list();

	}

	@Override
	public List<InvoiceEnergy> getUnpaidInvoices() {
		Query query = getSession()
				.createSQLQuery("select * from invoiceenergy where status =  :stat  order by date asc")
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

}
