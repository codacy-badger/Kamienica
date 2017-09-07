package kamienica.feature.residence;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.invoice.IInvoiceDao;
import kamienica.feature.meter.IMeterDao;
import kamienica.feature.payment.IPaymentDao;
import kamienica.feature.reading.IReadingDao;
import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.feature.rentcontract.IRentContractDao;
import kamienica.feature.residenceownership.IResidenceOwnershipDao;
import kamienica.feature.settings.ISettingsDao;
import kamienica.feature.tenant.ITenantDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PurgeService implements IPurgeService {

    private final IResidenceDao residenceDao;
    private final IMeterDao meterDao;
    private final IReadingDetailsDao readingDetailsDao;
    private final IApartmentDao apartmentDao;
    private final ITenantDao tenantDao;
    private final IInvoiceDao invoiceDao;
    private final IPaymentDao paymentDao;
    private final IReadingDao readingDao;
    private final IRentContractDao rentContractDao;
    private final IResidenceOwnershipDao residenceOwnershipDao;
    private final ISettingsDao settingsDao;

    @Autowired
    public PurgeService(IResidenceDao residenceDao, IMeterDao meterDao, IReadingDetailsDao details, IApartmentDao apartmentDao, ITenantDao tenantDao, IInvoiceDao invoiceDao, IPaymentDao paymentDao, IReadingDao readingDao, IRentContractDao rentContractDao, IResidenceOwnershipDao residenceOwnershipDao, ISettingsDao settingsDao) {
        this.residenceDao = residenceDao;
        this.meterDao = meterDao;
        this.readingDetailsDao = details;
        this.apartmentDao = apartmentDao;
        this.tenantDao = tenantDao;
        this.invoiceDao = invoiceDao;
        this.paymentDao = paymentDao;
        this.readingDao = readingDao;
        this.rentContractDao = rentContractDao;
        this.residenceOwnershipDao = residenceOwnershipDao;
        this.settingsDao = settingsDao;
    }

    @Override
    public void purgeData(Residence residence) {
        final Criterion res = Restrictions.eq("residence", residence);

        deleteReadings(res);
        deleteMeters(res);
        deleteInvoices(residence);
        deleteOwnership(res);
        deleteSetting(res);
        deleteApartmentsAndTenants(res);
        deleteResidence(residence);
    }

    private void deleteSetting(final Criterion res) {
        final Settings s = settingsDao.findOneByCriteria(res);
        settingsDao.delete(s);
    }

    private void deleteResidence(final Residence residence) {
        final Residence r = residenceDao.getById(residence.getId());
        SecurityDetails.removeResidenceFromPrincipal(r);
        residenceDao.delete(r);
    }

    private void deleteApartmentsAndTenants(final Criterion res) {
        final List<Apartment> apartments = apartmentDao.findByCriteria(res);
        final Criterion apartmentCriterion = Restrictions.in("apartment", apartments);
        final List<RentContract> rentContracts = rentContractDao.findByCriteria(apartmentCriterion);
        final List<Tenant> tenants = tenantDao.findByCriteria(Restrictions.in("rentContract", rentContracts));

        rentContractDao.delete(rentContracts);
        tenantDao.delete(tenants);
        apartmentDao.delete(apartments);
    }

    private void deleteOwnership(Criterion res) {
        final List<ResidenceOwnership> ownership = residenceOwnershipDao.findByCriteria(res);
        residenceOwnershipDao.delete(ownership);
    }

    private void deleteInvoices(final Residence residence) {
        final List<Invoice> invoices = invoiceDao.getList(residence, Media.ENERGY);
        invoices.addAll(invoiceDao.getList(residence, Media.GAS));
        invoices.addAll(invoiceDao.getList(residence, Media.WATER));
        final Criterion invoiceCriterion = Restrictions.in("invoice", invoices);

        final List<Payment> payments = paymentDao.findByCriteria(invoiceCriterion);
        paymentDao.delete(payments);
        invoiceDao.delete(invoices);
    }

    private void deleteMeters(final Criterion res) {
        final List<Meter> meters = meterDao.findByCriteria(res);
        meterDao.delete(meters);
    }

    private void deleteReadings(final Criterion c) {
        final List<ReadingDetails> details = readingDetailsDao.findByCriteria(c);
        final List<Reading> readings = readingDao.findByCriteria(Restrictions.in("readingDetails", details));
        readingDao.delete(readings);
        readingDetailsDao.delete(details);
    }
}
