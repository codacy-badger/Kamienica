package kamienica.feature.residenceownership;

import kamienica.model.ResidenceOwnership;
import kamienica.model.Tenant;

import java.util.List;

public interface ResidenceOwnershipService {

    List<ResidenceOwnership> list(Tenant t);

    void delete(Long id);

    void save(ResidenceOwnership residenceOwnership, Tenant tenant);

    void update(ResidenceOwnership residenceOwnership);

}
