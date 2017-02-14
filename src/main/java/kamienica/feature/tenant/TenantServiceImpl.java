package kamienica.feature.tenant;

import kamienica.core.enums.Status;
import kamienica.core.enums.UserRole;
import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.model.Apartment;
import kamienica.model.Tenant;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TenantServiceImpl implements TenantService {

    private final TenantDao tenantDao;
    private final ApartmentDao apartmentDao;

    @Autowired
    public TenantServiceImpl(TenantDao tenantDao,  ApartmentDao apartmentDao) {
        this.tenantDao = tenantDao;
        this.apartmentDao = apartmentDao;
    }

    @Override
    public void save(Tenant newTenant) {
        Tenant currentTenant = tenantDao.getTenantForApartment(newTenant.getApartment());
        if (currentTenant == null) {
            tenantDao.save(newTenant);
        } else compareMovementDatesAndPersist(newTenant, currentTenant);
    }

    private void compareMovementDatesAndPersist(Tenant newTenant, Tenant currentTenant) {
        if (currentTenant.getMovementDate().isAfter(newTenant.getMovementDate())) {
            newTenant.setStatus(Status.INACTIVE);
            tenantDao.save(newTenant);
        } else {
            currentTenant.setStatus(Status.INACTIVE);
            tenantDao.save(currentTenant);
            tenantDao.save(newTenant);
        }
    }


    @Override
    public List<Tenant> list() {
        return tenantDao.getList();
    }

    @Override
    public List<Tenant> listForOwner() {
        List<Apartment> apartments = apartmentDao.getListForOwner(SecurityDetails.getResidencesForOwner());
        Criterion c = Restrictions.in("apartment", apartments);
        return findByCriteria(c);
    }

    @Override
    public List<Tenant> listForTenant() {
        return null;
    }

    @Override
    public List<Tenant> findByCriteria(Criterion... crit) {
        return tenantDao.findByCriteria(crit);
    }

    @Override
    public void deleteById(Long id) {
        tenantDao.deleteById(id);
    }

    @Override
    public void update(Tenant tenant) {
        tenantDao.update(tenant);

    }

    @Override
    public Tenant getById(Long id) {
        return tenantDao.getById(id);
    }

    @Override
    public List<Tenant> getActiveTenants() {
        return tenantDao.getActiveTenants();
    }

    @Override
    public Tenant loadByMail(String mail) {
        return tenantDao.loadByMail(mail);
    }

    @Override
    public List<Tenant> getOwners() {
        Criterion onlyOwners = Restrictions.eq("role", UserRole.OWNER);
        return tenantDao.findByCriteria(onlyOwners);
    }

}
