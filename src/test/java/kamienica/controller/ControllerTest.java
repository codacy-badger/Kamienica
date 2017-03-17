package kamienica.controller;

import kamienica.configuration.TestAppConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
@WebAppConfiguration
@Transactional
public abstract class ControllerTest {

    protected MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected final static String SUFFIX = ".jsp";
    protected final static String WATER = "water";
    protected final static String GAS = "gas";
    protected final static String ENERGY = "energy";

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    protected void assertMVC(final String getUrl, final String view, final String forwarderURL) throws Exception {
        mockMvc.perform(get(getUrl))
                .andExpect(status().isOk())
                .andExpect(view().name(view))
                .andExpect(forwardedUrl(forwarderURL));
    }
}
