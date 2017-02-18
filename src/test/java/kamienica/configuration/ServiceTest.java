package kamienica.configuration;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentService;
import kamienica.feature.division.IDivisionService;
import kamienica.feature.invoice.IInvoiceService;
import kamienica.feature.meter.IMeterService;
import kamienica.feature.payment.IPaymentService;
import kamienica.feature.reading.IReadingService;
import kamienica.feature.residence.ResidenceService;
import kamienica.feature.residenceownership.IResidenceOwnershipService;
import kamienica.feature.settings.ISettingsService;
import kamienica.feature.tenant.ITenantService;
import kamienica.feature.user_admin.OwnerUserDataService;
import kamienica.feature.user_admin.SecurityServiceImpl;
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
    protected IDivisionService divisionService;
    @Autowired
    protected IPaymentService IPaymentService;
    @Autowired
    protected IInvoiceService invoiceService;
    @Autowired
    protected IReadingService IReadingService;
    @Autowired
    protected IMeterService meterService;
    @Autowired
    protected SecurityServiceImpl securityService;
    @Autowired
    protected OwnerUserDataService ownerUserDataService;
    @Autowired
    protected ResidenceService residenceService;
    @Autowired
    protected IResidenceOwnershipService residenceOwnershipService;

    /**
     * difference factor for calculated data
     */
    protected final double DELTA = 0.5;

    protected static final LocalDate TODAY = new LocalDate();
    protected final static String FIRST_OWNER_MAIL = "owner@res1";


    @BeforeClass
    public static void init() throws SQLException {
//        Server.main();
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

}
