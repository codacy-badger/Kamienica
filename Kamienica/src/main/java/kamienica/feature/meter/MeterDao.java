package kamienica.feature.meter;

import java.util.Set;

import kamienica.core.dao.DaoInterface;

public interface MeterDao<M extends MeterAbstract> extends DaoInterface<M> {

	boolean ifMainExists();
	
	Set<Long> getIdListForActiveMeters();
}
