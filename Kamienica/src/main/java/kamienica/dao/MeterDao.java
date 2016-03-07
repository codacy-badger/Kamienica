package kamienica.dao;

import java.util.List;

import kamienica.model.MeterAbstract;;

public interface MeterDao {

	public void save(Class<? extends MeterAbstract> meter);

	public void update(Class<? extends MeterAbstract> meter);

	public List<? extends MeterAbstract> getList();

	public Class<? extends MeterAbstract> getById(int id);

	public void deleteByID(int id);
}
