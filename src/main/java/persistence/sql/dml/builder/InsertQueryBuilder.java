package persistence.sql.dml.builder;

import persistence.sql.QueryBuilder;
import persistence.sql.ddl.Column;
import persistence.sql.ddl.Columns;
import persistence.sql.ddl.Table;
import persistence.sql.dml.Value;
import persistence.sql.dml.Values;

import java.util.stream.Collectors;

public class InsertQueryBuilder implements QueryBuilder {

    private static final String INSERT_QUERY = "INSERT INTO %s (%s) VALUES (%s);";

    private final Columns columns;
    private final Table table;
    private final Values values;

    public InsertQueryBuilder(Object entity) {
        Class<?> clazz = entity.getClass();
        this.table = new Table(clazz);
        this.columns = new Columns(clazz);
        this.values = new Values(columns, entity);
    }

    @Override
    public String build() {
        return String.format(
                INSERT_QUERY,
                table.getName(),
                generateColumns(),
                generateValues()
        );
    }

    private String generateColumns() {
        return columns.getColumns().stream()
                .filter(Column::isNotAutoIncrementId)
                .map(Column::getName)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.joining(COMMA));
    }

    private String generateValues() {
        return values.getValues().stream()
                .filter(Value::isNotAutoIncrementId)
                .map(Value::getValue)
                .filter(value -> !value.isEmpty())
                .collect(Collectors.joining(COMMA));
    }
}
