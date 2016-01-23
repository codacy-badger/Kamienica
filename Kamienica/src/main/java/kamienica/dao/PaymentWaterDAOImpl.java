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

	public void delete(PaymentWater payment) {
		Query query = getSession().createSQLQuery("delete from paymentwater where id = :id");
		query.setInteger("id", payment.getId());
		query.executeUpdate();
	}


	@Override
	public List<PaymentWater> getPaymentWaterForTenant(Tenant tenant) {
		@SuppressWarnings("unchecked")
		List<PaymentWater> list = getSession().createCriteria(PaymentWater.class).add(Restrictions.eq("tenant", tenant)).addOrder(Order.asc("paymentDate"))
				.addOrder(Order.asc("tenant")).list();
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
		Query query = getSession().createSQLQuery("Select * from paymentwater where readingdate =:date")
				.addEntity(PaymentWater.class).setDate("date", reading.getReadingDate());
		@SuppressWarnings("unchecked")
		List<PaymentWater> result = query.list();
		return result;
	}

	@Override
	public List<PaymentWater> getPaymentWater() {
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

	public void saveWater(List<PaymentWater> payment) {

		for (int i = 0; i < payment.size(); i++) {
			PaymentWater tmp = payment.get(i);
			save(tmp);

		}
	}
}
