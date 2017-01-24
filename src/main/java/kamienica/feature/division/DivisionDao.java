package kamienica.feature.division;

import kamienica.core.daoservice.BasicDao;
import kamienica.model.Division;

public interface DivisionDao extends BasicDao<Division> {

	void deleteAll();

}
