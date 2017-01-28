package kamienica.configuration;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.ApartmentService;
import kamienica.feature.division.DivisionService;
import kamienica.feature.invoice.InvoiceService;
import kamienica.feature.meter.MeterService;
import kamienica.feature.payment.PaymentService;
import kamienica.feature.reading.ReadingService;
import kamienica.feature.residence.ResidenceService;
import kamienica.feature.residenceownership.ResidenceOwnershipService;
import kamienica.feature.settings.SettingsService;
import kamienica.feature.tenant.TenantService;
import kamienica.feature.user_admin.OwnerUserDataService;
import kamienica.feature.user_admin.SecurityServiceImpl;
import kamienica.model.Residence;
import kamienica.model.Tenant;
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

    @Autowired
    protected TenantService tenantService;
    @Autowired
    protected ApartmentService apartmentService;
    @Autowired
    protected SettingsService settingsService;
    @Autowired
    protected DivisionService divisionService;
    @Autowired
    protected PaymentService paymentService;
    @Autowired
    protected InvoiceService invoiceService;
    @Autowired
    protected ReadingService readingService;
    @Autowired
    protected MeterService meterService;
    @Autowired
    protected SecurityServiceImpl securityService;
    @Autowired
    protected OwnerUserDataService ownerUserDataService;
    @Autowired
    protected ResidenceService residenceService;
    @Autowired
    protected ResidenceOwnershipService residenceOwnershipService;

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
