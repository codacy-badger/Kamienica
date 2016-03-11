package kamienica.dao;

import java.util.List;

import kamienica.model.MeterAbstract;;

public interface MeterDao<M extends MeterAbstract> {

	public void save(MeterAbstract meter);

	public void update(MeterAbstract meter);

	public List<MeterAbstract> getList();

	public MeterAbstract getById(int id);

	public void deleteByID(int id);
}
