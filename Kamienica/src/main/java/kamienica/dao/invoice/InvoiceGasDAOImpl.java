package kamienica.dao.invoice;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.model.Invoice;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentStatus;

@Repository("invoiceGas")
@Transactional
public class InvoiceGasDAOImpl extends AbstractDao<Integer, InvoiceGas> implements InvoiceGasDAO {

	public void deleteByID(int id) {
		Query query = getSession().createSQLQuery("delete from invoicegas where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();

	}

	//
	//
	//
	// public Invoice getGasByID(int id) {
	// Session session = this.sessionfactory.getCurrentSession();
	// session.beginTransaction();
	// Invoice out = (InvoiceGas) session.get(InvoiceGas.class, new
	// Integer(id));
	// session.close();
	// return out;
	// }

	public InvoiceGas getLatest() {
		Query query = getSession().createSQLQuery(
				"select * from kamienica.invoicegas where date = (select MAX(date) from kamienica.invoicegas)");
		return (InvoiceGas) query.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<InvoiceGas> getInvoicesForPayment(PaymentGas payment) {

		String sql = "";
		if (payment.getId() < 1) {
			sql = "select * from kamienica.invoicegas order by date asc";
		} else {
			sql = "select * from kamienica.invoicegas where date >= (select date from kamienica.invoicegas where id = "
					+ payment.getInvoice().getId() + ") order by date asc ";
		}
		Query query = getSession().createSQLQuery(sql).addEntity(InvoiceGas.class);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceGas> getInvoicesForCalulation(Invoice invoice) {
		Query query = getSession()
				.createSQLQuery(
						"select * from kamienica.invoicegas where status = :status and date <= :date order by date asc")
				.addEntity(InvoiceGas.class).setParameter("date", invoice.getDate())
				.setParameter("status", PaymentStatus.UNPAID.getPaymentStatus());
		return query.list();
	}

	@Override
	public List<InvoiceGas> getUnpaidInvoices() {
		Query query = getSession().createSQLQuery("select * from invoicegas where status =  :stat  order by date asc")
				.addEntity(InvoiceGas.class).setParameter("stat", PaymentStatus.UNPAID.getPaymentStatus());
		@SuppressWarnings("unchecked")
		List<InvoiceGas> invoice = query.list();
		return invoice;
	}
}
