package kamienica.feature.meter;

import kamienica.core.dao.DaoInterface;
import kamienica.model.Meter;

import java.util.Set;

public interface MeterDao<M extends Meter> extends DaoInterface<M> {

	boolean ifMainExists();
	
	Set<Long> getIdListForActiveMeters();
}
