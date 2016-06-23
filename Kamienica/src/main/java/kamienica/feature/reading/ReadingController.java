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
import kamienica.feature.meter.MeterService;

@Controller
public class ReadingController {

	@Autowired
	private MeterService meterService;
	@Autowired
	private ReadingService readingService;
	private final String NO_MAIN_COUNTER = "Brakuje licznika głównego. Wprowadź brakujące liczniki";

	// -----------------------------------REGISTER---------------------------------------------------------------

	@RequestMapping("/Admin/Reading/readingEnergyRegister")
	public ModelAndView readingEnergyRegister(@ModelAttribute("readingForm") ReadingEnergyForm readingForm,
			BindingResult result) {
System.out.println("-------------------------------");
System.out.println(meterService.ifMainExists(Media.ENERGY));
		HashMap<String, Object> model = new HashMap<>();
		if (!meterService.ifMainExists(Media.ENERGY)) {
			model.put("error", NO_MAIN_COUNTER);
			return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
		}

		List<ReadingEnergy> readings = readingService.energyLatestNew(meterService.getIdList(Media.ENERGY));

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

		HashMap<String, Object> model = new HashMap<>();
		if (!meterService.ifMainExists(Media.GAS)) {
			model.put("error", NO_MAIN_COUNTER);
			model.put("url", "/Admin/Reading/readingEnergySave.html");
			return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
		}
		List<ReadingGas> readings = readingService.gasLatest(meterService.getIdList(Media.GAS));
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

		HashMap<String, Object> model = new HashMap<>();
		if (!meterService.ifMainExists(Media.WATER)) {
			model.put("error", NO_MAIN_COUNTER);
			return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
		}
		List<ReadingWater> readings = readingService.waterLatest(meterService.getIdList(Media.WATER));

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

		HashMap<String, Object> model = new HashMap<>();
		if (!meterService.ifMainExists(Media.WATER)) {
			model.put("error", NO_MAIN_COUNTER);
			return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
		}
		
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
		switch (media) {
		case ENERGY:
			List<ReadingEnergy> energy = readingService.getReadingEnergy();
			model.put("reading", energy);
			model.put("media", "Energia");
			if (!energy.isEmpty()) {
				model.put("editUrl", "/Admin/Reading/readingEnergyEdit.html?date=");
				model.put("delUrl", "/Admin/Reading/readingEnergyDelete.html");
				model.put("date", energy.get(0).getReadingDate());
			}
			break;
		case WATER:

			List<ReadingWater> water = readingService.getReadingWater();
			model.put("reading", water);
			model.put("media", "Woda");
			if (!water.isEmpty()) {
				model.put("editUrl", "/Admin/Reading/readingWaterEdit.html?date=");
				model.put("delUrl", "/Admin/Reading/readingWaterDelete.html");
				model.put("date", water.get(0).getReadingDate());
			}
			break;
		case GAS:
			List<ReadingGas> gas = readingService.getReadingGas();
			model.put("reading", gas);
			model.put("media", "Gaz");
			if (!gas.isEmpty()) {
				model.put("editUrl", "/Admin/Reading/readingGasEdit.html?date=");
				model.put("delUrl", "/Admin/Reading/readingGasDelete.html");
				model.put("date", gas.get(0).getReadingDate());
			}
			break;
		default:
			break;
		}

		return new ModelAndView("/Admin/Reading/ReadingList", "model", model);

	}

	// ------------------------DELETE------------------------------

	@RequestMapping(value = "/Admin/Reading/readingEnergyDelete")
	public ModelAndView readingEnergyDelete() {
		// List<ReadingEnergy> listToDelete =
		// readingService.getReadingEnergyByDate(date);
		// if (listToDelete.get(0).isResolved() == true) {
		// Map<String, Object> model = new HashMap<String, Object>();
		// model.put("error", "Nie można usuwać odczytu, dla którego wprowadzono
		// Fakturę.");
		// return new ModelAndView("/Admin/Reading/ReadingList?media=ENERGY",
		// "model", model);
		// }

		readingService.deleteLatestReadings(Media.ENERGY);
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=ENERGY");
	}

	@RequestMapping(value = "/Admin/Reading/readingGasDelete")
	public ModelAndView readingGasDelete() {
		// List<ReadingGas> listToDelete =
		// readingService.getReadingGasByDate(date);
		// if (listToDelete.get(0).isResolved() == true) {
		// Map<String, Object> model = new HashMap<String, Object>();
		// model.put("error", "Nie można usuwać odczytu, dla którego wprowadzono
		// Fakturę.");
		// return new ModelAndView("/Admin/Reading/ReadingList?media=GAS",
		// "model", model);
		// }

		readingService.deleteLatestReadings(Media.GAS);
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=GAS");
	}

