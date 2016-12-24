package kamienica.feature.residence;

import kamienica.model.Residence;

import java.util.List;

public interface ResidenceService {

    void save(Residence residence);

    void update(Residence residence);

    List<Residence> getList();

    Residence getById(Long id);

    void deleteById(Long id);


}
