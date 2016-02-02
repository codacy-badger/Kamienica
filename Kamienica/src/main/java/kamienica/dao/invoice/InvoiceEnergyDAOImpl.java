package kamienica.dao.invoice;

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

	public List<Invoice> getInvoicesForCalulation(Invoice first, Invoice second) {
		Query query = getSession()
				.createSQLQuery(
						"select * from kamienica.invoiceenergy where date >  :date1 and date <= :date2 order by date asc")
				.addEntity(InvoiceEnergy.class).setParameter("date1", first.getDate())
				.setParameter("date2", second.getDate());
		@SuppressWarnings("unchecked")
		List<Invoice> invoice = query.list();
		return invoice;
	}

	@Override
	public List<Invoice> getUnpaidInvoices() {
		Query query = getSession()
				.createSQLQuery("select * from kamienica.invoiceenergy where status =  :stat  order by date asc")
				.addEntity(InvoiceEnergy.class).setParameter("stat", PaymentStatus.UNPAID.getPaymentStatus());
		@SuppressWarnings("unchecked")
		List<Invoice> invoice = query.list();
		return invoice;
	}

}