	@RequestMapping(value = "/Admin/Reading/readingWaterDelete")
	public ModelAndView usunReadingWater() {
		// List<ReadingWater> listToDelete =
		// readingService.getReadingWaterByDate(date);
		// if (listToDelete.get(0).isResolved() == true) {
		// Map<String, Object> model = new HashMap<String, Object>();
		// model.put("error", "Nie można usuwać odczytu, dla którego wprowadzono
		// Fakturę.");
		// return new ModelAndView("/Admin/Reading/ReadingList?media=WATER",
		// "model", model);
		// }
		readingService.deleteLatestReadings(Media.WATER);
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=WATER");
	}

	// ------------------------------EDIT-----------------------------------
	@RequestMapping(value = "/Admin/Reading/readingEnergyEdit")
	public ModelAndView readingEnergyEdit(@ModelAttribute("readingForm") ReadingEnergyForm readingForm) {

		readingForm.setCurrentReadings(readingService.energyLatestEdit(meterService.getIdList(Media.ENERGY)));
		readingForm.setPreviousReadings(readingService.getPreviousReadingEnergy(readingForm.getDate().toString(),
				meterService.getIdList(Media.ENERGY)));

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("date", readingForm.getCurrentReadings().get(0).getReadingDate());
		model.put("oldDate", readingForm.getPreviousDate());
		model.put("readingForm", readingForm);
		// model.put("previousReadings", readings2);
		// model.put("currentReadigns", readings);

		return new ModelAndView("/Admin/Reading/ReadingEnergyEdit", "model", model);
	}

	@RequestMapping(value = "/Admin/Reading/readingGasEdit")
	public ModelAndView readingGasEdit(@ModelAttribute("readingForm") ReadingGasForm readingForm) {

		readingForm.setCurrentReadings(readingService.gasLatestEdit(meterService.getIdList(Media.GAS)));
		readingForm.setPreviousReadings(readingService.getPreviousReadingGas(readingForm.getDate().toString(),
				meterService.getIdList(Media.GAS)));

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("date", readingForm.getCurrentReadings().get(0).getReadingDate());
		model.put("oldDate", readingForm.getPreviousDate());
		model.put("readingForm", readingForm);
		// model.put("previousReadings", readings2);
		// model.put("currentReadigns", readings);

		return new ModelAndView("/Admin/Reading/ReadingGasEdit", "model", model);
	}

	@RequestMapping(value = "/Admin/Reading/readingWaterEdit")
	public ModelAndView readingWaterEdit(@ModelAttribute("readingForm") ReadingWaterForm readingForm) {

		readingForm.setCurrentReadings(readingService.waterLatestEdit(meterService.getIdList(Media.WATER)));
		readingForm.setPreviousReadings(readingService.getPreviousReadingWater(readingForm.getDate().toString(),
				meterService.getIdList(Media.WATER)));

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("date", readingForm.getCurrentReadings().get(0).getReadingDate());
		model.put("oldDate", readingForm.getPreviousDate());
		model.put("readingForm", readingForm);
		// model.put("previousReadings", readings2);
		// model.put("currentReadigns", readings);

		return new ModelAndView("/Admin/Reading/ReadingWaterEdit", "model", model);
	}

	// -------------------------OVERWRITE--------------------------------------------
	@RequestMapping("/Admin/Reading/readingEnergyOverwrite")
	public ModelAndView readingEnergyOverwite(@ModelAttribute("readingForm") ReadingEnergyForm readingForm,
			BindingResult result, @RequestParam String date) throws ParseException {

		readingService.updateEnergyList(readingForm.getCurrentReadings(), date);
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=ENERGY");
	}

	@RequestMapping("/Admin/Reading/readingGasOverwrite")
	public ModelAndView readingGasOverwrite(@ModelAttribute("readingForm") ReadingGasForm readingForm,
			BindingResult result, @RequestParam String date) throws ParseException {

		readingService.updateGasList(readingForm.getCurrentReadings(), date);
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=GAS");
	}

	@RequestMapping("/Admin/Reading/readingWaterOverwrite")
	public ModelAndView readingWaterOverwrite(@ModelAttribute("readingForm") ReadingWaterForm readingForm,
			BindingResult result, @RequestParam String date) throws ParseException {

		readingService.updateWaterList(readingForm.getCurrentReadings(), date);
		return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=WATER");
	}

}
