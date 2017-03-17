package kamienica.controller.jsp;

import kamienica.controller.ControllerTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class MeterControllerTest extends ControllerTest {

    private final static String URL = "/Admin/Meter/";
    private final static String PAGE = "/WEB-INF/views/Admin/Meter/";

    @Test
    public void energyRest() throws Exception {
        final String energy = URL + ENERGY;
        final String page = PAGE + ENERGY;
        assertMVC(energy, energy, page + SUFFIX);
    }

    @Test
    public void gasRest() throws Exception {
        final String energy = URL + GAS;
        final String page = PAGE + GAS;
        assertMVC(energy, energy, page + SUFFIX);
    }

    @Test
    public void waterRest() throws Exception {
        final String energy = URL + WATER;
        final String page = PAGE + WATER;
        assertMVC(energy, energy, page + SUFFIX);
    }

}