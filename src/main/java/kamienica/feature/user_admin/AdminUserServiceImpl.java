package kamienica.feature.user_admin;

import kamienica.core.enums.Media;
import kamienica.core.util.CommonUtils;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.invoice.InvoiceAbstractDao;
import kamienica.feature.reading.*;
import kamienica.feature.settings.SettingsDao;
import kamienica.model.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private SettingsDao settingsDao;
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

    final LocalDate now = new LocalDate();

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

    private void addLatestReadings(HashMap<String, Object> model) {


        final LocalDate energyDate = energyDao.getLatestDate();
        final LocalDate gasDate = gasDao.getLatestDate();
        final LocalDate waterDate = waterDao.getLatestDate();

        int energy = countDays(energyDate);
        int gas = countDays(gasDate);
        int water = countDays(waterDate);
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
        model.put("readingDays", days);

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

    private int countDays(LocalDate energyDate) {
        if (energyDate != null) {
            return CommonUtils.countDaysBetween(energyDate, now);
        }
        return 999;

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
    public List<? extends Reading> getReadingsForTenant(Apartment apartment, Media media) {

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

    public SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
