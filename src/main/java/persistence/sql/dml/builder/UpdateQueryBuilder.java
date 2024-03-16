package persistence.sql.dml.builder;

import persistence.sql.QueryBuilder;
import persistence.sql.ddl.Columns;
import persistence.sql.ddl.Table;
import persistence.sql.dml.Value;
import persistence.sql.dml.Values;

import java.util.stream.Collectors;

public class UpdateQueryBuilder implements QueryBuilder {

    private static final String UPDATE_QUERY = "UPDATE %s SET %s%s;";

    private final Table table;
    private final Values values;
    private final WhereQueryBuilder whereQueryBuilder;

    public UpdateQueryBuilder(Object entity) {
        Class<?> clazz = entity.getClass();
        this.table = new Table(clazz);
        this.values = new Values(new Columns(clazz), entity);
        this.whereQueryBuilder = new WhereQueryBuilder(entity);
    }

    @Override
    public String build() {
        return String.format(
                UPDATE_QUERY,
                table.getName(),
                generateSetValues(),
                whereQueryBuilder.build()
        );
    }

    private String generateSetValues() {
        return values.getValues().stream()
                .filter(Value::isNotPrimaryKeyValue)
                .map(value -> value.getColumnName() + " = " + value.getValue())
                .collect(Collectors.joining(COMMA));
    }

}
