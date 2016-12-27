package kamienica.feature.payment;

import kamienica.model.PaymentGas;
import org.springframework.stereotype.Repository;

@Repository("paymentGasDao")
public class PaymentGasDAOImpl extends PaymentAbstractDaoImpl<PaymentGas> implements PaymentDao<PaymentGas> {

	// @Override
	// public List<PaymentGas> getPaymentForTenant(Tenant tenant) {
	// @SuppressWarnings("unchecked")
	// List<PaymentGas> list =
	// getSession().createCriteria(PaymentGas.class).add(Restrictions.eq("tenant",
	// tenant))
	// .addOrder(Order.asc("paymentDate")).addOrder(Order.asc("tenant")).list();
	// return list;
	// }
	//
	// @Override
	// @SuppressWarnings("unchecked")
	// public List<PaymentGas> getByInvoice(Invoice invoice) {
	// Query query = getSession().createSQLQuery("Select * from paymentenergy
	// where invoice_id = :id")
	// .addEntity(PaymentGas.class).setLong("id", invoice.getId());
	// List<PaymentGas> result = query.list();
	// return result;
	// }
	//
	// @Override
	// public List<PaymentGas> getByReading(ReadingGas reading) {
	// Query query = getSession().createSQLQuery("Select * from paymentenergy
	// where readingdate >=:date")
	// .addEntity(PaymentGas.class).setParameter("date",
	// reading.getReadingDate());
	// @SuppressWarnings("unchecked")
	// List<PaymentGas> result = query.list();
	// return result;
	// }
	//
	// @Override
	// public List<PaymentGas> getList() {
	// @SuppressWarnings("unchecked")
	// List<PaymentGas> list =
	// getSession().createCriteria(PaymentGas.class).addOrder(Order.asc("paymentDate"))
	// .addOrder(Order.asc("tenant")).list();
	// return list;
	// }
	//
	// @Override
	// public PaymentGas getLatestPayment() {
	// @SuppressWarnings("unchecked")
	//
	// List<PaymentGas> list =
	// getSession().createCriteria(PaymentGas.class).addOrder(Order.desc("readingDate"))
	// .list();
	// if (list.isEmpty()) {
	// return new PaymentGas();
	// }
	// return list.get(0);
	//
	// }
	//
	// public void saveGas(List<PaymentGas> payment) {
	//
	// for (int i = 0; i < payment.size(); i++) {
	// PaymentGas tmp = payment.get(i);
	// save(tmp);
	//
	// }
	// }
	//
	// @Override
	// public void deleteByDate(String date) {
	// Query query = getSession().createSQLQuery("delete from paymentgas where
	// paymentdate =:date")
	// .addEntity(PaymentGas.class).setString("date", date);
	// query.executeUpdate();
	//
	// }
}
