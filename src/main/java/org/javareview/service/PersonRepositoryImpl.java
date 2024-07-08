package org.javareview.service;

import org.javareview.generator.Generator;
import org.javareview.model.Person;
import org.javareview.repository.PersonRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepository {

    private Connection connection;

    public PersonRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <S extends Person> S save(S entity) {
        // String sqlStatement = Generator.generate(connection);

        return entity;
    }

    @Override
    public <S extends Person> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Person> findById(Long aLong) throws SQLException {
        String sqlStatement = Generator.generate(connection);
        PreparedStatement sqlPreparedStatement = connection.prepareStatement(sqlStatement);
        sqlPreparedStatement.setString(1, String.valueOf(aLong));
        ResultSet resultSet = sqlPreparedStatement.executeQuery();
        Optional<Person> optionalPerson = Optional.empty();
        while (resultSet.next()) {
            long personId = resultSet.getLong(1);
            String personName = resultSet.getString(2);
            System.out.printf("[INFO]: id ==> %d, name ==> %s\n", personId, personName);
            optionalPerson = Optional.of(new Person(personId, personName));
        }
        System.out.println("The sql statement in " + PersonRepositoryImpl.class.getName() + ": " + sqlStatement);
        return optionalPerson;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Person> findAll() throws SQLException {
        String sqlStatement = Generator.generate(connection);
        PreparedStatement sqlPreparedStatement = connection.prepareStatement(sqlStatement);
        ResultSet resultSet = sqlPreparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();
        while (resultSet.next()) {
            long personId = resultSet.getLong(1);
            String personName = resultSet.getString(2);
            System.out.printf("[INFO]: id ==> %d, name ==> %s\n", personId, personName);
            personList.add(new Person(personId, personName));
        }
        System.out.println("The sql statement in " + PersonRepositoryImpl.class.getName() + ": " + sqlStatement);
        return personList;
    }

    @Override
    public Iterable<Person> findAllById(Iterable<Long> longs) throws SQLException {
        String sqlStatement = Generator.generate(connection);
        PreparedStatement sqlPreparedStatement = connection.prepareStatement(sqlStatement);
        List<Person> personList = new ArrayList<>();
        for (Long aLong : longs) {
            sqlPreparedStatement.setString(1, String.valueOf(aLong));
            ResultSet resultSet = sqlPreparedStatement.executeQuery();
            while (resultSet.next()) {
                long personId = resultSet.getLong(1);
                String personName = resultSet.getString(2);
                System.out.printf("[INFO]: id ==> %d, name ==> %s\n", personId, personName);
                personList.add(new Person(personId, personName));
            }
            System.out.println("The sql statement in " + PersonRepositoryImpl.class.getName() + ": " + sqlStatement);
        }
        return personList;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Person entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Person> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Iterable<Person> findAllByIdAndName(Long id, String name) throws SQLException {
        String sqlStatement = Generator.generate(connection);
        PreparedStatement sqlPreparedStatement = connection.prepareStatement(sqlStatement);
        sqlPreparedStatement.setLong(1, id);
        sqlPreparedStatement.setString(2, name);
        ResultSet resultSet = sqlPreparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();
        while (resultSet.next()) {
            long personId = resultSet.getLong(1);
            String personName = resultSet.getString(2);
            System.out.printf("[INFO]: id ==> %d, name ==> %s\n", personId, personName);
            personList.add(new Person(personId, personName));
        }
        System.out.println("The sql statement in " + PersonRepositoryImpl.class.getName() + ": " + sqlStatement);
        return personList;
    }

    @Override
    public Iterable<Person> findAllByIdOrName(Long id, String name) throws SQLException {
        String sqlStatement = Generator.generate(connection);
        PreparedStatement sqlPreparedStatement = connection.prepareStatement(sqlStatement);
        sqlPreparedStatement.setLong(1, id);
        sqlPreparedStatement.setString(2, name);
        ResultSet resultSet = sqlPreparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();
        while (resultSet.next()) {
            long personId = resultSet.getLong(1);
            String personName = resultSet.getString(2);
            System.out.printf("[INFO]: id ==> %d, name ==> %s\n", personId, personName);
            personList.add(new Person(personId, personName));
        }
        System.out.println("The sql statement in " + PersonRepositoryImpl.class.getName() + ": " + sqlStatement);
        return personList;
    }
}
