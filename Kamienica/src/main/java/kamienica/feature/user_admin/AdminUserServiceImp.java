package kamienica.feature.user_admin;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.Media;
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

@Service
@Transactional
public class AdminUserServiceImp implements AdminUserService {

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
//	@Autowired
//	private PaymentDao<PaymentEnergy, ReadingEnergy> pamymentEnergyDao;
//	@Autowired
//	private PaymentDao<PaymentGas, ReadingEnergy> pamymentGasDao;
//	@Autowired
//	private PaymentDao<PaymentWater, ReadingEnergy> pamymentWaterDao;

	@Override
	public HashMap<String, Object> getMainData() {
		HashMap<String, Object> model = new HashMap<>();
		model.put("emptyApartments", apartmentDao.getNumOfEmptyApartment());
		addLatestReadings(model);
		return model;
	}

	public void addLatestReadings(HashMap<String, Object> model) {

		int energy = energyDao.countDaysFromLastReading();
		int gas = gasDao.countDaysFromLastReading();
		int water = waterDao.countDaysFromLastReading();
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
			System.out.println("getReadingsForTenant - energia");
			return energyDao.getListForTenant(apartment);

		case GAS:
			System.out.println("getReadingsForTenant - gas");
			return gasDao.getListForTenant(apartment);

		case WATER:
			System.out.println("getReadingsForTenant - woda");
			return waterDao.getListForTenant(apartment);

		default:
			throw new IllegalArgumentException();
		}

	}

//	@Override
//	public List<PaymentEnergy> getPaymentEnergyForTenant() {
//		return energy.getPaymentForTenant(tenant);
//	}
//
//	@Override
//	public List<PaymentGas> getPaymentGasForTenant() {
//		return gas.getPaymentForTenant(tenant);
//	}
//
//	@Override
//	public List<PaymentWater> getPaymentWaterForTenant() {
//		return water.getPaymentForTenant(tenant);
//	}
	
	
	public SecurityUser getCurrentUser() {
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
}
