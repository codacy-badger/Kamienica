package kamienica.feature.residence;

import kamienica.model.Residence;
import kamienica.model.Tenant;

import java.util.List;

public interface ResidenceService {

    void save(Residence residence);

    void update(Residence residence);

    List<Residence> getList();

    List<Residence> listForOwner();

    List<Residence> listForFirstLogin(Tenant tenant);

    Residence getById(Long id);

    void deleteById(Long id);


}
