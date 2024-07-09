package org.javareview.generator.utils;

import java.sql.Connection;

public class SQLCreator {

    private Connection connection;

    //Dependency injection
    public SQLCreator(Connection connection) {
        this.connection = connection;
    }

    public String createSql(String methodName) {
        /*
            priorities: findAll, findById, findAllByIdAndName, findAllByIdOrName
         */
        String sqlStatement = "";
        if (methodName.startsWith("find")) {
            sqlStatement = getFind(methodName);
        }
        return sqlStatement;
    }

    //TODO: change me please
    private String getFind(String find) {
        String sql = "SELECT * FROM PERSONTABLE ";
        if (find.equalsIgnoreCase("findAll")) {
            sql = sql;
        } else if (find.equalsIgnoreCase("findById") ||
                find.equalsIgnoreCase("findAllById")
        ) {
            sql = sql + "WHERE id = ?";
        } else if (find.equalsIgnoreCase("findAllByIdAndName")) {
            sql = sql + "WHERE id = ? AND name = ?";
        } else if (find.equalsIgnoreCase("findAllByIdOrName")) {
            sql = sql + "WHERE id = ? OR name = ?";
        } else {
            System.out.println("[ERROR]: The find method not found " + SQLCreator.class.getName());
            System.exit(1);
        }
        return sql;
    }
}
