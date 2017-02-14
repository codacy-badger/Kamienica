package kamienica.feature.meter;

import kamienica.core.daoservice.BasicDao;
import kamienica.model.Meter;
import kamienica.model.Residence;

import java.util.Set;

public interface MeterDao<M extends Meter> extends BasicDao<M> {

	boolean ifMainExists();
	
	Set<Long> getIdListForActiveMeters(Residence r);

}
