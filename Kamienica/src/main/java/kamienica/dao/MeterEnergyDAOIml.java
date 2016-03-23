package kamienica.dao;

import org.springframework.stereotype.Repository;

import kamienica.model.MeterEnergy;

@Repository("meterEnergyDao")
public class MeterEnergyDAOIml extends AbstractDao<Integer, MeterEnergy> implements DaoInterface<MeterEnergy> {

}
