package kamienica.configuration;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentService;
import kamienica.feature.invoice.IInvoiceService;
import kamienica.feature.meter.IMeterService;
import kamienica.feature.payment.IPaymentService;
import kamienica.feature.reading.IReadingService;
import kamienica.feature.readingdetails.IReadingDetailsService;
import kamienica.feature.residence.IResidenceService;
import kamienica.feature.residenceownership.IResidenceOwnershipService;
import kamienica.feature.settings.ISettingsService;
import kamienica.feature.tenant.ITenantService;
import kamienica.feature.user_admin.IOwnerUserDataService;
import kamienica.feature.user_admin.SecurityServiceImpl;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;
import org.joda.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@ContextConfiguration(classes = {JUnitConfig.class})
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest(SecurityDetails.class)
public abstract class ServiceTest {

    protected static final long RESIDENCE_ID = 1L;
    @Autowired
    protected ITenantService tenantService;
    @Autowired
    protected IApartmentService apartmentService;
    @Autowired
    protected ISettingsService settingsService;
    @Autowired
    protected IPaymentService paymentService;
    @Autowired
    protected IInvoiceService invoiceService;
    @Autowired
    protected IReadingService readingService;
    @Autowired
    protected IMeterService meterService;
    @Autowired
    protected SecurityServiceImpl securityService;
    @Autowired
    protected IOwnerUserDataService ownerUserDataService;
    @Autowired
    protected IResidenceService residenceService;
    @Autowired
    protected IResidenceOwnershipService residenceOwnershipService;
    @Autowired
    protected IReadingDetailsService readingDetailsService;
    /**
     * difference factor for calculated data
     */
    protected final double DELTA = 0.5;
    protected static final LocalDate TODAY = new LocalDate();
    protected final static String FIRST_OWNER_MAIL = "owner@res1";
    protected final static Long RES_1_OWNER_ID = 1L;

    @BeforeClass
    public static void init() throws SQLException {
        mockStatic(SecurityDetails.class);
    }

    protected List<Residence> getMockedResidences() {
        List<Residence> residences = new ArrayList<>();
        residences.add(residenceService.getById(1L));
        return residences;
    }

    protected Tenant getOwner() {
        return tenantService.getById(1L);
    }

    protected Residence getOWnersResidence() {
        return residenceService.getById(RESIDENCE_ID);
    }

    protected double countTotalPayment(List<Payment> paymentList) {
        double result = 0;
        for (Payment p : paymentList) {
            result += p.getPaymentAmount();
        }
        return result;
    }
}
