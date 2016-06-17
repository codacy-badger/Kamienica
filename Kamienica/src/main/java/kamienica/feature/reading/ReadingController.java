package kamienica.feature.reading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.Media;
import kamienica.feature.meter.MeterAbstract;
import kamienica.feature.meter.MeterEnergy;
import kamienica.feature.meter.MeterGas;
import kamienica.feature.meter.MeterService;
import kamienica.feature.meter.MeterWater;

@Controller
public class ReadingController {

	@Autowired
	private MeterService meterService;
	@Autowired
	private ReadingService readingService;

	// -----------------------------------REGISTER---------------------------------------------------------------

	@RequestMapping("/Admin/Reading/readingEnergyRegister")
	public ModelAndView readingEnergyRegister(@ModelAttribute("readingForm") ReadingEnergyForm readingForm,
			BindingResult result) {
		List<MeterEnergy> meterEnergy = meterService.getEnergyList();
		HashMap<String, Object> model = new HashMap<>();
		if (!validateMeters(meterEnergy)) {
			model.put("error", "Brakuje licznika głównego. Wprowadź brakujące liczniki");
			return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
		}

		List<ReadingEnergy> readings = readingService.getLatestEnergyReadings(meterService.getIdList(Media.ENERGY));
		model.put("date", new LocalDate());
		readingForm.setCurrentReadings(readings);
		readingForm.setNewReadings(readings);
		if (readings.isEmpty()) {
			model.put("oldDate", "2000-01-01");

		} else {
			model.put("oldDate", readings.get(0).getReadingDate().plusDays(1));
		}

		return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
	}

	@RequestMapping("/Admin/Reading/readingGasRegister")
	public ModelAndView readingGasRegister(@ModelAttribute("readingForm") ReadingGasForm readingForm,
			BindingResult result) {
		List<MeterGas> meterGas = meterService.getGasList();
		HashMap<String, Object> model = new HashMap<>();
		if (!validateMeters(meterGas)) {
			model.put("error", "Brakuje licznika głównego. Wprowadź brakujące liczniki");
			model.put("url", "/Admin/Reading/readingEnergySave.html");
			return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
		}
		List<ReadingGas> readings = readingService.getLatestGasReadings(meterService.getIdList(Media.GAS));
		model.put("date", new LocalDate());

		readingForm.setCurrentReadings(readings);

		if (readings.isEmpty()) {
			model.put("oldDate", "2000-01-01");

		} else {
			model.put("oldDate", readings.get(0).getReadingDate().plusDays(1));
		}

		return new ModelAndView("/Admin/Reading/ReadingGasRegister", "model", model);
	}

	@RequestMapping("/Admin/Reading/readingWaterRegister")
	public ModelAndView readingWaterRegister(@ModelAttribute("readingForm") ReadingWaterForm readingWaterForm,
			BindingResult result) {
		List<MeterWater> meterWater = meterService.getWaterList();
		HashMap<String, Object> model = new HashMap<>();
		if (!validateMeters(meterWater)) {
			model.put("error", "Brakuje licznika głównego. Wprowadź brakujące liczniki");
			return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
		}
		List<ReadingWater> readings = readingService.getLatestWaterReadings(meterService.getIdList(Media.WATER));

		model.put("date", new LocalDate());

		readingWaterForm.setCurrentReadings(readings);

		if (readings.isEmpty()) {
			model.put("oldDate", "2000-01-01");
		} else {
			model.put("oldDate", readings.get(0).getReadingDate().plusDays(1));
		}
		return new ModelAndView("/Admin/Reading/ReadingWaterRegister", "model", model);
	}
	// --------------------------------SAVE-----------------------------------------------------------------

	@RequestMapping(value = "/Admin/Reading/readingEnergySave", method = RequestMethod.POST)
	public ModelAndView readingEnergySave(@ModelAttribute("readingForm") ReadingEnergyForm readingForm,
			BindingResult result, @RequestParam String date) {

		if (ReadingValidator.validateMeterReadings(readingForm.getCurrentReadings(), readingForm.getNewReadings())) {
			return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "error",
					"Nowa wartość nie może być mniejsza od poprzedniej");
		}

