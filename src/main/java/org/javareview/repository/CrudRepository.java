package org.javareview.repository;

import java.sql.SQLException;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    <S extends T> S save(S entity);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    Optional<T> findById(ID id) throws SQLException;

    boolean existsById(ID id);

    Iterable<T> findAll() throws SQLException;

    Iterable<T> findAllById(Iterable<ID> ids) throws SQLException;

    void deleteById(ID id);

    void delete(T entity);

    void deleteAllById(Iterable<? extends ID> ids);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();
}
