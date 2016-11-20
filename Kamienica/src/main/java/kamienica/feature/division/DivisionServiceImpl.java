package kamienica.feature.division;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.exception.WrongDivisionInputException;
import kamienica.core.util.CommonUtils;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.settings.SettingsDao;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantDao;

@Service
@Transactional
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    DivisionDao divisionDAO;
    @Autowired
    TenantDao tenantDAO;
    @Autowired
    ApartmentDao apartmentDAO;
    @Autowired
    SettingsDao settingsDao;

    @Override
    public List<Division> getList() {
        return divisionDAO.getList();
    }

    @Override
    public void deleteByID(Long id) {
        divisionDAO.deleteById(id);
        settingsDao.changeDivisionState(false);

    }

    @Override
    public void update(Division division) {
        divisionDAO.update(division);
        settingsDao.changeDivisionState(true);
    }

    @Override
    public void deleteAll() {
        divisionDAO.deleteAll();
        settingsDao.changeDivisionState(false);

    }

    @Override
    public void saveList(List<Division> division, LocalDate date) {

        divisionDAO.deleteAll();
        for (Division div : division) {
            div.setDate(date);
            divisionDAO.save(div);
        }
        settingsDao.changeDivisionState(true);

    }

    @Override
    public void saveList(DivisionForm form) throws InvalidDivisionException {
        if (DivisionValidator.checksumForDivision(form.getApartments(), form.getDivisionList())) {
            throw new InvalidDivisionException();
        }

        saveList(form.getDivisionList(), form.getDate());

    }

    @Override
    public void prepareForm(DivisionForm form) throws WrongDivisionInputException {
        List<Tenant> tenantList = tenantDAO.getActiveTenants();
        List<Apartment> apartmentList = apartmentDAO.getList();

        if (tenantList.isEmpty() || apartmentList.isEmpty()) {
            throw new WrongDivisionInputException();
        }

        form.setDivisionList(prepareDivisionListForRegistration(tenantList, apartmentList));
        form.setDate(new LocalDate());
        form.setApartments(apartmentList);
        form.setTenants(tenantList);

    }

    @Override
    public List<Division> prepareDivisionListForRegistration(List<Tenant> tenantList, List<Apartment> apartmentList) {

        ArrayList<Division> divisionList = new ArrayList<>();
        for (Tenant ten : tenantList) {
            for (Apartment ap : apartmentList) {
                divisionList.add(createDivision(tenantList, ten, ap));
            }
        }
        return divisionList;
    }

    private Division createDivision(List<Tenant> tenantList, Tenant ten, Apartment ap) {
        Division tmp = new Division();
        tmp.setApartment(ap);
        tmp.setTenant(ten);
        if (ap.getApartmentNumber() == 0) {
            tmp.setDivisionValue(Double.valueOf(CommonUtils.decimalFormat(1 / (double) tenantList.size())));
        } else if (ap.getApartmentNumber() == ten.getApartment().getApartmentNumber()) {
            tmp.setDivisionValue(1);
        } else {
            tmp.setDivisionValue(0);
        }
        return tmp;
    }


    @Override
    public boolean isDivisionCorrect() {
        return settingsDao.isDivisionCorrect();
    }

    @Override
    public Map<Tenant, List<Division>> getMappedList() {
        return null;
    }

}
