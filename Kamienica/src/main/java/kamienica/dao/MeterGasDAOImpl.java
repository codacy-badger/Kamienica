package kamienica.dao;

import org.springframework.stereotype.Repository;

import kamienica.model.MeterGas;

@Repository("meterGasDao")
public class MeterGasDAOImpl extends AbstractDao<Integer, MeterGas> implements DaoInterface<MeterGas> {

}
