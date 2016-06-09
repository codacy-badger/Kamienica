package kamienica.feature.meter;

import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.dao.DaoInterface;

@Repository("meterGasDao")
public class MeterGasDAOImpl extends AbstractDao<Integer, MeterGas> implements DaoInterface<MeterGas> {

}
