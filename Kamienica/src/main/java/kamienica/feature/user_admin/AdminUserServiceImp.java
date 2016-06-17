package kamienica.feature.user_admin;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.Media;
import kamienica.dao.DaoInterface;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.invoice.InvoiceEnergy;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.invoice.InvoiceService;
import kamienica.feature.meter.MeterEnergy;
import kamienica.feature.meter.MeterGas;
import kamienica.feature.meter.MeterWater;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.reading.ReadingDao;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingService;
import kamienica.feature.reading.ReadingWater;
import kamienica.feature.reading.ReadingWaterDAO;

@Service
@Transactional
public class AdminUserServiceImp implements AdminUserService {

	@Autowired
	ApartmentDao apartmentDao;
	@Autowired
	private ReadingService readingService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	ReadingDao<ReadingEnergy, InvoiceEnergy> energyDao;
	@Autowired
	ReadingWaterDAO waterDao;
	@Autowired
	ReadingDao<ReadingGas, InvoiceGas> gasDao;
	@Autowired
	DaoInterface<MeterEnergy> meterEnergyDao;
	@Autowired
	DaoInterface<MeterGas> meterGasDao;
	@Autowired
	DaoInterface<MeterWater> meterWaterDao;

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

		energy = invoiceService.getDaysForEnergy();
		gas = invoiceService.getDaysForGas();
		water = invoiceService.getDaysForWater();

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
