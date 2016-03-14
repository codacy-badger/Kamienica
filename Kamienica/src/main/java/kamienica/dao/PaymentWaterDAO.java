package kamienica.dao;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.PaymentWater;
import kamienica.model.ReadingWater;
import kamienica.model.Tenant;

public interface PaymentWaterDAO extends DaoInterface<PaymentWater> {

	public void deleteByDate(String date);

	public List<PaymentWater> getWaterByInvoice(Invoice invoice);

	public List<PaymentWater> getWaterByReading(ReadingWater reading);

	public PaymentWater getLatestPaymentWater();

	public List<PaymentWater> getPaymentWaterForTenant(Tenant tenant);

}
