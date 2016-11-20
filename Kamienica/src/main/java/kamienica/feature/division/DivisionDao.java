package kamienica.feature.division;

import kamienica.core.dao.DaoInterface;

public interface DivisionDao extends DaoInterface<Division> {

	void deleteAll();

}
