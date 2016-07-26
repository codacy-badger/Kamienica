package kamienica.dao;

import java.util.Arrays;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import kamienica.configuration.HibernateTestConfiguration;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
public abstract class EntityDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	DataSource dataSource;

	@BeforeClass
	public void setUp() throws Exception {
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
		DatabaseConfig config = dbConn.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
	}

	// protected abstract IDataSet getDataSet() throws Exception;

	protected IDataSet getDataSet() throws Exception {
		IDataSet[] datasets = new IDataSet[] {
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Apartment.xml")),
//				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Meters.xml")),
//				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("ReadingWater.xml")),
//				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("ReadingEnergy.xml")),
//				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("ReadingGas.xml")),
//				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Incoices.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Tenant.xml")) };
		System.out.println(Arrays.toString(datasets));
		return new CompositeDataSet(datasets);
	
	}
}