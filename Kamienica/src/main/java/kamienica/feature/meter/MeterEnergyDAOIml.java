package kamienica.feature.meter;

import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.dao.DaoInterface;

@Repository("meterEnergyDao")
public class MeterEnergyDAOIml extends AbstractDao<Long, MeterEnergy> implements DaoInterface<MeterEnergy> {

}
