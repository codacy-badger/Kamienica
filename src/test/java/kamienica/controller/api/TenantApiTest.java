package kamienica.controller.api;

import kamienica.feature.tenant.TenantService;
import kamienica.model.Tenant;
import kamienica.testutils.EntityProvider;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;

import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.verify;

public class TenantApiTest {

    @Mock
    private TenantService service;

    @Spy
    private static List<Tenant> tenantList= EntityProvider.TENANTS;

    @InjectMocks
    private TenantApi api;


    @Test
    @Ignore
    public void list() throws Exception {
     //   api.list(true);
        verify(service, calls(1)).getList();
    }

    @Test
    @Ignore
    public void save() throws Exception {

    }

    @Test
    @Ignore
    public void delete() throws Exception {

    }

}