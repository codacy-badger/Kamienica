package kamienica.feature.residenceownership;

import kamienica.model.entity.ResidenceOwnership;
import kamienica.model.entity.Tenant;

import java.util.List;

public interface IResidenceOwnershipService {

    List<ResidenceOwnership> list();

    void delete(Long id);

    void save(ResidenceOwnership residenceOwnership, Tenant tenant);

    void update(ResidenceOwnership residenceOwnership);

}
