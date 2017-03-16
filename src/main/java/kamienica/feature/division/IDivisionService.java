package kamienica.feature.division;

import kamienica.model.entity.Division;
import kamienica.model.entity.Residence;

import java.util.List;

public interface IDivisionService {

    List<Division> createDivisionForResidence(Residence res);


}
