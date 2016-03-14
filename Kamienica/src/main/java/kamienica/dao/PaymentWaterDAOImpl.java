package kamienica.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import kamienica.model.Invoice;
import kamienica.model.PaymentWater;
import kamienica.model.ReadingWater;
import kamienica.model.Tenant;

@Repository("paymentWaterDao")
public class PaymentWaterDAOImpl extends AbstractDao<Integer, PaymentWater> implements PaymentWaterDAO {

	@Override
	public List<PaymentWater> getPaymentWaterForTenant(Tenant tenant) {
		@SuppressWarnings("unchecked")
		List<PaymentWater> list = getSession().createCriteria(PaymentWater.class).add(Restrictions.eq("tenant", tenant))
				.addOrder(Order.asc("paymentDate")).addOrder(Order.asc("tenant")).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<PaymentWater> getWaterByInvoice(Invoice invoice) {
		Query query = getSession().createSQLQuery("Select * from paymentwater where invoice_id = :id")
				.addEntity(PaymentWater.class).setInteger("id", invoice.getId());
		List<PaymentWater> result = query.list();
		return result;
	}

	@Override
	public List<PaymentWater> getWaterByReading(ReadingWater reading) {
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
	public PaymentWater getLatestPaymentWater() {
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
