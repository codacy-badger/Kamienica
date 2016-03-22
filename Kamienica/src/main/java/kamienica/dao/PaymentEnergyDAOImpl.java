package kamienica.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.model.Invoice;
import kamienica.model.PaymentEnergy;
import kamienica.model.ReadingEnergy;
import kamienica.model.Tenant;

@Repository("paymentEnergyDao")
public class PaymentEnergyDAOImpl extends AbstractDao<Integer, PaymentEnergy>
		implements PaymentDao<PaymentEnergy, ReadingEnergy> {

	@SuppressWarnings("unchecked")
	public List<PaymentEnergy> getByInvoice(Invoice invoice) {
		Query query = getSession().createSQLQuery("Select * from paymentenergy where invoice_id = :id")
				.addEntity(PaymentEnergy.class).setInteger("id", invoice.getId());
		List<PaymentEnergy> result = query.list();
		return result;
	}

	@Override
	public List<PaymentEnergy> getByReading(ReadingEnergy reading) {
		Query query = getSession().createSQLQuery("Select * from paymentenergy where readingdate =:date")
				.addEntity(PaymentEnergy.class).setDate("date", reading.getReadingDate());
		@SuppressWarnings("unchecked")
		List<PaymentEnergy> result = query.list();
		return result;
	}

	@Override
	public List<PaymentEnergy> getList() {
		@SuppressWarnings("unchecked")
		List<PaymentEnergy> list = getSession().createCriteria(PaymentEnergy.class).addOrder(Order.asc("paymentDate"))
				.addOrder(Order.asc("tenant")).list();
		return list;
	}

	@Override
	public List<PaymentEnergy> getPaymentForTenant(Tenant tenant) {
		@SuppressWarnings("unchecked")
		List<PaymentEnergy> list = getSession().createCriteria(PaymentEnergy.class)
				.add(Restrictions.eq("tenant", tenant)).addOrder(Order.asc("paymentDate")).addOrder(Order.asc("tenant"))
				.list();
		return list;
	}

	@Override
	public PaymentEnergy getLatestPayment() {
		@SuppressWarnings("unchecked")

		List<PaymentEnergy> list = getSession().createCriteria(PaymentEnergy.class).addOrder(Order.desc("readingDate"))
				.list();
		if (list.isEmpty()) {
			return new PaymentEnergy();
		}
		return list.get(0);

	}

	@Override
	public void deleteByDate(String date) {
		Query query = getSession().createSQLQuery("delete from paymentenergy where paymentdate >= :date")
				.addEntity(PaymentEnergy.class).setString("date", date);
		query.executeUpdate();

	}
}
