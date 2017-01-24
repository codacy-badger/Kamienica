package kamienica.feature.residence;

import kamienica.model.Residence;
import kamienica.model.Tenant;

import java.util.List;

public interface ResidenceService {

    void save(Residence residence, Tenant t);

    void update(Residence residence);

    List<Residence> getList();

    List<Residence> listForOwner(Tenant t);

    Residence getById(Long id);

    void deleteById(Long id);


}
