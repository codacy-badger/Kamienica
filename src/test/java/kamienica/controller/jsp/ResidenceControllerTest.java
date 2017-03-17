package kamienica.controller.jsp;

import kamienica.controller.ControllerTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResidenceControllerTest extends ControllerTest {

    private static final String URL = "/Admin/Residence/residence";

    @Test
    public void list() throws Exception {
        assertMVC(URL, URL, "/WEB-INF/views/Admin/Residence/residence.jsp");
    }

}