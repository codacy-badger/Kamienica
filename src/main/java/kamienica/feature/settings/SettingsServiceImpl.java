package kamienica.feature.settings;

import kamienica.model.entity.Residence;
import kamienica.model.entity.Settings;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SettingsServiceImpl implements ISettingsService {

	private final ISettingsDao dao;

	@Autowired
	public SettingsServiceImpl(ISettingsDao dao) {
		this.dao = dao;
	}

    @Override
    public Settings getSettings(final Residence residence) {
        return dao.findOneByCriteria(Restrictions.eq("residence", residence));
    }

    @Override
    public List<Settings> list() {
        return dao.getList();
    }

    @Override
    public void save(Settings settings) {
        if (settings.getId() == null) {
            dao.save(settings);
        } else {
            dao.update(settings);
        }

    }

}
