package kamienica.service.residence;

import static org.junit.Assert.assertEquals;

import java.util.List;
import kamienica.configuration.ServiceTest;
import kamienica.model.entity.ResidenceOwnership;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;

@WithUserDetails(ServiceTest.OWNER)
public class ResidenceOwnershipServiceTest extends ServiceTest {

    @Test
    public void listForOwner() {
        final List<ResidenceOwnership> result = residenceOwnershipService.list();
        assertEquals(1, result.size());
    }
}