package kamienica.feature.division;

import kamienica.core.util.CommonUtils;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.tenant.ITenantDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Division;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Status;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DivisionServiceImpl implements IDivisionService {

    private final ITenantDao tenantDAO;
    private final IApartmentDao apartmentDAO;

    @Autowired
    public DivisionServiceImpl( ITenantDao tenantDAO, IApartmentDao apartmentDAO) {
        this.tenantDAO = tenantDAO;
        this.apartmentDAO = apartmentDAO;
    }


    @Override
    public List<Division> createDivisionForResidence(final Residence res) {
        final Criterion forResidence = Restrictions.eq("residence", res);
        final List<Apartment> apartments = apartmentDAO.findByCriteria(forResidence);
        final List<Tenant> tenants = tenantDAO.findByCriteria(createCriteria(apartments));
        
        return prepareDivisionList(tenants, apartments);
    }

    private Criterion[] createCriteria(final List<Apartment> apartments) {
        final Criterion forTheseApartments = Restrictions.in("apartment", apartments);
        final Criterion onlyActive = Restrictions.eq("status", Status.ACTIVE);
        return new Criterion[] {forTheseApartments, onlyActive};
    }

    private List<Division> prepareDivisionList(List<Tenant> tenantList, List<Apartment> apartmentList) {
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
