package kamienica.feature.division;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DivisionServiceImpl implements DivisionService {
	@Autowired
	DivisionDao divisionDAO;

	@Override
	public List<Division> getList() {
		return divisionDAO.getList();
	}

	@Override
	public void deleteByID(int id) {
		divisionDAO.deleteById(id);

	}

	@Override
	public void update(Division division) {
		divisionDAO.update(division);
	}

	@Override
	public void deleteAll() {
		divisionDAO.deleteAll();

	}

	@Override
	public void saveList(List<Division> division) {
		divisionDAO.deleteAll();
		for (Division div : division) {
			divisionDAO.save(div);
		}


	}

}
