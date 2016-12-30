package kamienica.feature.history;

import kamienica.model.History;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface HistoryService {

    void save(History history);

    void delete(Long id);

    List<History> getList();

    List<History> getByCriteria(Criterion... criterions);


}
