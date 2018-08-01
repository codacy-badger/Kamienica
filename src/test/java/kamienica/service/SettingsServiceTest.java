package kamienica.service;

import kamienica.configuration.ServiceTest;
import kamienica.model.entity.Settings;
import kamienica.model.enums.WaterHeatingSystem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SettingsServiceTest extends ServiceTest {

    @Test
    public void getList() {
        Settings settings = settingsService.getSettings(getOWnersResidence());

        assertEquals(WaterHeatingSystem.SHARED_GAS, settings.getWaterHeatingSystem());

    }



}
