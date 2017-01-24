package kamienica.feature.payment;

import kamienica.model.PaymentWater;
import org.springframework.stereotype.Repository;

@Repository("paymentWaterDao")
public class PaymentWaterDAOImpl extends PaymentAbstractDaoImpl<PaymentWater>
		implements PaymentDao<PaymentWater> {

//	@Override
//	public List<PaymentWater> getPaymentForTenant(Tenant tenant) {
//		@SuppressWarnings("unchecked")
//		List<PaymentWater> list = getSession().createCriteria(PaymentWater.class).add(Restrictions.eq("tenant", tenant))
//				.addOrder(Order.asc("paymentDate")).addOrder(Order.asc("tenant")).list();
//		return list;
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<PaymentWater> getByInvoice(Invoice invoice) {
//		Query query = getSession().createSQLQuery("Select * from paymentwater where invoice_id = :id")
//				.addEntity(PaymentWater.class).setLong("id", invoice.getId());
//		List<PaymentWater> result = query.list();
//		return result;
//	}
//
//	@Override
//	public List<PaymentWater> getByReading(ReadingWater reading) {
//		Query query = getSession().createSQLQuery("Select * from paymentwater where readingdate >=:date")
//				.addEntity(PaymentWater.class).setParameter("date", reading.getReadingDate());
//		@SuppressWarnings("unchecked")
//		List<PaymentWater> result = query.list();
//		return result;
//	}
//
//	@Override
//	public List<PaymentWater> getListForOwner() {
//		@SuppressWarnings("unchecked")
//		List<PaymentWater> list = getSession().createCriteria(PaymentWater.class).addOrder(Order.asc("paymentDate"))
//				.addOrder(Order.asc("tenant")).list();
//		return list;
//	}
//
//	@Override
//	public PaymentWater getLatestPayment() {
//		@SuppressWarnings("unchecked")
//
//		List<PaymentWater> list = getSession().createCriteria(PaymentWater.class).addOrder(Order.desc("readingDate"))
//				.list();
//		if (list.isEmpty()) {
//			return new PaymentWater();
//		}
//		return list.get(0);
//
//	}
//
//	@Override
//	public void deleteByDate(String date) {
//		Query query = getSession().createSQLQuery("delete from paymentwater where paymentdate =:date")
//				.addEntity(PaymentWater.class).setString("date", date);
//		query.executeUpdate();
//
//	}
}
