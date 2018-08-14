package kamienica.feature.settings;

import java.util.List;
import kamienica.model.entity.Residence;
import kamienica.model.entity.Settings;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SettingsService implements ISettingsService {

	private final ISettingsDao dao;

	@Autowired
	public SettingsService(ISettingsDao dao) {
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
    public void save(final Settings settings) {
        if (settings.getId() == null) {
            dao.save(settings);
        } else {
            dao.update(settings);
        }

    }

}
