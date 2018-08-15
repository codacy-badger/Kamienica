package kamienica.controller.api.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import kamienica.feature.apartment.IApartmentService;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Residence;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ApartmentApi.class, secure = false)
@RunWith(SpringRunner.class)
public class ApartmentApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IApartmentService apartmentService;

    private static final Residence RESIDENCE_ONE = new Residence(1L, null, null, "Gdynia");
    private static final Residence RESIDENCE_TWO = new Residence(2L, null, null, "Sopot");
    private static final List<Apartment> ALL_APARTMENTS = new ArrayList<>();
    private static final List<Apartment> APARTMENTS_FOR_RES_1 = new ArrayList<>();

    @BeforeClass
    public static void setup() {
        IntStream.range(1, 4).forEach(i -> {
            final Apartment forResOne = new Apartment(RESIDENCE_ONE, i, "a", String.valueOf(i));
            final Apartment forResTwo = new Apartment(RESIDENCE_TWO, i, "a", String.valueOf(i));

            ALL_APARTMENTS.add(forResOne);
            ALL_APARTMENTS.add(forResTwo);

            APARTMENTS_FOR_RES_1.add(forResOne);
        });
    }


    @Test
    public void shouldReturnListOfAllApartments() throws Exception {
        given(apartmentService.list()).willReturn(ALL_APARTMENTS);

        final MvcResult result = mvc.perform(get("/api/v1/apartments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].description", is("1")))
                .andReturn();

           assertNotNull(result.getResponse().getContentAsString());
    }


    @Test
    public void shouldReturnListOfAllApartmentsForResOne() throws Exception {
        given(apartmentService.getByResidence(RESIDENCE_ONE.getId(), false)).willReturn(APARTMENTS_FOR_RES_1);

        final MvcResult result = mvc.perform(get("/api/v1/apartments?residence=1&showSharedPart=false")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].description", is("1")))
                .andReturn();

        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void shouldCreateNewApartment() throws Exception {
        final Apartment apartment = new Apartment(RESIDENCE_ONE, 12, "1234", "new");
        final String requestBody = prepareRequestBody(apartment);

        final MvcResult result = mvc.perform(post("/api/v1/apartments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(result.getResponse().getContentAsString());
    }

    @Ignore
    @Test
    public void shouldDeleteApartment() throws Exception {
        final MvcResult result = mvc.perform(delete("/api/v1/apartments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(result.getResponse().getContentAsString());
    }

    private String prepareRequestBody(final Apartment apartment) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        final ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(apartment);
    }
}