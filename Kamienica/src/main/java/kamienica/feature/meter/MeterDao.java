package kamienica.feature.meter;

import kamienica.dao.DaoInterface;

public interface MeterDao<M extends MeterAbstract> extends DaoInterface<M> {

	public boolean ifMainExists();
}
