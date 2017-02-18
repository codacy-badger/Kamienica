package kamienica.feature.division;

import kamienica.model.jpa.dao.BasicDao;
import kamienica.model.entity.Division;

public interface IDivisionDao extends BasicDao<Division> {

	void deleteAll();

}
