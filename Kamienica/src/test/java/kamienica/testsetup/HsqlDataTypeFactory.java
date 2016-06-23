package kamienica.testsetup;

import java.sql.Types;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
 
public class HsqlDataTypeFactory
  extends DefaultDataTypeFactory
{
//  private static final Log log = LogFactory.getLog(HsqlDataTypeFactory.class);
 
  @Override
public DataType createDataType(int sqlType, String sqlTypeName)
    throws DataTypeException
  {
    if (sqlType == Types.BOOLEAN)
    {
      return DataType.BOOLEAN;
    }
 
    return super.createDataType(sqlType, sqlTypeName);
  }
}