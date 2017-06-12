package kamienica.feature.owner;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.reading.IReadingDao;
import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.model.entity.*;
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
    private final IReadingDetailsDao readingDetailsDao;

    @Autowired
    public OwnerUserDataService(final IApartmentDao apartmentDao, final IReadingDetailsDao readingDetailsDao) {
        this.apartmentDao = apartmentDao;
        this.readingDetailsDao = readingDetailsDao;
    }

    @Override
    public HashMap<String, Object> getMainData() {
        final OwnerData data = new OwnerData();
        HashMap<String, Object> model = new HashMap<>();
        model.put("emptyApartments", countEmptyApartments());
        addLatestReadings(model);
        checkConfig(model);
        return model;
    }

    private int countEmptyApartments() {
    List<Residence> residences = SecurityDetails.getResidencesForOwner();
    apartmentDao.getBySQLQuery("from Apartment where resicence_id in (:res)");

        return 0;
    }

    private void checkConfig(HashMap<String, Object> model) {
        model.put("resicences", SecurityDetails.getResidencesForOwner().size());
    }

    private void addLatestReadings(HashMap<String, Object> model) {
//TODO implement method after merging the tables

//        final LocalDate energyDate = readingDetailsDao.getLatestDate(SecurityDetails.getResidencesForOwner());
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
        return null;
    }

    public SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Tenant getLoggedTenant() {
        SecurityUser su = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return su.getTenant();
    }
}
