package kamienica.feature.reading;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	// @InitBinder
	// protected void initBinder(HttpServletRequest request,
	// ServletRequestDataBinder binder) {
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// sdf.setLenient(true);
	// binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	// }

	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	// Date today = new Date();
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
		List<ReadingEnergy> readings = new ArrayList<ReadingEnergy>();
		HashMap<Long, ReadingEnergy> latestReadings = readingService.getLatestEnergyReadings();

		model.put("date", new LocalDate());

		for (int i = 0; i < meterEnergy.size(); i++) {
			Long id = meterEnergy.get(i).getId();
			ReadingEnergy tmp = new ReadingEnergy();
			tmp.setMeter(meterEnergy.get(i));
			if (latestReadings.get(id) != null) {
				tmp.setValue(latestReadings.get(id).getValue());
			} else {
				tmp.setValue(0);
			}
			tmp.setReadingDate(new LocalDate());
			readings.add(tmp);
		}

		readingForm.setCurrentReadings(readings);

		LocalDate oldDate = null;
		try {
			oldDate = latestReadings.get(meterEnergy.get(0).getId()).getReadingDate();
			model.put("oldDate", oldDate.plusDays(1));
		} catch (NullPointerException e) {
			model.put("oldDate", "2000-01-01");
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
		List<ReadingGas> readings = new ArrayList<>();
		HashMap<Long, ReadingGas> latestReadings = readingService.getLatestGasReadings();

		model.put("date", new LocalDate());

		for (int i = 0; i < meterGas.size(); i++) {
			Long id = meterGas.get(i).getId();
			ReadingGas tmp = new ReadingGas();
			tmp.setMeter(meterGas.get(i));
			if (latestReadings.get(id) != null) {
				tmp.setValue(latestReadings.get(id).getValue());
			} else {
				tmp.setValue(0);
			}
			tmp.setReadingDate(new LocalDate());
			readings.add(tmp);
		}

		readingForm.setCurrentReadings(readings);

		LocalDate oldDate = null;
		try {
			oldDate = latestReadings.get(meterGas.get(0).getId()).getReadingDate();
			model.put("oldDate", oldDate.plusDays(1));
		} catch (NullPointerException e) {
			model.put("oldDate", "2000-01-01");
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
		List<ReadingWater> readings = new ArrayList<>();
		HashMap<Long, ReadingWater> currentReadings = readingService.getLatestWaterReadings();

		model.put("date", new LocalDate());
		for (int i = 0; i < meterWater.size(); i++) {
			Long id = meterWater.get(i).getId();
			ReadingWater reading = new ReadingWater();
			reading.setMeter(meterWater.get(i));
			if (currentReadings.get(id) != null) {
				reading.setValue(currentReadings.get(id).getValue());
			} else {
				reading.setValue(0);
			}
			reading.setReadingDate(new LocalDate());
			readings.add(reading);

		}
		readingWaterForm.setCurrentReadings(readings);
		LocalDate oldDate = null;
		try {
			oldDate = currentReadings.get(meterWater.get(0).getId()).getReadingDate();
			model.put("oldDate", oldDate.plusDays(1));
		} catch (NullPointerException e) {
			model.put("oldDate", "2000-01-01");
		}

		return new ModelAndView("/Admin/Reading/ReadingWaterRegister", "model", model);
	}
	// --------------------------------SAVE-----------------------------------------------------------------

	@RequestMapping(value = "/Admin/Reading/readingEnergySave", method = RequestMethod.POST)
	public ModelAndView readingEnergySave(@ModelAttribute("readingForm") ReadingEnergyForm readingForm,
			BindingResult result, @RequestParam String date) {
		List<ReadingEnergy> reading = readingForm.getCurrentReadings();
		if (!ReadingValidator.validateMeterReadings(reading)) {
			return new ModelAndView("/Admin/Reading/ReadingEnergyRegister");
		}
		// String date = req.getParameter("date");
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < reading.size(); i++) {
			reading.get(i).setReadingDate(LocalDate.parse(date));
			reading.get(i).setUnit(reading.get(i).getMeter().getUnit());
		}

		readingService.saveEnergyList(reading);
		return new ModelAndView("redirect:/Admin/Reading/readingEnergyList.html");
	}

	@RequestMapping(value = "/Admin/Reading/readingGasSave", method = RequestMethod.POST)
	public ModelAndView readingGasSave(@ModelAttribute("readingForm") ReadingGasForm readingForm, BindingResult result,
			@RequestParam String date) {
		// String date = req.getParameter("date");
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ReadingGas> reading = readingForm.getCurrentReadings();
		for (int i = 0; i < reading.size(); i++) {
			reading.get(i).setReadingDate(LocalDate.parse(date));
			reading.get(i).setUnit(reading.get(i).getMeter().getUnit());
		}

		readingService.saveGasList(reading);
		return new ModelAndView("redirect:/Admin/Reading/readingGasList.html");
	}

	@RequestMapping(value = "/Admin/Reading/readingWaterSave", method = RequestMethod.POST)
	public ModelAndView readingWaterSave(@ModelAttribute("readingForm") ReadingWaterForm readingWaterForm,
			BindingResult result, @RequestParam String date) throws ParseException {
		// String date = req.getParameter("date");
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ReadingWater> reading = readingWaterForm.getCurrentReadings();
		for (int i = 0; i < reading.size(); i++) {
			reading.get(i).setReadingDate(LocalDate.parse(date));
			reading.get(i).setUnit(reading.get(i).getMeter().getUnit());
		}
		readingService.saveWaterList(reading);
		return new ModelAndView("redirect:/Admin/Reading/readingWaterList.html");
	}
	// -----------------------------LIST-------------------------------------------------------

	@RequestMapping("/Admin/Reading/readingEnergyList")
	public ModelAndView readingEnergyList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("reading", readingService.getReadingEnergy());
		return new ModelAndView("/Admin/Reading/ReadingEnergyList", model);

	}

	@RequestMapping("/Admin/Reading/readingGasList")
	public ModelAndView odczytGazLista() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("reading", readingService.getReadingGas());
		return new ModelAndView("/Admin/Reading/ReadingGasList", model);

	}

	@RequestMapping("/Admin/Reading/readingWaterList")
	public ModelAndView odczytWodaLista() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("reading", readingService.getReadingWater());
		return new ModelAndView("/Admin/Reading/ReadingWaterList", model);

	}
	// ------------------------DELETE------------------------------

	@RequestMapping(value = "/Admin/Reading/readingEnergyDelete", params = { "date" })
	public ModelAndView readingEnergyDelete(@RequestParam(value = "date") String date) {
		List<ReadingEnergy> listToDelete = readingService.getReadingEnergyByDate(date);
		if (listToDelete.get(0).isResolved() == true) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("error", "Nie można usuwać odczytu, dla którego wprowadzono Fakturę.");
			return new ModelAndView("/Admin/Reading/ReadingEnergyList", "model", model);
		}
		readingService.deleteReadingEnergyList(listToDelete);

		return new ModelAndView("redirect:/Admin/Reading/readingEnergyList.html");
	}

	@RequestMapping(value = "/Admin/Reading/readingGasDelete", params = { "date" })
	public ModelAndView readingGasDelete(@RequestParam(value = "date") String date) {
		List<ReadingGas> listToDelete = readingService.getReadingGasByDate(date);
		if (listToDelete.get(0).isResolved() == true) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("error", "Nie można usuwać odczytu, dla którego wprowadzono Fakturę.");
			return new ModelAndView("/Admin/Reading/ReadingGasList", "model", model);
		}
		readingService.deleteReadingGasList(listToDelete);
		return new ModelAndView("redirect:/Admin/Reading/readingGasList.html");
	}

	@RequestMapping(value = "/Admin/Reading/readingWaterDelete", params = { "date" })
	public ModelAndView usunReadingWater(@RequestParam(value = "date") String date) {
		List<ReadingWater> listToDelete = readingService.getReadingWaterByDate(date);
		if (listToDelete.get(0).isResolved() == true) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("error", "Nie można usuwać odczytu, dla którego wprowadzono Fakturę.");
			return new ModelAndView("/Admin/Reading/ReadingWaterList", "model", model);
		}
		readingService.deleteReadingWaterList(listToDelete);
		return new ModelAndView("redirect:/Admin/Reading/readingWaterList.html");
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
			System.out.println("--------------------------reading controler-> edit oldDate " + oldDate);
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
		return new ModelAndView("redirect:/Admin/Reading/readingEnergyList.html");
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
		return new ModelAndView("redirect:/Admin/Reading/readingGasList.html");
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
		return new ModelAndView("redirect:/Admin/Reading/readingWaterList.html");
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

	// private static String getIncrementedDate(Date date) {
	// long tt = 24 * 60 * 60 * 1000;
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	// return df.format(new Date(date.getTime() + tt));
	// }
}
