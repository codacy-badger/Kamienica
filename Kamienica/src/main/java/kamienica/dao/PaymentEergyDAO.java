package kamienica.dao;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.PaymentEnergy;
import kamienica.model.ReadingEnergy;
import kamienica.model.Tenant;

public interface PaymentEergyDAO {

	public void saveEnergy(List<PaymentEnergy> payment);

	public void update(PaymentEnergy payment);

	public void delete(PaymentEnergy payment);

	public void deleteByDate(String date);

	public PaymentEnergy getById(int id);

	public List<PaymentEnergy> getEnergyByInvoice(Invoice invoice);

	public List<PaymentEnergy> getEnergyByReading(ReadingEnergy reading);

	public List<PaymentEnergy> getPaymentEnergy();

	public List<PaymentEnergy> getPaymentEnergyForTenant(Tenant tenant);

	public PaymentEnergy getLatestPaymentEnergy();

}
