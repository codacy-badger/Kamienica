package kamienica.controller.jsp;

import kamienica.controller.ControllerTest;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class PaymentControllerTest extends ControllerTest {

    @Test
    public void gasList() throws Exception {
        mockMvc.perform(get("/Admin/Payment/payment?media=GAS")).andExpect(status().isOk())
                .andExpect(view().name("/Admin/Payment/gas"))
                .andExpect(forwardedUrl("/WEB-INF/views/Admin/Payment/gas.jsp"));
    }

    @Test
    public void waterList() throws Exception {
        mockMvc.perform(get("/Admin/Payment/payment?media=WATER")).andExpect(status().isOk())
                .andExpect(view().name("/Admin/Payment/water"))
                .andExpect(forwardedUrl("/WEB-INF/views/Admin/Payment/water.jsp"));
    }

    @Test
    public void energyList() throws Exception {
        mockMvc.perform(get("/Admin/Payment/payment?media=ENERGY")).andExpect(status().isOk())
                .andExpect(view().name("/Admin/Payment/energy"))
                .andExpect(forwardedUrl("/WEB-INF/views/Admin/Payment/energy.jsp"));
    }
}