package kamienica.service.settings;

import static org.junit.Assert.assertEquals;

import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Settings;
import kamienica.model.enums.WaterHeatingSystem;
import org.junit.Test;

public class SettingsServiceTest extends ServiceTest {

    //TODO settings should be limited to one owner
    @Test
    public void getList() {
        Settings settings = settingsService.getSettings(getOWnersResidence());
        assertEquals(WaterHeatingSystem.SHARED_GAS, settings.getWaterHeatingSystem());
    }
}
