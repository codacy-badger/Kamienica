package kamienica.feature.user_admin;

import kamienica.core.util.CommonUtils;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.invoice.IInvoiceDao;
import kamienica.feature.reading.IReadingDao;
import kamienica.feature.settings.ISettingsDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class OwnerUserDataService implements IOwnerUserDataService {

    private final ISettingsDao settingsDao;
    private final IApartmentDao apartmentDao;
    private final IReadingDao readingDao;
    private final LocalDate now = new LocalDate();

    @Autowired
    public OwnerUserDataService(ISettingsDao settingsDao, IApartmentDao apartmentDao, IReadingDao readingDao) {
        this.settingsDao = settingsDao;
        this.apartmentDao = apartmentDao;
        this.readingDao = readingDao;
    }

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
            model.put("settings", "BRAK USTAWIEŃ.... Nie ma już algoruytmu podzialui");
    }

    private void addLatestReadings(HashMap<String, Object> model) {
//TODO implement method after merging the tables

//        final LocalDate energyDate = readingDao.getLatestDate(SecurityDetails.getResidencesForOwner());
//        final LocalDate gasDate = gasDao.getLatestDate();
//        final LocalDate waterDate = waterDao.getLatestDate();
//
//        int energy = countDays(energyDate);
//        int gas = countDays(gasDate);
//        int water = countDays(waterDate);
//        String media;
//        int days;
//        if (energy > gas && energy > water) {
//            days = energy;
//            media = "Energia";
//        } else if (water > gas) {
//            days = water;
//            media = "Woda";
//        } else {
//            days = gas;
//            media = "Gaz";
//        }
//        model.put("readingMedia", media);
//        model.put("readingDays", days);

//        energy = invoiceDao.getDaysOfLastInvoice();
//        gas = invoiceGasDao.getDaysOfLastInvoice();
//        water = invoiceWaterDao.getDaysOfLastInvoice();
//
//        if (energy > gas && energy > water) {
//            days = energy;
//            media = "Energia";
//        } else if (water > gas) {
//            days = water;
//            media = "Woda";
//        } else {
//            days = gas;
//            media = "Gaz";
//        }
//        model.put("invoiceMedia", media);
//        model.put("invoiceDays", days);

        model.put("readingMedia", "Błąd");
        model.put("readingDays", 0);
        model.put("invoiceMedia", "błąd");
        model.put("invoiceDays", 0);

    }

    private int countDays(LocalDate energyDate) {
        if (energyDate != null) {
            return CommonUtils.countDaysBetween(energyDate, now);
        }
        return 999;

    }

    @Override
    public List<Reading> getReadingEnergyForTenant(Apartment aparmtent) {
        return readingDao.getListForTenant(aparmtent);
    }

    @Override
    public List<Reading> getReadingGasForTenant(Apartment aparmtent) {
        return readingDao.getListForTenant(aparmtent);
    }

    @Override
    public List<Reading> getReadingWaterForTenant(Apartment aparmtent) {
        return readingDao.getListForTenant(aparmtent);
    }

    @Override
    public List<Reading> getReadingsForTenant(Apartment apartment, Media media) {

        switch (media) {
            case ENERGY:
                return readingDao.getListForTenant(apartment);

            case GAS:
                return readingDao.getListForTenant(apartment);

            case WATER:
                return readingDao.getListForTenant(apartment);

            default:
                throw new IllegalArgumentException();
        }

    }

    public SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Tenant getLoggedTenant() {
        SecurityUser su = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return su.getTenant();
    }
}
