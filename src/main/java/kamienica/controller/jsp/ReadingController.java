package kamienica.controller.jsp;

import kamienica.feature.meter.IMeterService;
import kamienica.feature.reading.IReadingService;
import kamienica.feature.reading.ReadingForm;
import kamienica.feature.reading.ReadingValidator;
import kamienica.feature.residence.IResidenceService;
import kamienica.model.entity.Meter;
import kamienica.model.entity.Reading;
import kamienica.model.entity.ReadingDetails;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.exception.NoMainCounterException;
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

    private final IMeterService meterService;
    private final IReadingService readingService;
    private final IResidenceService residenceService;
    private final String NO_MAIN_COUNTER = "Brakuje licznika głównego. Wprowadź brakujące liczniki";

    @Autowired
    public ReadingController(IMeterService meterService, IReadingService readingService,
                             IResidenceService residenceService) {
        this.meterService = meterService;
        this.readingService = readingService;
        this.residenceService = residenceService;
    }

    // -----------------------------------REGISTER---------------------------------------------------------------

    @RequestMapping("/readingEnergyRegister")
    public ModelAndView readingEnergyRegister(@ModelAttribute("readingForm") ReadingForm readingForm,
                                              BindingResult result, @RequestParam("residence_id") Long id) {

        HashMap<String, Object> model = new HashMap<>();
        try {
            final Residence r = residenceService.getById(id);
            List<Reading> readings = readingService.getLatestNew(r, Media.ENERGY);
            readingForm.setCurrentReadings(readings);
            readingForm.setNewReadings(readings);
            return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
        } catch (NoMainCounterException e) {
            model.put("error", e.getMessage());
            return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
        }

    }

    @RequestMapping("/readingGasRegister")
    public ModelAndView readingGasRegister(@ModelAttribute("readingForm") ReadingForm readingForm,
                                           BindingResult result, @RequestParam("residence_id") Long id) {

        HashMap<String, Object> model = new HashMap<>();

        try {
            final Residence r = residenceService.getById(id);
            List<Reading> readings = readingService.getLatestNew(r, Media.GAS);
            model.put("date", new LocalDate());

            readingForm.setCurrentReadings(readings);

            if (readings.isEmpty()) {
                model.put("oldDate", "2000-01-01");
            } else {
                model.put("oldDate", readings.get(0).getReadingDetails().getReadingDate().plusDays(1));
            }

            return new ModelAndView("/Admin/Reading/ReadingGasRegister", "model", model);
        } catch (NoMainCounterException e) {
            model.put("error", e.getMessage());
            return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
        }

    }

    @RequestMapping("/readingWaterRegister")
    public ModelAndView readingWaterRegister(@ModelAttribute("readingForm") ReadingForm ReadingForm,
                                             BindingResult result, @RequestParam("residence_id") Long id) {

        HashMap<String, Object> model = new HashMap<>();
        try {
            final Residence r = residenceService.getById(id);
            List<Reading> readings = readingService.getLatestNew(r, Media.WATER);
            ReadingForm.setCurrentReadings(readings);

            model.put("date", new LocalDate());


            if (readings.isEmpty()) {
                model.put("oldDate", "2000-01-01");
            } else {
                model.put("oldDate", readings.get(0).getReadingDetails().getReadingDate().plusDays(1));
            }
            return new ModelAndView("/Admin/Reading/ReadingWaterRegister", "model", model);
        } catch (NoMainCounterException e) {
            model.put("error", e.getMessage());
            return new ModelAndView("/Admin/Reading/ReadingEnergyRegister", "model", model);
        }

    }
    // --------------------------------SAVE-----------------------------------------------------------------

    @RequestMapping(value = "/readingEnergySave", method = RequestMethod.POST)
    public ModelAndView readingEnergySave(@ModelAttribute("readingForm") ReadingForm readingForm,
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
        final ReadingDetails details = getReadingDetails(readingForm, date);
        readingService.save(readingForm.getNewReadings(), details);
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=ENERGY");
    }


    @RequestMapping(value = "/readingGasSave", method = RequestMethod.POST)
    public ModelAndView readingGasSave(@ModelAttribute("readingForm") ReadingForm readingForm, BindingResult result,
                                       @RequestParam String date) {

        if (!meterService.ifMainExists(Media.GAS)) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("error", NO_MAIN_COUNTER);
            return new ModelAndView("/Admin/Reading/ReadingGasRegister", "model", model);
        }
        final ReadingDetails details = getReadingDetails(readingForm, date);
        readingService.save(readingForm.getCurrentReadings(), details);
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=GAS");
    }

    @RequestMapping(value = "/readingWaterSave", method = RequestMethod.POST)
    public ModelAndView readingWaterSave(@ModelAttribute("readingForm") ReadingForm readingForm,
                                         BindingResult result, @RequestParam String date) {
        if (!meterService.ifMainExists(Media.WATER)) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("error", NO_MAIN_COUNTER);
            return new ModelAndView("/Admin/Reading/ReadingGasRegister", "model", model);
        }
        final ReadingDetails details = getReadingDetails(readingForm, date);
        readingService.save(readingForm.getCurrentReadings(), details);
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=WATER");
    }

    @RequestMapping("/readingList")
    public ModelAndView readingList(@RequestParam("media") final Media media, @RequestParam("residence_id") final Long id) {
        final Residence residence = residenceService.getById(id);
        Map<String, Object> model = new HashMap<>();
        List<Reading> readings = readingService.getList(residence, media);
        model.put("reading", readings);
        model.put("media", media);
        if (!readings.isEmpty()) {
            model.put("editUrl", "/Admin/Reading/" + media + "Edit.html?date=");
            model.put("delUrl", "/Admin/Reading/"+media+"Delete.html");
            model.put("date", readings.get(0).getReadingDetails().getReadingDate());
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
    public ModelAndView readingEnergyEdit(@ModelAttribute("readingForm") ReadingForm readingForm, @RequestParam("residence_id") final Long id) {
        return createMAVForEdit(readingForm, id, Media.ENERGY);
    }

    @RequestMapping(value = "/readingGasEdit")
    public ModelAndView readingGasEdit(@ModelAttribute("readingForm") ReadingForm readingForm, @RequestParam("residence_id") final Long id) {
        return createMAVForEdit(readingForm, id, Media.GAS);
    }

    @RequestMapping(value = "/readingWaterEdit")
    public ModelAndView readingWaterEdit(@ModelAttribute("readingForm") ReadingForm readingForm, @RequestParam("residence_id") final Long id) {
        return createMAVForEdit(readingForm, id, Media.WATER);
    }

    // -------------------------OVERWRITE--------------------------------------------
    @RequestMapping("/readingEnergyOverwrite")
    public ModelAndView readingEnergyOverwite(@ModelAttribute("readingForm") ReadingForm readingForm,
                                              BindingResult result, @RequestParam LocalDate date) {

        readingService.update(readingForm.getCurrentReadings(), date);
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=ENERGY");
    }

    @RequestMapping("/readingGasOverwrite")
    public ModelAndView readingGasOverwrite(@ModelAttribute("readingForm") ReadingForm readingForm,
                                            BindingResult result, @RequestParam String date) {

        readingService.update(readingForm.getCurrentReadings(), LocalDate.parse(date));
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=GAS");
    }

    @RequestMapping("/readingWaterOverwrite")
    public ModelAndView readingWaterOverwrite(@ModelAttribute("readingForm") ReadingForm readingForm,
                                              BindingResult result, @RequestParam String date) {

        readingService.update(readingForm.getCurrentReadings(), LocalDate.parse(date));
        return new ModelAndView("redirect:/Admin/Reading/readingList.html?media=WATER");
    }

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

    private ReadingDetails getReadingDetails(final ReadingForm readingForm, final String date) {
        final Residence residence = readingForm.getCurrentReadings().get(0).getResidence();
        return new ReadingDetails(LocalDate.parse(date), Media.ENERGY, residence);
    }

    private ModelAndView createMAVForEdit(final ReadingForm readingForm, final Long id, final Media media) {
        final Residence r = residenceService.getById(id);
        final List<Meter> meters = meterService.list(r, media);
        readingForm.setCurrentReadings(readingService.latestEdit(r, media));
        readingForm.setPreviousReadings(readingService.getPreviousReading(readingForm.getDate(), meters));

        Map<String, Object> model = new HashMap<>();
        model.put("date", readingForm.getCurrentReadings().get(0).getReadingDetails().getReadingDate());
        model.put("oldDate", readingForm.getPreviousDate());
        model.put("readingForm", readingForm);

        return new ModelAndView("/Admin/Reading/" + media + "Edit", "model", model);
    }
}
