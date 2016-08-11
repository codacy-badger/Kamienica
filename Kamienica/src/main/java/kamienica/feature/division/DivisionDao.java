package kamienica.feature.division;

import kamienica.dao.DaoInterface;

public interface DivisionDao extends DaoInterface<Division> {

	public void deleteAll();

}
