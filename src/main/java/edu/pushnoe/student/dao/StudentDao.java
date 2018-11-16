package edu.pushnoe.student.dao;

import java.sql.*;


public class StudentDao  {

    private Connection connection;

    public StudentDao() throws Exception{

       connection =  ConnectDb.getConnections();

    }

    public void updateStreet(Integer street_code, String street_name) throws SQLException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {


            String select = "SELECT street_code FROM jc_street WHERE street_code=?";
            statement = connection.prepareStatement(select);
            statement.setInt(1, street_code);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                    statement =null;
                    statement = connection.prepareStatement("INSERT INTO jc_street (street_code, street_name) VALUES (?,?)");
                    statement.setInt(1, street_code);
                    statement.setString(2, street_name);
                    statement.execute();
                    System.out.println("Данные добавлены");


                } else {
                    System.out.println("Такая улица уже существует");
                }


            }finally{

                UtilsDao.close(statement, resultSet);

            }
    }

    public void selectAllStreet() throws SQLException {


        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();

        System.out.println(stmt.toString());

        rs = stmt.executeQuery("SELECT * FROM jc_street");

        while (rs.next()){
            System.out.println(rs.getLong(1) +" : " + rs.getString(2));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{

            UtilsDao.close(stmt, rs);

        }
    }

    }

