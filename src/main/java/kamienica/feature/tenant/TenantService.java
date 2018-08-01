package kamienica.feature.tenant;

import com.google.common.collect.Lists;
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
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class TenantService implements ITenantService {

    private static final String SELECT_TENANTS_BY_RESIDENCE = "select * from TENANT where rentContract_id in" +
            "(SELECT id FROM RENT_CONTRACT where apartment_id in" +
            "(select  id from APARTMENT where RESIDENCE_id = %s )" +
            ");";
    private final ITenantDao tenantDao;
    private final IApartmentDao apartmentDao;
    private final IRentContractDao rentContractDao;

    @Autowired
    public TenantService(final ITenantDao tenantDao, final IApartmentDao apartmentDao, final IRentContractDao rentContractDao) {
        this.tenantDao = tenantDao;
        this.apartmentDao = apartmentDao;
        this.rentContractDao = rentContractDao;
    }

    @Override
    public void save(final Tenant newTenant) {
        if (newTenant.getRentContract() == null) {
            savePriviligedTenant(newTenant);
        } else {
            final Tenant currentTenant = findCurrentTenant(newTenant.fetchApartment());
            if (ifApartmentIsEmpty(currentTenant)) {
                tenantDao.save(newTenant);
            } else {
                compareMovementDatesAndPersist(newTenant, currentTenant);
            }
        }
    }

    private void savePriviligedTenant(final Tenant newTenant) {
        if (newTenant.checkIsOwner() || newTenant.checkIsAdmin()) {
            tenantDao.save(newTenant);
        } else {
            throw new IllegalArgumentException("Tenant must have a rentContract");
        }
    }

    private boolean ifApartmentIsEmpty(Tenant currentTenant) {
        return currentTenant == null || currentTenant.getRentContract().getContractEnd().isBefore(new LocalDate());
    }

    private void compareMovementDatesAndPersist(Tenant newTenant, Tenant currentTenant) {
        final RentContract newRentContract = newTenant.getRentContract();
        final RentContract oldRentContract = currentTenant.getRentContract();
        if (newRentContract.getContractStart().isAfter(oldRentContract.getContractStart())) {
            oldRentContract.setContractEnd(newRentContract.getContractStart().minusDays(1));
            rentContractDao.save(newRentContract);
            rentContractDao.update(oldRentContract);
            tenantDao.save(newTenant);
        } else {
            newRentContract.setContractEnd(oldRentContract.getContractStart().minusDays(1));
            rentContractDao.save(newRentContract);
            tenantDao.save(newTenant);
        }
    }


    @Override
    public List<Tenant> list() {
        return tenantDao.getList();
    }

    @Override
    public List<Tenant> listForOwner() {
        final List<Apartment> apartments = apartmentDao.getListForOwner(SecurityDetails.getResidencesForOwner());
        final Criterion c1 = Restrictions.in("apartment", apartments);
        final List<RentContract> contracts = rentContractDao.findByCriteria(c1);
        return findByCriteria(Restrictions.in("rentContract", contracts));
    }

    @Override
    public List<Tenant> findByCriteria(final Criterion... crit) {
        return tenantDao.findByCriteria(crit);
    }

    @Override
    public List<Tenant> findForSpecifiedResicence(final Long residenceId) {
        //TODO UGLY WORKAROUND!!!
        final List<Residence> residences = Lists.newArrayList(getResidence(residenceId));
        final List<Apartment> apartments = apartmentDao.findForResidence(residences);
        final Criterion c = Restrictions.in("apartment", apartments);
        final List<RentContract> contracts = rentContractDao.findByCriteria(c);
        if (contracts.isEmpty()) {
            return Lists.newArrayList();
        }
        final Criterion tenantCriteria = Restrictions.in("rentContract", contracts);
        return tenantDao.findByCriteria(tenantCriteria);
    }

    private Residence getResidence(Long residenceId) {
        return SecurityDetails.getResidencesForOwner()
                .stream()
                .filter(x -> x.getId().equals(residenceId))
                .findFirst().get();
    }

    @Override
    public List<Tenant> listActiveTenants(final Residence residence) {
        final LocalDate now = new LocalDate();
        final List<Apartment> apartments = apartmentDao.getListForOwner(Collections.singletonList(residence));
        final Criterion c1 = Restrictions.in("apartment", apartments);
        final Criterion c2 = Restrictions.lt("contractStart", now);
        final Criterion c3 = Restrictions.gt("contractEnd", now);
        List<RentContract> contracts = rentContractDao.findByCriteria(c1, c2, c3);
        return tenantDao.findByCriteria(Restrictions.in("rentContract", contracts));
    }

    @Override
    public Tenant findCurrentTenant(final Apartment apartment) {
        final LocalDate now = new LocalDate();
        final Criterion c1 = Restrictions.eq("apartment", apartment);
        final Criterion c2 = Restrictions.lt("contractStart", now);
        final Criterion c3 = Restrictions.gt("contractEnd", now);
        final RentContract contract = rentContractDao.findOneByCriteria(c1, c2, c3);
        return tenantDao.findOneByCriteria(Restrictions.eq("rentContract", contract));
    }

    @Override
    public void delete(final Tenant object) {
        tenantDao.delete(object);
    }

    @Override
    public void update(final Tenant tenant) {
        tenantDao.update(tenant);

    }

    @Override
    public Tenant getById(final Long id) {
        return tenantDao.getById(id);
    }


    @Override
    public Tenant loadByMail(final String mail) {
        return tenantDao.loadByMail(mail);
    }

    @Override
    public List<Tenant> getOwners() {
        final Criterion onlyOwners = Restrictions.eq("role", UserRole.OWNER);
        return tenantDao.findByCriteria(onlyOwners);
    }
}
