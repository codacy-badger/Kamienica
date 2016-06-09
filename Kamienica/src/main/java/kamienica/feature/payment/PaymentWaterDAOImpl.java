package kamienica.feature.payment;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.feature.invoice.Invoice;
import kamienica.feature.reading.ReadingWater;
import kamienica.feature.tenant.Tenant;

@Repository("paymentWaterDao")
public class PaymentWaterDAOImpl extends AbstractDao<Integer, PaymentWater>
		implements PaymentDao<PaymentWater, ReadingWater> {

	@Override
	public List<PaymentWater> getPaymentForTenant(Tenant tenant) {
		@SuppressWarnings("unchecked")
		List<PaymentWater> list = getSession().createCriteria(PaymentWater.class).add(Restrictions.eq("tenant", tenant))
				.addOrder(Order.asc("paymentDate")).addOrder(Order.asc("tenant")).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<PaymentWater> getByInvoice(Invoice invoice) {
		Query query = getSession().createSQLQuery("Select * from paymentwater where invoice_id = :id")
				.addEntity(PaymentWater.class).setInteger("id", invoice.getId());
		List<PaymentWater> result = query.list();
		return result;
	}

	@Override
	public List<PaymentWater> getByReading(ReadingWater reading) {
		Query query = getSession().createSQLQuery("Select * from paymentwater where readingdate >=:date")
				.addEntity(PaymentWater.class).setDate("date", reading.getReadingDate());
		@SuppressWarnings("unchecked")
		List<PaymentWater> result = query.list();
		return result;
	}

	@Override
	public List<PaymentWater> getList() {
		@SuppressWarnings("unchecked")
		List<PaymentWater> list = getSession().createCriteria(PaymentWater.class).addOrder(Order.asc("paymentDate"))
				.addOrder(Order.asc("tenant")).list();
		return list;
	}

	@Override
	public PaymentWater getLatestPayment() {
		@SuppressWarnings("unchecked")

		List<PaymentWater> list = getSession().createCriteria(PaymentWater.class).addOrder(Order.desc("readingDate"))
				.list();
		if (list.isEmpty()) {
			return new PaymentWater();
		}
		return list.get(0);

	}

	@Override
	public void deleteByDate(String date) {
		Query query = getSession().createSQLQuery("delete from paymentwater where paymentdate =:date")
				.addEntity(PaymentWater.class).setString("date", date);
		query.executeUpdate();

	}
}
