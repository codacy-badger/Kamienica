package kamienica.feature.user_admin;

import java.util.HashMap;
import java.util.List;

import kamienica.core.Media;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.payment.PaymentEnergy;
import kamienica.feature.payment.PaymentGas;
import kamienica.feature.payment.PaymentWater;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;
import kamienica.feature.tenant.Tenant;

public interface AdminUserService {

	public HashMap<String, Object> getMainData();

	public List<? extends ReadingAbstract> getReadingsForTenant(Apartment apartment, Media media);

	public List<ReadingEnergy> getReadingEnergyForTenant(Apartment apartment);

	public List<ReadingWater> getReadingWaterForTenant(Apartment apartment);

	public List<ReadingGas> getReadingGasForTenant(Apartment apartment);

//	public List<PaymentEnergy> getPaymentEnergyForTenant(Tenant tenant);
//
//	public List<PaymentGas> getPaymentGasForTenant(Tenant tenant);
//
//	public List<PaymentWater> getPaymentWaterForTenant(Tenant tenant);
	
}
