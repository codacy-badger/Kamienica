package kamienica.feature.meter;

import kamienica.core.enums.Media;
import kamienica.model.Meter;
import kamienica.model.MeterEnergy;
import kamienica.model.MeterGas;
import kamienica.model.MeterWater;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MeterServiceImpl implements MeterService {

    @Autowired
    MeterDao<MeterEnergy> energy;

    @Autowired
    MeterDao<MeterGas> gas;

    @Autowired
    MeterDao<MeterWater> water;

    @Override
    public <T extends Meter> void save(T meter, Media media) {
        if (meter.getApartment() == null) {
            meter.setMain(true);
        }
        switch (media) {
            case ENERGY:

                energy.save((MeterEnergy) meter);
                break;
            case GAS:

                gas.save((MeterGas) meter);
                break;
            case WATER:

                water.save((MeterWater) meter);
                break;
            default:
                break;
        }

    }

    @Override
    public <T extends Meter> void update(T meter, Media media) {
        meter.setMain(meter.getApartment() == null);
        switch (media) {
            case ENERGY:

                energy.update((MeterEnergy) meter);
                break;
            case GAS:

                gas.update((MeterGas) meter);
                break;
            case WATER:

                water.update((MeterWater) meter);
                break;
            default:
                break;
        }

    }

    // @Override
    // public void saveGas(MeterGas meter) {
    // if (meter.getApartment() == null) {
    // meter.main = true;
    // }
    // gas.save(meter);
    //
    // }
    //
    // @Override
    // public void saveWater(MeterWater meter) {
    // if (meter.getApartment() == null) {
    // meter.main = true;
    // }
    // water.save(meter);
    //
    // }
    //
    // @Override
    // public void saveEnergy(MeterEnergy meter) {
    // if (meter.getApartment() == null) {
    // meter.main = true;
    // }
    // energy.save(meter);
    //
    // }

    // @Override
    // public void updateGas(MeterGas meter) {
    // gas.update(meter);
    //
    // }
    //
    // @Override
    // public void updateWater(MeterWater meter) {
    // water.update(meter);
    //
    // }
    //
    // @Override
    // public void updateEnergy(MeterEnergy meter) {
    // energy.update(meter);
    //
    // }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Meter> List<T> getList(Media media) {
        switch (media) {
            case ENERGY:

                return (List<T>) energy.getList();

            case GAS:

                return (List<T>) gas.getList();

            case WATER:

                return (List<T>) water.getList();

            default:
                return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Meter> T getById(Long id, Media media) {
        switch (media) {
            case ENERGY:

                return (T) energy.getById(id);

            case GAS:

                return (T) gas.getById(id);

            case WATER:

                return (T) water.getById(id);

            default:
                return null;
        }
    }
    //
    // @Override
    // public List<MeterEnergy> getEnergyList() {
    //
    // return energy.getList();
    // }
    //
    // @Override
    // public List<MeterGas> getGasList() {
    // return gas.getList();
    // }
    //
    // @Override
    // public List<MeterWater> getWaterList() {
    // return water.getList();
    // }

    @Override
    public void delete(Long id, Media media) {
        switch (media) {
            case ENERGY:

                try {
                    energy.deleteById(id);
                } catch (ConstraintViolationException e) {
                    MeterEnergy meter = energy.getById(id);
                    meter.setDeactivation(LocalDate.now());
                    meter.setDescription(meter.getDescription() + " (NIEAKTYWNY)");
                    energy.update(meter);
                }

                break;
            case GAS:
                try {
                    gas.deleteById(id);
                } catch (ConstraintViolationException e) {
                    MeterGas meter = gas.getById(id);
                    meter.setDeactivation(LocalDate.now());
                    meter.setDescription(meter.getDescription() + " (NIEAKTYWNY)");
                    gas.update(meter);
                }
                break;
            case WATER:
                try {
                    water.deleteById(id);
                } catch (ConstraintViolationException e) {
                    MeterWater meter = water.getById(id);
                    meter.setDeactivation(LocalDate.now());
                    meter.setDescription(meter.getDescription() + " (NIEAKTYWNY)");
                    water.update(meter);
                }
                break;
            default:
                break;
        }

    }

    // @Override
    // public void deleteEnergyByID(Long id) {
    // energy.deleteById(id);
    //
    // }
    //
    // @Override
    // public void deleteGasByID(Long id) {
    // gas.deleteById(id);
    //
    // }
    //
    // @Override
    // public void deleteWaterByID(Long id) {
    // water.deleteById(id);
    //
    // }

    // @Override
    // public MeterEnergy getEnergyByID(Long id) {
    // return energy.getById(id);
    // }
    //
    // @Override
    // public MeterGas getGasByID(Long id) {
    // return gas.getById(id);
    // }
    //
    // @Override
    // public MeterWater getWaterByID(Long id) {
    // return water.getById(id);
    // }

    @Override
    public Set<Long> getIdList(Media media) {
        switch (media) {
            case ENERGY:
                return energy.getIdList();
            case WATER:
                return water.getIdList();
            case GAS:
                return gas.getIdList();

            default:
                return null;
        }
    }

    @Override
    public Set<Long> getIdListForActiveMeters(Media media) {
        switch (media) {
            case ENERGY:
                return energy.getIdListForActiveMeters();
            case WATER:
                return water.getIdListForActiveMeters();
            case GAS:
                return gas.getIdListForActiveMeters();

            default:
                return null;
        }
    }

    @Override
    public boolean ifMainExists(Media media) {
        switch (media) {
            case ENERGY:

                return energy.ifMainExists();

            case GAS:

                return gas.ifMainExists();

            case WATER:

                return water.ifMainExists();

            default:
                return false;
        }
    }

    @Override
    public <T extends Meter> void validateMeter(BindingResult result, Media media, T meter) {
        final String WARM_CWU = "Licznik Główny nie może być licznikiem CWU bądź Ciepłej Wody";
        final String MAIN_EXISTS = "Istnieje już w bazie licznik główny";

        switch (media) {
            case ENERGY:
                if (meter.isMain() && ifMainExists(Media.ENERGY)) {
                    result.rejectValue("apartment", "error.meter", MAIN_EXISTS);
                }
                break;

            case GAS:
                if (meter.isMain() && ifMainExists(Media.GAS)) {
                    result.rejectValue("apartment", "error.meter", MAIN_EXISTS);
                }
                if (meter.getApartment() == null && ((MeterGas) meter).isCwu()) {
                    result.rejectValue("cwu", "error.meter", WARM_CWU);
                }
                break;
            case WATER:
                if (meter.isMain() && ifMainExists(Media.WATER)) {
                    result.rejectValue("apartment", "error.meter", MAIN_EXISTS);
                }
                if (meter.getApartment() == null && ((MeterWater) meter).getIsWarmWater()) {
                    result.rejectValue("isWarmWater", "error.meter", WARM_CWU);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public <T extends Meter> void deactivateMeter(T meter, Media media) {
        meter.setDeactivation(LocalDate.now());

        switch (media) {
            case ENERGY:
                energy.update((MeterEnergy) meter);
                break;
            case GAS:
                gas.update((MeterGas) meter);
                break;
            case WATER:
                water.update((MeterWater) meter);
                break;

            default:
                break;
        }

    }
}
