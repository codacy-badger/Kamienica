package kamienica.controller.jsp;

import kamienica.controller.ControllerTest;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class InvoiceControllerTest extends ControllerTest {

   private final static String URL =  "/Admin/Invoice/";
   private  final static String REDIRECT = "/WEB-INF/views/Admin/Invoice/";

    @Test
    public void energyRest() throws Exception {
        final String media = "energy";
        mockMvc.perform(get(URL +media))
                .andExpect(status().isOk())
                .andExpect(view().name(URL +media))
                .andExpect(forwardedUrl(REDIRECT+ media +SUFFIX));
    }

    @Test
    public void gasRest() throws Exception {
        final String media = "gas";
        mockMvc.perform(get(URL +media))
                .andExpect(status().isOk())
                .andExpect(view().name(URL +media))
                .andExpect(forwardedUrl(REDIRECT+ media +SUFFIX));
    }

    @Test
    public void waterRest() throws Exception {
        final String media = "water";
        mockMvc.perform(get(URL +media))
                .andExpect(status().isOk())
                .andExpect(view().name(URL +media))
                .andExpect(forwardedUrl(REDIRECT+ media +SUFFIX));
    }

}