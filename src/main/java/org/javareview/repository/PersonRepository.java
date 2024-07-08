package org.javareview.repository;

import org.javareview.model.Person;

import java.sql.SQLException;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Iterable<Person> findAllByIdAndName(Long id, String name) throws SQLException;

    Iterable<Person> findAllByIdOrName(Long id, String name) throws SQLException;
}
