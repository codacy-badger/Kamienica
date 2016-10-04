package kamienica.feature.user_admin;

import java.util.HashMap;
import java.util.List;

import kamienica.core.util.Media;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.reading.Reading;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;

public interface AdminUserService {

	public HashMap<String, Object> getMainData();

	public List<? extends Reading> getReadingsForTenant(Apartment apartment, Media media);

	public List<ReadingEnergy> getReadingEnergyForTenant(Apartment apartment);

	public List<ReadingWater> getReadingWaterForTenant(Apartment apartment);

	public List<ReadingGas> getReadingGasForTenant(Apartment apartment);

//	public List<PaymentEnergy> getPaymentEnergyForTenant(Tenant tenant);
//
//	public List<PaymentGas> getPaymentGasForTenant(Tenant tenant);
//
//	public List<PaymentWater> getPaymentWaterForTenant(Tenant tenant);
	
}
