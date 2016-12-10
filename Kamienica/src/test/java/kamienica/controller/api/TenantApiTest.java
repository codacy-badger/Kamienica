package kamienica.controller.api;

import kamienica.feature.tenant.TenantService;
import kamienica.model.Tenant;
import kamienica.testutils.EntityProvider;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;

/**
 * Created by macfol on 12/7/16.
 */
public class TenantApiTest {

    @Mock
    private TenantService service;

    @Spy
    private static List<Tenant> tenantList= EntityProvider.TENANTS;

    @InjectMocks
    private TenantApi api;


    @Test
    public void list() throws Exception {
        api.list(true);
        verify(service, calls(1)).getList();
    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

}