package kamienica.core;

import kamienica.feature.apartment.Apartment;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by macfol on 10/17/16.
 */
public class Testing {

    @Mock
    Apartment ap = Mockito.mock(Apartment.class);
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void test() {
        when(ap.getApartmentNumber()).thenReturn(35);
        assertEquals(35, ap.getApartmentNumber());
    }

    @Test
    public void test1()  {
        //  create mock
        Apartment test = mockObject(Apartment.class);

        // define return value for method getUniqueId()
        when(test.getApartmentNumber()).thenReturn(43);

        // use mock in test....
        assertEquals(test.getApartmentNumber(), 43);
    }

    private <T> T mockObject(Class<T> classToMock) {
        return Mockito.mock(classToMock);
    }
}
