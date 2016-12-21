package kamienica.service;

import kamienica.configuration.JUnitConfig;
import kamienica.feature.apartment.ApartmentService;
import kamienica.feature.division.DivisionService;
import kamienica.feature.invoice.InvoiceService;
import kamienica.feature.meter.MeterService;
import kamienica.feature.payment.PaymentService;
import kamienica.feature.reading.ReadingService;
import kamienica.feature.settings.SettingsService;
import kamienica.feature.tenant.TenantService;
import kamienica.feature.user_admin.AdminUserService;
import kamienica.feature.user_admin.SecurityService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {JUnitConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractServiceTest {

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
    protected AdminUserService adminUserService;

    /**
     * difference factor for calculated data
     */
    protected final double DELTA = 0.5;

}