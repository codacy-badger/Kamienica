package kamienica.controller.jsp;

import kamienica.controller.ControllerTest;
import kamienica.feature.meter.IMeterService;
import kamienica.feature.meter.MeterService;
import kamienica.feature.reading.IReadingService;
import kamienica.feature.reading.ReadingService;
import kamienica.feature.residence.IResidenceService;
import kamienica.feature.residence.ResidenceServiceImpl;
import kamienica.model.entity.Reading;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.testutils.EntityProvider;
import org.h2.tools.Server;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ReadingControllerTest extends ControllerTest {

    private static final String PREFIX = "/Admin/Reading";
    @Mock
    private IMeterService meterService = Mockito.mock(MeterService.class);;
    @Mock
    private IReadingService readingService = Mockito.mock(ReadingService.class);
    @Mock
    private IResidenceService residenceService = Mockito.mock(ResidenceServiceImpl.class);




    @Test
    @Ignore("need to work out how it works")
    public void readingEnergyRegister() throws Exception {
        IResidenceService residenceService = Mockito.mock(ResidenceServiceImpl.class);
        when(residenceService.getById(Matchers.any())).thenReturn(EntityProvider.RESIDENCE);
        when(readingService.getLatestNew(Matchers.any(), Matchers.any())).thenReturn(EntityProvider.ENERGY_NEW);
        HashMap<String, Object> model = new HashMap<>();
        mockMvc.perform(get(PREFIX + "/readingEnergyRegister?residence_id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name(PREFIX + "/ReadingEnergyRegister"))
                .andExpect(forwardedUrl("/WEB-INF/views/Admin/Reading/ReadingEnergyRegister.jsp"))
                .andExpect(model().attribute("model", model));
    }

    @Test
    public void readingGasRegister() throws Exception {

    }

    @Test
    public void readingWaterRegister() throws Exception {

    }

    @Test
    public void readingEnergySave() throws Exception {

    }

    @Test
    public void readingGasSave() throws Exception {

    }

    @Test
    public void readingWaterSave() throws Exception {

    }

    @Test
    public void readingList() throws Exception {

    }

    @Test
    public void readingEnergyDelete() throws Exception {

    }

    @Test
    public void readingGasDelete() throws Exception {

    }

    @Test
    public void usunReadingWater() throws Exception {

    }

    @Test
    public void readingEnergyEdit() throws Exception {

    }

    @Test
    public void readingGasEdit() throws Exception {

    }

    @Test
    public void readingWaterEdit() throws Exception {

    }

    @Test
    public void readingEnergyOverwite() throws Exception {

    }

    @Test
    public void readingGasOverwrite() throws Exception {

    }

    @Test
    public void readingWaterOverwrite() throws Exception {

    }

    @Test
    public void energyRest() throws Exception {

    }

    @Test
    public void gasRest() throws Exception {

    }

    @Test
    public void waterRest() throws Exception {

    }

}