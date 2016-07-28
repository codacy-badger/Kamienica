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
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import kamienica.configuration.HibernateTestConfiguration;
import kamienica.testsetup.HsqlDataTypeFactory;

@ContextConfiguration(classes = { HibernateTestConfiguration.class })
public abstract class EntityDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	DataSource dataSource;

	@BeforeClass
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
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Tenant.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("MeterEnergy.xml")),
				//new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Meters.xml")),
				
				// new
				// FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("ReadingWater.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("ReadingEnergy.xml")),
				// new
				// FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("ReadingGas.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Incoices.xml"))
				 };

		return new CompositeDataSet(datasets);

	}
	
	
	public DataSource gatDataSource() {
		
		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
			.setType(EmbeddedDatabaseType.H2) //.H2 or .DERBY
		//	.addScript("create-db.sql")
			.addScript("import.sql")
			.build();
		System.out.println("-----------------------------------------dataSource-------------------");
		return db;
	}
	
}