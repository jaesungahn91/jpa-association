package persistence.repository;

import persistence.entity.EntityManager;
import persistence.sql.ddl.Column;
import persistence.sql.ddl.Columns;

public class CustomJpaRepository<T, ID> implements JpaRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T save(T entity) {
        if (isNew(entity)) {
            return entityManager.persist(entity);
        }
        return entityManager.merge(entity);
    }

    private boolean isNew(T entity) {
        Columns columns = new Columns(entity.getClass());
        Column primaryKeyColumn = columns.getPrimaryKeyColumn();

        Object id = columns.getPKOriginValue(entity);
        Class<?> idType = primaryKeyColumn.getField().getType();

        if (!idType.isPrimitive()) {
            return id == null;
        }
        if (id instanceof Number) {
            return ((Number) id).longValue() == 0L;
        }

        throw new IllegalArgumentException(String.format("Unsupported primitive id type %s", idType));
    }

}