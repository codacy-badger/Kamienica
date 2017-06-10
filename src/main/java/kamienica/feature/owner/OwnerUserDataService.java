package kamienica.feature.owner;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.reading.IReadingDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Reading;
import kamienica.model.entity.SecurityUser;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class OwnerUserDataService implements IOwnerUserDataService {

    private final IApartmentDao apartmentDao;
    private final IReadingDao readingDao;

    @Autowired
    public OwnerUserDataService(IApartmentDao apartmentDao, IReadingDao readingDao) {
        this.apartmentDao = apartmentDao;
        this.readingDao = readingDao;
    }

    @Override
    public HashMap<String, Object> getMainData() {
        HashMap<String, Object> model = new HashMap<>();
        model.put("emptyApartments", "Brak danych");
        addLatestReadings(model);
        checkConfig(model);
        return model;
    }

    private void checkConfig(HashMap<String, Object> model) {
        model.put("resicences", SecurityDetails.getResidencesForOwner().size());
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

//    private int countDays(LocalDate energyDate) {
//        if (energyDate != null) {
//            return CommonUtils.countDaysBetween(energyDate, now);
//        }
//        return 999;
//
//    }


    @Override
    public List<Reading> getReadingsForTenant(Apartment apartment, Media media) {
        return readingDao.getListForTenant(apartment, media);
    }

    public SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Tenant getLoggedTenant() {
        SecurityUser su = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return su.getTenant();
    }
}
