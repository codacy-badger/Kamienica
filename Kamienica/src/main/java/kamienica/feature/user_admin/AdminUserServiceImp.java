package kamienica.feature.user_admin;

import java.util.HashMap;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.util.Media;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.invoice.InvoiceAbstractDao;
import kamienica.feature.invoice.InvoiceEnergy;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.invoice.InvoiceWater;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingEnergyDao;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingGasDao;
import kamienica.feature.reading.ReadingWater;
import kamienica.feature.reading.ReadingWaterDao;
import kamienica.feature.settings.Settings;
import kamienica.feature.settings.SettingsDao;

@Service
@Transactional
public class AdminUserServiceImp implements AdminUserService {

	@Autowired
	SettingsDao settingsDao;
	@Autowired
	private ApartmentDao apartmentDao;
	@Autowired
	private ReadingEnergyDao energyDao;
	@Autowired
	private ReadingWaterDao waterDao;
	@Autowired
	private ReadingGasDao gasDao;
	@Autowired
	@Qualifier("invoiceEnergy")
	private InvoiceAbstractDao<InvoiceEnergy> invoiceEnergyDao;
	@Autowired
	@Qualifier("invoiceWater")
	private InvoiceAbstractDao<InvoiceWater> invoiceWaterDao;
	@Autowired
	@Qualifier("invoiceGas")
	private InvoiceAbstractDao<InvoiceGas> invoiceGasDao;

	@Override
	public HashMap<String, Object> getMainData() {
		HashMap<String, Object> model = new HashMap<>();
		model.put("emptyApartments", apartmentDao.getNumOfEmptyApartment());
		addLatestReadings(model);
		checkConfig(model);
		return model;
	}

	private void checkConfig(HashMap<String, Object> model) {
		List<Settings> list = settingsDao.getList();
		if (list.isEmpty()) {
			model.put("settings", "BRAK USTAWIEŃ");
		} else if (!list.get(0).isCorrectDivision()) {
			model.put("settings", "Podział niekatualny");
		}
	}

	public void addLatestReadings(HashMap<String, Object> model) {

		int energy =  countDays(energyDao.getLatestDate());
		int gas = countDays(gasDao.getLatestDate());
		int water = countDays(waterDao.getLatestDate());
		String media;
		int days;
		if (energy > gas && energy > water) {
			days = energy;
			media = "Energia";
		} else if (water > gas) {
			days = water;
			media = "Woda";
		} else {
			days = gas;
			media = "Gaz";
		}
		model.put("readingMedia", media);
		model.put("radingDays", days);

		energy = invoiceEnergyDao.getDaysOfLastInvoice();
		gas = invoiceGasDao.getDaysOfLastInvoice();
		water = invoiceWaterDao.getDaysOfLastInvoice();

		if (energy > gas && energy > water) {
			days = energy;
			media = "Energia";
		} else if (water > gas) {
			days = water;
			media = "Woda";
		} else {
			days = gas;
			media = "Gaz";
		}
		model.put("invoiceMedia", media);
		model.put("invoiceDays", days);

	}

	@Override
	public List<ReadingEnergy> getReadingEnergyForTenant(Apartment aparmtent) {
		return energyDao.getListForTenant(aparmtent);
	}

	@Override
	public List<ReadingGas> getReadingGasForTenant(Apartment aparmtent) {
		return gasDao.getListForTenant(aparmtent);
	}

	@Override
	public List<ReadingWater> getReadingWaterForTenant(Apartment aparmtent) {
		return waterDao.getListForTenant(aparmtent);
	}

	@Override
	public List<? extends ReadingAbstract> getReadingsForTenant(Apartment apartment, Media media) {

		switch (media) {
		case ENERGY:
			return energyDao.getListForTenant(apartment);

		case GAS:
			return gasDao.getListForTenant(apartment);

		case WATER:
			return waterDao.getListForTenant(apartment);

		default:
			throw new IllegalArgumentException();
		}

	}

	// @Override
	// public List<PaymentEnergy> getPaymentEnergyForTenant() {
	// return energy.getPaymentForTenant(tenant);
	// }
	//
	// @Override
	// public List<PaymentGas> getPaymentGasForTenant() {
	// return gas.getPaymentForTenant(tenant);
	// }
	//
	// @Override
	// public List<PaymentWater> getPaymentWaterForTenant() {
	// return water.getPaymentForTenant(tenant);
	// }

	public SecurityUser getCurrentUser() {
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}

	private int countDays(LocalDate date) {
		LocalDate now = LocalDate.now();
		return Days.daysBetween(date, now).getDays();
	}
}
