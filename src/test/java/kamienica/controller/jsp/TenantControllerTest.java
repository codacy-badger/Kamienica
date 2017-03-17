package kamienica.controller.jsp;

import kamienica.controller.ControllerTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class TenantControllerTest extends ControllerTest {

    private static final String URL = "/Admin/Tenant/tenant";

    @Test
    public void list() throws Exception {
        assertMVC(URL, URL, "/WEB-INF/views/Admin/Tenant/tenant.jsp");
    }

}