		readingService.saveEnergyList(readingForm.getNewReadings(), LocalDate.parse(date));
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=ENERGY");
	}

	@RequestMapping(value = "/Admin/Reading/readingGasSave", method = RequestMethod.POST)
	public ModelAndView readingGasSave(@ModelAttribute("readingForm") ReadingGasForm readingForm, BindingResult result,
			@RequestParam String date) {

		readingService.saveGasList(readingForm.getCurrentReadings(), LocalDate.parse(date));
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=GAS");
	}

	@RequestMapping(value = "/Admin/Reading/readingWaterSave", method = RequestMethod.POST)
	public ModelAndView readingWaterSave(@ModelAttribute("readingForm") ReadingWaterForm readingWaterForm,
			BindingResult result, @RequestParam String date) {

		readingService.saveWaterList(readingWaterForm.getCurrentReadings(), LocalDate.parse(date));
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=WATER");
	}
	// -----------------------------LIST-------------------------------------------------------

	@RequestMapping("/Admin/Reading/readingList")
	public ModelAndView readingList(@RequestParam("media") Media media) {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println(media);
		switch (media) {
		case ENERGY:
			System.out.println("hellooo!");
			model.put("reading", readingService.getReadingEnergy());
			model.put("media", "Energia");
			model.put("editUrl", "/Admin/Reading/readingEnergyEdit.html?date=");
			model.put("delUrl", "/Admin/Reading/readingEnergyDelete.html?date=");

			break;
		case WATER:
			model.put("reading", readingService.getReadingWater());
			model.put("media", "Woda");
			model.put("editUrl", "/Admin/Reading/readingWaterEdit.html?date=");
			model.put("delUrl", "/Admin/Reading/readingWaterDelete.html?date=");
			break;
		case GAS:
			model.put("reading", readingService.getReadingGas());
			model.put("media", "Gaz");
			model.put("editUrl", "/Admin/Reading/readingGasEdit.html?date=");
			model.put("delUrl", "/Admin/Reading/readingGasDelete.html?date=");
			break;
		default:
			break;
		}

		return new ModelAndView("/Admin/Reading/ReadingList", "model", model);

	}
	//
	// @RequestMapping("/Admin/Reading/readingGasList")
	// public ModelAndView odczytGazLista() {
	// Map<String, Object> model = new HashMap<String, Object>();
	// model.put("reading", readingService.getReadingGas());
	// return new ModelAndView("/Admin/Reading/ReadingGasList", model);
	//
	// }
	//
	// @RequestMapping("/Admin/Reading/readingWaterList")
	// public ModelAndView odczytWodaLista() {
	// Map<String, Object> model = new HashMap<String, Object>();
	// model.put("reading", readingService.getReadingWater());
	// return new ModelAndView("/Admin/Reading/ReadingWaterList", model);
	//
	// }
	// ------------------------DELETE------------------------------

	@RequestMapping(value = "/Admin/Reading/readingEnergyDelete")
	public ModelAndView readingEnergyDelete(@RequestParam(value = "date") String date) {
		List<ReadingEnergy> listToDelete = readingService.getReadingEnergyByDate(date);
		if (listToDelete.get(0).isResolved() == true) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("error", "Nie można usuwać odczytu, dla którego wprowadzono Fakturę.");
			return new ModelAndView("/Admin/Reading/ReadingList?media=ENERGY", "model", model);
		}

		readingService.deleteList(listToDelete, Media.ENERGY);
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=ENERGY");
	}

	@RequestMapping(value = "/Admin/Reading/readingGasDelete")
	public ModelAndView readingGasDelete(@RequestParam(value = "date") String date) {
		List<ReadingGas> listToDelete = readingService.getReadingGasByDate(date);
		if (listToDelete.get(0).isResolved() == true) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("error", "Nie można usuwać odczytu, dla którego wprowadzono Fakturę.");
			return new ModelAndView("/Admin/Reading/ReadingList?media=GAS", "model", model);
		}

		readingService.deleteList(listToDelete, Media.GAS);
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=GAS");
	}

	@RequestMapping(value = "/Admin/Reading/readingWaterDelete")
	public ModelAndView usunReadingWater(@RequestParam(value = "date") String date) {
		List<ReadingWater> listToDelete = readingService.getReadingWaterByDate(date);
		if (listToDelete.get(0).isResolved() == true) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("error", "Nie można usuwać odczytu, dla którego wprowadzono Fakturę.");
			return new ModelAndView("/Admin/Reading/ReadingList?media=WATER", "model", model);
		}
		readingService.deleteList(listToDelete, Media.WATER);
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=WATER");
	}

	// ------------------------------EDIT-----------------------------------
	@RequestMapping(value = "/Admin/Reading/readingEnergyEdit")
	public ModelAndView readingEnergyEdit(@RequestParam(value = "date") String date,
			@ModelAttribute("readingForm") ReadingEnergyForm readingForm) {
		List<ReadingEnergy> readings = readingService.getReadingEnergyByDate(date);
		List<ReadingEnergy> readings2 = readingService.getReadingEnergyByDate(date);

		readingForm.setCurrentReadings(readings);
		String oldDate = "2000-01-01";
		if (!readingService.getPreviousReadingEnergy(date).isEmpty()) {

			List<ReadingEnergy> previousReading = readingService.getPreviousReadingEnergy(date);
			oldDate = previousReading.get(0).getReadingDate().plusDays(1).toString();
			HashMap<String, ReadingEnergy> mapPreviousReadings = new HashMap<>();

			for (ReadingEnergy i : previousReading) {
				mapPreviousReadings.put(i.getMeter().getSerialNumber(), i);
			}

			for (int i = 0; i < readings2.size(); i++) {
				String serialNumber = readings2.get(i).getMeter().getSerialNumber();
				if (mapPreviousReadings.get(serialNumber) != null) {
					readings2.get(i).setValue(mapPreviousReadings.get(serialNumber).getValue());
				} else {
					readings2.get(i).setValue(0);
				}
			}
			readingForm.setPreviousReadings(readings2);
		} else {

			for (int i = 0; i < readings2.size(); i++) {
				readings2.get(i).setValue(0);
			}
			readingForm.setPreviousReadings(readings2);
			readingForm.setCurrentReadings(readings);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("date", date);
		model.put("oldDate", oldDate);
		model.put("readingForm", readingForm);
		model.put("previousReadings", readings2);
		model.put("currentReadigns", readings);

		return new ModelAndView("/Admin/Reading/ReadingEnergyEdit", "model", model);
	}

	@RequestMapping(value = "/Admin/Reading/readingGasEdit")
	public ModelAndView readingGasEdit(@RequestParam(value = "date") String date,
			@ModelAttribute("readingForm") ReadingGasForm readingForm) {

		List<ReadingGas> readings = readingService.getReadingGasByDate(date);
		List<ReadingGas> readings2 = readingService.getReadingGasByDate(date);
		readingForm.setCurrentReadings(readings);
		String oldDate = "2000-01-01";
		if (!readingService.getPreviousReadingGas(date).isEmpty()) {

			List<ReadingGas> previousReading = readingService.getPreviousReadingGas(date);
			oldDate = previousReading.get(0).getReadingDate().plusDays(1).toString();
			HashMap<String, ReadingGas> mapPreviousReadings = new HashMap<>();

			for (ReadingGas i : previousReading) {
				mapPreviousReadings.put(i.getMeter().getSerialNumber(), i);
			}

			for (int i = 0; i < readings2.size(); i++) {
				String serialNumber = readings2.get(i).getMeter().getSerialNumber();

				if (mapPreviousReadings.get(serialNumber) != null) {

					readings2.get(i).setValue(mapPreviousReadings.get(serialNumber).getValue());
				} else {
					readings2.get(i).setValue(0);
				}
			}
			readingForm.setPreviousReadings(readings2);
		} else {

			for (int i = 0; i < readings2.size(); i++) {
				readings2.get(i).setValue(0);
			}
			readingForm.setPreviousReadings(readings2);
			readingForm.setCurrentReadings(readings);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("date", date);
		model.put("oldDate", oldDate);
		model.put("readingForm", readingForm);
		model.put("previousReadings", readings2);
		model.put("currentReadings", readings);

		return new ModelAndView("/Admin/Reading/ReadingGasEdit", "model", model);
	}

	@RequestMapping(value = "/Admin/Reading/readingWaterEdit")
	public ModelAndView readingWaterEdit(@RequestParam(value = "date") String date,
			@ModelAttribute("readingForm") ReadingWaterForm readingForm) {
		List<ReadingWater> readings = readingService.getReadingWaterByDate(date);
		List<ReadingWater> readings2 = readingService.getReadingWaterByDate(date);
		readingForm.setCurrentReadings(readings);
		String oldDate = "2000-01-01";
		if (!readingService.getPreviousReadingWater(date).isEmpty()) {

			List<ReadingWater> previousReadings = readingService.getPreviousReadingWater(date);
			oldDate = previousReadings.get(0).getReadingDate().plusDays(1).toString();
			HashMap<String, ReadingWater> mapPreviousReadings = new HashMap<>();

			for (ReadingWater i : previousReadings) {
				mapPreviousReadings.put(i.getMeter().getSerialNumber(), i);
			}

			for (int i = 0; i < readings2.size(); i++) {
				String nrSeryjnyLicznika = readings2.get(i).getMeter().getSerialNumber();

				if (mapPreviousReadings.get(nrSeryjnyLicznika) != null) {

					readings2.get(i).setValue(mapPreviousReadings.get(nrSeryjnyLicznika).getValue());
				} else {
					readings2.get(i).setValue(0);
				}
			}
			readingForm.setPreviousReadings(readings2);
		} else {

			for (int i = 0; i < readings2.size(); i++) {
				readings2.get(i).setValue(0);
			}
			readingForm.setPreviousReadings(readings2);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("date", date);
		model.put("oldDate", oldDate);
		model.put("readingForm", readingForm);
		model.put("previousReadings", readings2);
		model.put("currentReadings", readings);

		return new ModelAndView("/Admin/Reading/ReadingWaterEdit", "model", model);
	}

	// -------------------------OVERWRITE--------------------------------------------
	@RequestMapping("/Admin/Reading/readingEnergyOverwrite")
	public ModelAndView readingEnergyOverwite(@ModelAttribute("readingForm") ReadingEnergyForm readingForm,
			BindingResult result, @RequestParam String date) throws ParseException {
		// String date = req.getParameter("date");
		List<ReadingEnergy> listToSave = readingForm.getCurrentReadings();

		for (ReadingEnergy i : listToSave) {
			i.setReadingDate(LocalDate.parse(date));
			i.setUnit(i.getMeter().getUnit());

		}
		readingService.updateEnergyList(listToSave);
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=ENERGY");
	}

	@RequestMapping("/Admin/Reading/readingGasOverwrite")
	public ModelAndView readingGasOverwrite(@ModelAttribute("readingForm") ReadingGasForm readingForm,
			BindingResult result, @RequestParam String date) throws ParseException {
		// String date = req.getParameter("date");
		List<ReadingGas> listToSave = readingForm.getCurrentReadings();

		for (ReadingGas i : listToSave) {
			i.setReadingDate(LocalDate.parse(date));
			i.setUnit(i.getMeter().getUnit());
			readingService.updateGas(i);
		}
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=GAS");
	}

	@RequestMapping("/Admin/Reading/readingWaterOverwrite")
	public ModelAndView readingWaterOverwrite(@ModelAttribute("readingForm") ReadingWaterForm readingForm,
			BindingResult result, @RequestParam String date) throws ParseException {
		// String date = req.getParameter("date");
		List<ReadingWater> listToSave = readingForm.getCurrentReadings();

		for (ReadingWater i : listToSave) {
			i.setReadingDate(LocalDate.parse(date));
			i.setUnit(i.getMeter().getUnit());
			readingService.updateWater(i);
		}
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media?WATER");
	}

	// -----------------------CONTROLER_METHODS-----------------------------------------------------------------------------
	private static boolean validateMeters(List<? extends MeterAbstract> meters) {
		if (meters.isEmpty()) {
			return false;
		}
		for (MeterAbstract meter : meters) {
			if (meter.getApartment() == null) {
				return true;
			}
		}

		return false;
	}

}
