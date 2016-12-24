package kamienica.feature.residence;

import kamienica.model.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResidenceServiceImpl implements ResidenceService {

    @Autowired
    ResidenceDao dao;

    @Override
    public void save(Residence residence) {
        dao.save(residence);
    }

    @Override
    public void update(Residence residence) {
        dao.update(residence);
    }

    @Override
    public List<Residence> getList() {
        return dao.getList();
    }

    @Override
    public Residence getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
