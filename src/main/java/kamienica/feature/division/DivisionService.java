package kamienica.feature.division;

import kamienica.core.util.CommonUtils;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.tenant.ITenantDao;
import kamienica.model.entity.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DivisionService implements IDivisionService {

    private final ITenantDao tenantDAO;
    private final IApartmentDao apartmentDAO;

    @Autowired
    public DivisionService(ITenantDao tenantDAO, IApartmentDao apartmentDAO) {
        this.tenantDAO = tenantDAO;
        this.apartmentDAO = apartmentDAO;
    }

    @Override
    public List<Division> createDivisionForResidence(final Invoice invoice) {
        final Residence residence = invoice.getResidence();
        final List<Apartment> apartments = apartmentDAO.findByCriteria(Restrictions.eq("residence", residence));
        final List<Tenant> tenants = tenantDAO.getActiveTenants(apartments);
        return prepareDivisionList(tenants, apartments);
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
        final double numOfTenants = (double) tenantList.size();
        Division tmp = new Division();
        tmp.setApartment(ap);
        tmp.setTenant(ten);
        if (ap.getApartmentNumber() == 0) {
            tmp.setDivisionValue(CommonUtils.decimalFormat(1 / numOfTenants));
        } else if (ap.getApartmentNumber() == ten.fetchApartment().getApartmentNumber()) {
            tmp.setDivisionValue(1);
        } else {
            tmp.setDivisionValue(0);
        }
        return tmp;
    }
}
