package kamienica.service;

import kamienica.configuration.DatabaseTest;
import kamienica.model.Settings;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SettingsServiceTest extends DatabaseTest {

    @Test
    public void getList() {
        Settings settings = settingsService.getSettings();

        assertEquals(true, settings.isCorrectDivision());

    }

    @Test
    public void getById() {
        assertNotNull(settingsService.getSettings());

    }

    @Test
    @Transactional
    public void changeResolvementState() {
        settingsService.changeDivisionState(false);
        assertEquals(false, settingsService.isDivisionCorrect());
        settingsService.changeDivisionState(true);
        assertEquals(true, settingsService.isDivisionCorrect());
    }

}
