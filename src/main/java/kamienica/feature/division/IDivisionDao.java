package kamienica.feature.division;

import kamienica.model.entity.Division;
import kamienica.model.jpa.dao.BasicDao;

public interface IDivisionDao extends BasicDao<Division> {

	void deleteAll();

}
