package kamienica.dao;

import org.springframework.stereotype.Repository;

import kamienica.model.Apartment;

@Repository("apatmentDao")
public class ApartmentDaoImpl extends AbstractDao<Integer, Apartment> implements DaoInterface<Apartment> {

}
