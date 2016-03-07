package kamienica.dao;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import kamienica.configuration.HibernateTestConfiguration;
import kamienica.testsetup.HsqlDataTypeFactory;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
public abstract class EntityDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	DataSource dataSource;

	@BeforeMethod
	public void setUp() throws Exception {
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
		DatabaseConfig config = dbConn.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqlDataTypeFactory());
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
	}

	// protected abstract IDataSet getDataSet() throws Exception;

	protected IDataSet getDataSet() throws Exception {
		IDataSet[] datasets = new IDataSet[] {
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Apartment.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("MeterEnergy.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("MeterWater.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("MeterGas.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("ReadingEnergy.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Tenant.xml"))};

		return new CompositeDataSet(datasets);
	}
}