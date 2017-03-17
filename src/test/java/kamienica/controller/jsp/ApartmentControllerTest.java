package kamienica.controller.jsp;

import kamienica.controller.ControllerTest;
import org.junit.Test;

public class ApartmentControllerTest extends ControllerTest {

    @Test
    public void apartmentController() throws Exception {
        String view = "/Admin/Apartment/apartment";
        assertMVC(view, view,"/WEB-INF/views/Admin/Apartment/apartment.jsp");
    }
}