package kamienica.dao;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.ReadingGas;
import kamienica.model.Tenant;

public interface PaymentGasDAO {

	public void saveGas(List<PaymentGas> payment);

	public void delete(PaymentGas payment);
	
	public void deleteByDate(String date);

	public List<PaymentGas> getGasByInvoice(Invoice invoice);

	public List<PaymentGas> getGasByReading(ReadingGas reading);

	public List<PaymentGas> getPaymentGas();

	public PaymentGas getLatestPaymentGas();

	public List<PaymentGas> getPaymentGasForTenant(Tenant tenant);

	public PaymentGas getById(int id);

}
