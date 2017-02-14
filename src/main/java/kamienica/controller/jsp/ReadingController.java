package kamienica.controller.jsp;

import kamienica.core.enums.Media;
import kamienica.core.exception.NoMainCounterException;
import kamienica.feature.meter.MeterService;
import kamienica.feature.reading.*;
import kamienica.feature.residence.ResidenceService;
import kamienica.model.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Admin/Reading")
public class ReadingController {

    private final MeterService meterService;
    private final ReadingService readingService;
    private final ResidenceService residenceService;
    private final String NO_MAIN_COUNTER = "Brakuje licznika głównego. Wprowadź brakujące liczniki";

    @Autowired
    public ReadingController(MeterService meterService, ReadingService readingService, ResidenceService residenceService) {
        this.meterService = meterService;
        this.readingService = readingService;
        this.residenceService = residenceService;
    }

    // -----------------------------------REGISTER---------------------------------------------------------------

    @RequestMapping("/readingEnergyRegister")
    public ModelAndView readingEnergyRegister(@ModelAttribute("readingForm") ReadingEnergyForm readingForm,
                                              BindingResult result, @RequestParam("residence_id") Long id) {

        HashMap<String, Object> model = new HashMap<>();
        try {
            final Residence r = residenceService.getById(id);
            List<ReadingEnergy> readings = readingService.getLatestNew(r, Media.ENERGY);
            readingForm.setCurrentReadings(readings);
            readingForm.setNewReadings(readings);
            readingService.setDates(model, readings);
            return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
        } catch (NoMainCounterException e) {
            model.put("error", e.getMessage());
            return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
        }

    }

    @RequestMapping("/readingGasRegister")
    public ModelAndView readingGasRegister(@ModelAttribute("readingForm") ReadingGasForm readingForm,
                                           BindingResult result, @RequestParam("residence_id") Long id) {

        HashMap<String, Object> model = new HashMap<>();

        try {
            final Residence r = residenceService.getById(id);
            List<ReadingGas> readings = readingService.getLatestNew(r, Media.GAS);
            model.put("date", new LocalDate());

            readingForm.setCurrentReadings(readings);

            if (readings.isEmpty()) {
                model.put("oldDate", "2000-01-01");
            } else {
                model.put("oldDate", readings.get(0).getReadingDate().plusDays(1));
            }

            return new ModelAndView("/Admin/Reading/ReadingGasRegister", "model", model);
        } catch (NoMainCounterException e) {
            model.put("error", e.getMessage());
            model.put("url", "/Admin/Reading/readingEnergySave.html");
            return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
        }

    }

    @RequestMapping("/readingWaterRegister")
    public ModelAndView readingWaterRegister(@ModelAttribute("readingForm") ReadingWaterForm readingWaterForm,
                                             BindingResult result, @RequestParam("residence_id") Long id) {

        HashMap<String, Object> model = new HashMap<>();
        try {
            final Residence r = residenceService.getById(id);
            List<ReadingWater> readings = readingService.getLatestNew(r, Media.WATER);
            readingWaterForm.setCurrentReadings(readings);

            model.put("date", new LocalDate());


            if (readings.isEmpty()) {
                model.put("oldDate", "2000-01-01");
            } else {
                model.put("oldDate", readings.get(0).getReadingDate().plusDays(1));
            }
            return new ModelAndView("/Admin/Reading/ReadingWaterRegister", "model", model);
        } catch (NoMainCounterException e) {
            model.put("error", e.getMessage());
            return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
        }

    }
    // --------------------------------SAVE-----------------------------------------------------------------

    @RequestMapping(value = "/readingEnergySave", method = RequestMethod.POST)
    public ModelAndView readingEnergySave(@ModelAttribute("readingForm") ReadingEnergyForm readingForm,
                                          BindingResult result, @RequestParam String date) {

        if (!meterService.ifMainExists(Media.ENERGY)) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("error", NO_MAIN_COUNTER);
            return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
        }

