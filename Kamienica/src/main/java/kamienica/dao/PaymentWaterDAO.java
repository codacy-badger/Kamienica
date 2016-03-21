package kamienica.dao;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.PaymentWater;
import kamienica.model.ReadingWater;
import kamienica.model.Tenant;

public interface PaymentWaterDAO extends DaoInterface<PaymentWater> {

	public void deleteByDate(String date);

	public List<PaymentWater> getByInvoice(Invoice invoice);

	public List<PaymentWater> getByReading(ReadingWater reading);

	public PaymentWater getLatestPayment();

	public List<PaymentWater> getPaymentForTenant(Tenant tenant);

}
