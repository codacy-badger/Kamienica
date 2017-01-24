package kamienica.configuration;

import kamienica.core.util.CommonUtils;
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
import kamienica.feature.user_admin.SecurityService;
import org.joda.time.LocalDate;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {JUnitConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@PrepareForTest(CommonUtils.class)
public abstract class DatabaseTest {

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
    protected SecurityService securityService;
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

//    @BeforeClass
//    public static void init() throws SQLException {
//        Server.main();
//    }

}
