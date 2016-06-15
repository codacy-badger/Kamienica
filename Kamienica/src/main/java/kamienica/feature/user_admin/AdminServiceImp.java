package kamienica.feature.user_admin;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.invoice.InvoiceService;
import kamienica.feature.reading.ReadingService;

@Service
@Transactional
public class AdminServiceImp implements AdminService {

	@Autowired
	ApartmentDao apartmentDao;
	@Autowired
	private ReadingService readingService;
	@Autowired
	private InvoiceService invoiceService;

	@Override
	public HashMap<String, Object> getMainData() {
		HashMap<String, Object> model = new HashMap<>();
		model.put("emptyApartments", apartmentDao.getNumOfEmptyApartment());
		addLatestReadings(model);
		return model;
	}

	public void addLatestReadings(HashMap<String, Object> model) {
		int energy = readingService.countLatestEnergyDays();
		int gas = readingService.countLatestGasDays();
		int water = readingService.countLatestWaterDays();
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

}
