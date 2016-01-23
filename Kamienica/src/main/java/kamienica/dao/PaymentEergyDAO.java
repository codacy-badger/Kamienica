package kamienica.dao;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.PaymentEnergy;
import kamienica.model.ReadingEnergy;
import kamienica.model.Tenant;

public interface PaymentEergyDAO {

	public void saveEnergy(List<PaymentEnergy> payment);

	public void delete(PaymentEnergy payment);


	public List<PaymentEnergy> getEnergyByInvoice(Invoice invoice);

	public List<PaymentEnergy> getEnergyByReading(ReadingEnergy reading);

	public List<PaymentEnergy> getPaymentEnergy();
	
	public List<PaymentEnergy> getPaymentEnergyForTenant(Tenant tenant);

	public PaymentEnergy getLatestPaymentEnergy();
	

//	@Override
//	public void saveGas(List<PaymentGas> payment) {
//		
//		Transaction tx = getSession().beginTransaction();
//
//		for (int i = 0; i < payment.size(); i++) {
//			PaymentGas tmp = payment.get(i);
//			getSession().save(tmp);
//			if (i % 20 == 0) { // 20, same as the JDBC batch size
//				// flush a batch of inserts and release memory:
//				getSession().flush();
//				getSession().clear();
//			}
//		}
//		tx.commit();
//		getSession().close();
//		
//	}
}
