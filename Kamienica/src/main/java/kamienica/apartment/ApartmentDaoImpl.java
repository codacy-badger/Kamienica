package kamienica.apartment;

import org.springframework.stereotype.Repository;

import kamienica.dao.AbstractDao;
import kamienica.dao.DaoInterface;

@Repository("apatmentDao")
public class ApartmentDaoImpl extends AbstractDao<Integer, Apartment> implements DaoInterface<Apartment> {

}
