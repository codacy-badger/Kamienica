package kamienica.feature.meter;

import kamienica.model.entity.Meter;
import kamienica.model.entity.Residence;
import kamienica.model.enums.Media;
import kamienica.model.jpa.dao.IBasicDao;

import java.util.Set;

public interface IMeterDao extends IBasicDao<Meter> {

	boolean ifMainExists();
	
	Set<Long> getIdListForActiveMeters(Residence r, Media media);

}
