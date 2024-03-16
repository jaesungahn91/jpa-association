package persistence.sql.ddl.dialect;

import persistence.sql.ddl.Column;
import persistence.sql.ddl.Type;

public interface Dialect {

    String getTypeString(Type type);

    String getPrimaryKeyString(Column column);

    String getGenerationTypeString(Column column);

    String getConstraintString(Column column);

}
