//package kamienica.dao;
//
//import org.hibernate.Query;
//import org.springframework.stereotype.Repository;
//
//import kamienica.model.MyUser;
//import kamienica.model.Tenant;
//
//@Repository("userDao")
//public class UserDAOImpl extends Tena implements UserDAO {
//
//	@Override
//	public Tenant getTenant(String mail) {
//		Query query = getSession().createSQLQuery("select * from tenant where email=:email").addEntity(Tenant.class);
//		query.setString("email", mail);
//		return (Tenant) query.uniqueResult();
//	}
//
////	@Override
////	public Apartment getApartment(String mail) {
////		Query query = getSession().createSQLQuery("select * from apartment where email=:email").addEntity(Apartment.class);
////		query.setString("email", mail);
////		return (Apartment) query.uniqueResult();
////	}
////
////	@Override
////	public List<MeterEnergy> getEnergy(String mail) {
////		Query query = getSession().createSQLQuery("select * from tenant where email=:email").addEntity(Tenant.class);
////		query.setString("email", mail);
////		return (Tenant) query.uniqueResult();
////	}
////
////	@Override
////	public List<MeterWater> getWater(String mail) {
////		Query query = getSession().createSQLQuery("select * from tenant where email=:email").addEntity(Tenant.class);
////		query.setString("email", mail);
////		return (Tenant) query.uniqueResult();
////	}
////
////	@Override
////	public List<MeterGas> getGas(String mail) {
////		Query query = getSession().createSQLQuery("select * from tenant where email=:email").addEntity(Tenant.class);
////		query.setString("email", mail);
////		return (Tenant) query.uniqueResult();
////	}
//
//}
