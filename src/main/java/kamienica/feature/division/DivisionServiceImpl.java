package kamienica.feature.division;

import kamienica.core.util.CommonUtils;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.tenant.ITenantDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Division;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Status;
import kamienica.model.exception.WrongDivisionInputException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DivisionServiceImpl implements IDivisionService {

    private final IDivisionDao divisionDAO;
    private final ITenantDao tenantDAO;
    private final IApartmentDao apartmentDAO;

    @Autowired
    public DivisionServiceImpl(IDivisionDao divisionDAO, ITenantDao tenantDAO, IApartmentDao apartmentDAO) {
        this.divisionDAO = divisionDAO;
        this.tenantDAO = tenantDAO;
        this.apartmentDAO = apartmentDAO;
    }

    @Override
    public List<Division> getList() {
        return divisionDAO.getList();
    }

    @Override
    public List<Division> createDivisionForResidence(Residence res) {
        final Criterion forResidence = Restrictions.eq("residence", res);
        List<Apartment> apartments = apartmentDAO.findByCriteria(forResidence);

        final Criterion forTheseApartments = Restrictions.in("apartment", apartments);
        final Criterion onlyActive = Restrictions.eq("status", Status.ACTIVE);
        List<Tenant> tenants = tenantDAO.findByCriteria(forTheseApartments, onlyActive);
        return prepareDivisionList(tenants, apartments);
    }

    @Override
    public void deleteAll() {
        divisionDAO.deleteAll();
    }

    @Override
    public void saveList(List<Division> division, LocalDate date) {
        divisionDAO.deleteAll();
        for (Division div : division) {
            div.setDate(date);
            divisionDAO.save(div);
        }
    }

    @Override
    public void prepareForm(DivisionForm form) throws WrongDivisionInputException {
        List<Tenant> tenantList = tenantDAO.getActiveTenants();
        List<Apartment> apartmentList = apartmentDAO.getList();

        if (tenantList.isEmpty() || apartmentList.isEmpty()) {
            throw new WrongDivisionInputException();
        }

        form.setDivisionList(prepareDivisionList(tenantList, apartmentList));
        form.setDate(new LocalDate());
        form.setApartments(apartmentList);
        form.setTenants(tenantList);

    }

    @Override
    public List<Division> prepareDivisionList(List<Tenant> tenantList, List<Apartment> apartmentList) {
        List<Division> divisionList = new ArrayList<>();
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
            tmp.setDivisionValue(CommonUtils.decimalFormat(1 / (double) tenantList.size()));
        } else if (ap.getApartmentNumber() == ten.getApartment().getApartmentNumber()) {
            tmp.setDivisionValue(1);
        } else {
            tmp.setDivisionValue(0);
        }
        return tmp;
    }


}
