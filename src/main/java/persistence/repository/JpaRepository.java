package persistence.repository;

public interface JpaRepository<T, ID> {

    T save(T entity);

}
