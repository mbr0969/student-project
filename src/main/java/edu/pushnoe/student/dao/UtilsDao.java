package edu.pushnoe.student.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class UtilsDao {


    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {

        if(resultSet != null) {
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }

        if (connection !=null){
            connection.close();
        }
    }

    public static void close(Statement statement, ResultSet resultSet) throws SQLException {

        close(null, statement,resultSet);

    }

    public static void close(Statement statement) throws SQLException {

        close(null, statement, null);

    }

    public static void close(Connection connection) throws SQLException {

        close(connection, null, null);

    }
}