        if (ReadingValidator.validateMeterReadings(readingForm.getCurrentReadings(), readingForm.getNewReadings())) {
            return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "error",
                    "Nowa wartość nie może być mniejsza od poprzedniej");
        }
        readingService.save(readingForm.getNewReadings(), LocalDate.parse(date), Media.ENERGY);
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=ENERGY");
    }

    @RequestMapping(value = "/readingGasSave", method = RequestMethod.POST)
    public ModelAndView readingGasSave(@ModelAttribute("readingForm") ReadingGasForm readingForm, BindingResult result,
                                       @RequestParam String date) {

        if (!meterService.ifMainExists(Media.GAS)) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("error", NO_MAIN_COUNTER);
            return new ModelAndView("/Admin/Reading/ReadingGasRegister", "model", model);
        }
        readingService.save(readingForm.getCurrentReadings(), LocalDate.parse(date), Media.GAS);
        // readingService.saveGasList(readingForm.getCurrentReadings(),
        // LocalDate.parse(date));
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=GAS");
    }

    @RequestMapping(value = "/readingWaterSave", method = RequestMethod.POST)
    public ModelAndView readingWaterSave(@ModelAttribute("readingForm") ReadingWaterForm readingWaterForm,
                                         BindingResult result, @RequestParam String date) {
        if (!meterService.ifMainExists(Media.WATER)) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("error", NO_MAIN_COUNTER);
            return new ModelAndView("/Admin/Reading/ReadingGasRegister", "model", model);
        }
        readingService.save(readingWaterForm.getCurrentReadings(), LocalDate.parse(date), Media.WATER);
        // readingService.saveWaterList(readingWaterForm.getCurrentReadings(),
        // LocalDate.parse(date));
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=WATER");
    }
    // -----------------------------LIST-------------------------------------------------------

    @RequestMapping("/readingList")
    public ModelAndView readingList(@RequestParam("media") Media media) {
        Map<String, Object> model = new HashMap<>();
        switch (media) {
            case ENERGY:
                List<? extends Reading> energy = readingService.getList(media);
                model.put("reading", energy);
                model.put("media", "Energia");
                if (!energy.isEmpty()) {
                    model.put("editUrl", "/Admin/Reading/readingEnergyEdit.html?date=");
                    model.put("delUrl", "/Admin/Reading/readingEnergyDelete.html");
                    model.put("date", energy.get(0).getReadingDate());
                }
                break;
            case WATER:

                List<? extends Reading> water = readingService.getList(media);
                model.put("reading", water);
                model.put("media", "Woda");
                if (!water.isEmpty()) {
                    model.put("editUrl", "/Admin/Reading/readingWaterEdit.html?date=");
                    model.put("delUrl", "/Admin/Reading/readingWaterDelete.html");
                    model.put("date", water.get(0).getReadingDate());
                }
                break;
            case GAS:
                List<? extends Reading> gas = readingService.getList(media);
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

    @RequestMapping(value = "/readingEnergyDelete")
    public ModelAndView readingEnergyDelete(@RequestParam("residence_id") final Long id) {
        final Residence r = residenceService.getById(id);
        readingService.deleteLatestReadings(r, Media.ENERGY);
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=ENERGY");
    }

    @RequestMapping(value = "/readingGasDelete")
    public ModelAndView readingGasDelete(@RequestParam("residence_id") final Long id) {
        final Residence r = residenceService.getById(id);
        readingService.deleteLatestReadings(r, Media.GAS);
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=GAS");
    }

    @RequestMapping(value = "/readingWaterDelete")
    public ModelAndView usunReadingWater(@RequestParam("residence_id") final Long id) {
        final Residence r = residenceService.getById(id);
        readingService.deleteLatestReadings(r, Media.WATER);
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=WATER");
    }

    // ------------------------------EDIT-----------------------------------
    @RequestMapping(value = "/readingEnergyEdit")
    public ModelAndView readingEnergyEdit(@ModelAttribute("readingForm") ReadingEnergyForm readingForm, @RequestParam("residence_id") final Long id) {
        final Residence r = residenceService.getById(id);
        readingForm.setCurrentReadings(readingService.energyLatestEdit(r));

        // readingForm.setPreviousReadings(readingService.getPreviousReadingEnergy(readingForm.getDate(),
        // Media.ENERGY));
        readingForm.setPreviousReadings(
                readingService.getPreviousReadingEnergy(readingForm.getDate(), meterService.getIdList(Media.ENERGY)));

        Map<String, Object> model = new HashMap<>();
        model.put("date", readingForm.getCurrentReadings().get(0).getReadingDate());
        model.put("oldDate", readingForm.getPreviousDate());
        model.put("readingForm", readingForm);
        // model.put("previousReadings", readings2);
        // model.put("currentReadigns", readings);

        return new ModelAndView("/Admin/Reading/ReadingEnergyEdit", "model", model);
    }

    @RequestMapping(value = "/readingGasEdit")
    public ModelAndView readingGasEdit(@ModelAttribute("readingForm") ReadingGasForm readingForm, @RequestParam("residence_id") final Long id) {
        final Residence r = residenceService.getById(id);
        readingForm.setCurrentReadings(readingService.gasLatestEdit(r));
        readingForm.setPreviousReadings(
                readingService.getPreviousReadingGas(readingForm.getDate(), meterService.getIdList(Media.GAS)));

        Map<String, Object> model = new HashMap<>();
        model.put("date", readingForm.getCurrentReadings().get(0).getReadingDate());
        model.put("oldDate", readingForm.getPreviousDate());
        model.put("readingForm", readingForm);
        // model.put("previousReadings", readings2);
        // model.put("currentReadigns", readings);

        return new ModelAndView("/Admin/Reading/ReadingGasEdit", "model", model);
    }

    @RequestMapping(value = "/readingWaterEdit")
    public ModelAndView readingWaterEdit(@ModelAttribute("readingForm") ReadingWaterForm readingForm, @RequestParam("residence_id") final Long id) {
        final Residence r = residenceService.getById(id);
        readingForm.setCurrentReadings(readingService.waterLatestEdit(r));
        readingForm.setPreviousReadings(
                readingService.getPreviousReadingWater(readingForm.getDate(), meterService.getIdList(Media.WATER)));

        Map<String, Object> model = new HashMap<>();
        model.put("date", readingForm.getCurrentReadings().get(0).getReadingDate());
        model.put("oldDate", readingForm.getPreviousDate());
        model.put("readingForm", readingForm);
        // model.put("previousReadings", readings2);
        // model.put("currentReadigns", readings);

        return new ModelAndView("/Admin/Reading/ReadingWaterEdit", "model", model);
    }

    // -------------------------OVERWRITE--------------------------------------------
    @RequestMapping("/readingEnergyOverwrite")
    public ModelAndView readingEnergyOverwite(@ModelAttribute("readingForm") ReadingEnergyForm readingForm,
                                              BindingResult result, @RequestParam LocalDate date) {

        readingService.update(readingForm.getCurrentReadings(), date, Media.ENERGY);
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=ENERGY");
    }

    @RequestMapping("/readingGasOverwrite")
    public ModelAndView readingGasOverwrite(@ModelAttribute("readingForm") ReadingGasForm readingForm,
                                            BindingResult result, @RequestParam String date) {

        readingService.update(readingForm.getCurrentReadings(), LocalDate.parse(date), Media.GAS);
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=GAS");
    }

    @RequestMapping("/readingWaterOverwrite")
    public ModelAndView readingWaterOverwrite(@ModelAttribute("readingForm") ReadingWaterForm readingForm,
                                              BindingResult result, @RequestParam String date) {

        readingService.update(readingForm.getCurrentReadings(), LocalDate.parse(date), Media.WATER);
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=WATER");
    }

    // -------------------------REST--------------------------------------------
    @RequestMapping("/energyRest")
    public ModelAndView energyRest() {

        return new ModelAndView("/Admin/Reading/energyRest");
    }

    @RequestMapping("/gasRest")
    public ModelAndView gasRest() {

        return new ModelAndView("/Admin/Reading/gasRest");
    }

    @RequestMapping("/waterRest")
    public ModelAndView waterRest() {

        return new ModelAndView("/Admin/Reading/waterRest");
    }

}
