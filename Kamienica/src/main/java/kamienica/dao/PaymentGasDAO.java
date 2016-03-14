package kamienica.dao;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.PaymentGas;
import kamienica.model.ReadingGas;
import kamienica.model.Tenant;

public interface PaymentGasDAO extends DaoInterface<PaymentGas> {

	public void deleteByDate(String date);

	public List<PaymentGas> getGasByInvoice(Invoice invoice);

	public List<PaymentGas> getGasByReading(ReadingGas reading);

	public PaymentGas getLatestPaymentGas();

	public List<PaymentGas> getPaymentGasForTenant(Tenant tenant);

}
