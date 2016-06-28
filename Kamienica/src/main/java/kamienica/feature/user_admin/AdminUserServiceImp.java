package kamienica.feature.user_admin;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.Media;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.invoice.InvoiceDao;
import kamienica.feature.invoice.InvoiceEnergy;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.invoice.InvoiceWater;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.reading.ReadingDao;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;
import kamienica.feature.reading.ReadingWaterDAO;

@Service
@Transactional
public class AdminUserServiceImp implements AdminUserService {

	@Autowired
	private ApartmentDao apartmentDao;
	@Autowired
	private ReadingDao<ReadingEnergy, InvoiceEnergy> energyDao;
	@Autowired
	private ReadingWaterDAO waterDao;
	@Autowired
	private ReadingDao<ReadingGas, InvoiceGas> gasDao;
	@Autowired
	@Qualifier("invoiceEnergy")
	private InvoiceDao<InvoiceEnergy> invoiceEnergyDao;
	@Autowired
	@Qualifier("invoiceWater")
	private InvoiceDao<InvoiceWater> invoiceWaterDao;
	@Autowired
	@Qualifier("invoiceGas")
	private InvoiceDao<InvoiceGas> invoiceGasDao;

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

}
