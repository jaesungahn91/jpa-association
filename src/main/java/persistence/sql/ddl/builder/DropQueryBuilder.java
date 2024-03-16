package persistence.sql.ddl.builder;

import persistence.sql.QueryBuilder;
import persistence.sql.ddl.Table;

public class DropQueryBuilder implements QueryBuilder {

    private static final String DROP_TABLE_QUERY = "DROP TABLE %s;";

    private final Table table;

    public DropQueryBuilder(Class<?> clazz) {
        this.table = new Table(clazz);
    }

    @Override
    public String build() {
        return String.format(DROP_TABLE_QUERY, table.getName());
    }

}
