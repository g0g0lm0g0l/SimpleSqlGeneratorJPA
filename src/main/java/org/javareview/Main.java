package org.javareview;

import org.javareview.repository.PersonRepository;
import org.javareview.service.PersonRepositoryImpl;

import java.sql.*;
import java.util.List;

/*
    1. create methods CRUD (maybe only with finds with logical operators?)
    2. check if the methods exist
    3. generate sql by its name
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        //TODO: delete the code in curly braces, its for testing
        {
            String url = "jdbc:h2:~/test";
            String user = "sa";
            String password = "";

            Connection connection = null;

            String deleteSQL = "DELETE FROM PERSONTABLE";
            String createTableSQL = "CREATE TABLE IF NOT EXISTS PERSONTABLE (" + "id INT PRIMARY KEY, name VARCHAR(255))";
            String insertSQL = "INSERT INTO PERSONTABLE (id, name) VALUES (?, ?)";
            String querySQL = "SELECT * FROM PERSONTABLE WHERE name = ?";

            try {
                connection = DriverManager.getConnection(url, user, password);
                PreparedStatement createTablePreparedStatement = connection.prepareStatement(createTableSQL);
                PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
                PreparedStatement queryStatement = connection.prepareStatement(querySQL);
                PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
                createTablePreparedStatement.execute();
                System.out.printf("[INFO]: %s\n", "create");

                deleteStatement.execute();
                System.out.printf("[INFO]: %s\n", "delete");

                insertStatement.setInt(1, 1);
                insertStatement.setString(2, "Ivan");
                insertStatement.execute();
                System.out.printf("[INFO]: %s\n", "insert");

                queryStatement.setString(1, "Ivan");
                ResultSet resultSet = queryStatement.executeQuery();
                System.out.printf("[INFO]: %s\n", "select");

                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    System.out.printf("[INFO]: id ==> %d, name ==> %s\n", id, name);
                }

                PersonRepository personRep = new PersonRepositoryImpl(connection);
                //personRep.save(new Person());

                /* start */
                personRep.findAll();
                personRep.findById(1L);
                personRep.findAllById(List.of(1L));
                personRep.findAllByIdAndName(1L, "Ivan");
                personRep.findAllByIdOrName(1L, "Ivan");
                personRep.findAllByIdOrName(0L, "Ivan");
                personRep.findAllByIdOrName(1L, "null");
                /* fin */
            } catch (SQLException e) {
                System.err.printf("[ERROR]: %s\n", e.getMessage());
            } finally {
                assert connection != null;
                connection.close();
                System.out.print("[INFO]: The connection is closed\n");
            }
            System.out.print("[INFO]: END");
        }
    }

}