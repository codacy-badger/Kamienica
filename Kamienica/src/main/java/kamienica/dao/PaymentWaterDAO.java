package kamienica.dao;

import java.util.List;

import kamienica.model.Invoice;
import kamienica.model.InvoiceEnergy;
import kamienica.model.PaymentWater;
import kamienica.model.ReadingWater;
import kamienica.model.Tenant;

public interface PaymentWaterDAO {

	public void saveWater(List<PaymentWater> payment);

	public void delete(PaymentWater payment);

	public List<PaymentWater> getWaterByInvoice(Invoice invoice);

	public List<PaymentWater> getWaterByReading(ReadingWater reading);

	public List<PaymentWater> getPaymentWater();

	public PaymentWater getLatestPaymentWater();

	public List<PaymentWater> getPaymentWaterForTenant(Tenant tenant);

;
}
