package kamienica.feature.tenant;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.rentcontract.IRentContractDao;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.RentContract;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.UserRole;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TenantServiceImpl implements ITenantService {

    private final ITenantDao tenantDao;
    private final IApartmentDao apartmentDao;
    private final IRentContractDao rentContractDao;

    @Autowired
    public TenantServiceImpl(ITenantDao tenantDao, IApartmentDao apartmentDao, IRentContractDao rentContractDao) {
        this.tenantDao = tenantDao;
        this.apartmentDao = apartmentDao;
        this.rentContractDao = rentContractDao;
    }

    @Override
    public void save(Tenant newTenant) {
        Tenant currentTenant = tenantDao.getTenantForApartment(newTenant.getApartment());
        if (currentTenant == null) {
            tenantDao.save(newTenant);
        } else compareMovementDatesAndPersist(newTenant, currentTenant);
    }

    private void compareMovementDatesAndPersist(Tenant newTenant, Tenant currentTenant) {

        if (currentTenant.getRentContract().getContractStart().isAfter(newTenant.getRentContract().getContractStart())) {
            tenantDao.save(newTenant);
        } else {
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
        final Criterion c1 = Restrictions.in("apartment", apartments);
        List<RentContract> contracts = rentContractDao.findByCriteria(c1);
        return findByCriteria(Restrictions.in("rentContract", contracts));
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
    public List<Tenant> listActiveTenants(Residence residence) {

        return null;
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
    public Tenant loadByMail(String mail) {
        return tenantDao.loadByMail(mail);
    }

    @Override
    public List<Tenant> getOwners() {
        Criterion onlyOwners = Restrictions.eq("role", UserRole.OWNER);
        return tenantDao.findByCriteria(onlyOwners);
    }

}
