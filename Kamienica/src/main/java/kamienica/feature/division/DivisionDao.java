package kamienica.feature.division;

import kamienica.core.dao.DaoInterface;
import kamienica.model.Division;

public interface DivisionDao extends DaoInterface<Division> {

	void deleteAll();

}
