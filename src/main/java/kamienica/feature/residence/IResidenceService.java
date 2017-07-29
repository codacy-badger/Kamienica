package kamienica.feature.residence;

import kamienica.model.entity.Residence;
import kamienica.model.entity.Tenant;

import java.util.List;

public interface IResidenceService {

    void save(Residence residence);

    void update(Residence residence);

    List<Residence> getList();

    List<Residence> listForOwner();

    List<Residence> listForFirstLogin(Tenant tenant);

    Residence getById(Long id);

    void delete(Residence residence);
}
