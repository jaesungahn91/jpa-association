package persistence.sql.dml.builder;

import persistence.sql.QueryBuilder;
import persistence.sql.ddl.Column;
import persistence.sql.ddl.Columns;
import persistence.sql.ddl.Table;

import java.util.List;
import java.util.stream.Collectors;

public class SelectQueryBuilder implements QueryBuilder {

    private static final String SELECT_QUERY = "SELECT %s FROM %s%s;";

    private final Table table;
    private final Columns columns;
    private final WhereQueryBuilder whereQueryBuilder;

    public SelectQueryBuilder(Class<?> clazz, List<String> whereColumnNames, List<Object> whereValues) {
        this.table = new Table(clazz);
        this.columns = new Columns(clazz);
        this.whereQueryBuilder = new WhereQueryBuilder(clazz, whereColumnNames, whereValues);
    }

    public SelectQueryBuilder(Class<?> clazz, Object id) {
        this.table = new Table(clazz);
        this.columns = new Columns(clazz);
        this.whereQueryBuilder = new WhereQueryBuilder(columns.getPrimaryKeyColumn(), id);
    }

    @Override
    public String build() {
        return String.format(
                SELECT_QUERY,
                generateColumns(),
                table.getName(),
                whereQueryBuilder.build()
        );
    }

    private String generateColumns() {
        return columns.getColumns().stream()
                .map(Column::getName)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.joining(COMMA));
    }
}
