package edu.pushnoe.student.dao;

import edu.pushnoe.student.domain.Street;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DirectoryDao {

    public List<Street> findSreet(String pattern) throws Exception {

       List<Street> result = new LinkedList<>();

       Class.forName("org.postgresql.Driver");
       Connection connection = ConnectDb.getConntect();

        Statement stmt = connection.createStatement();
        String sql = "SELECT street_code, street_name FROM jc_street " +
                "WHERE UPPER(street_name) LIKE UPPER('%"+pattern+"%')";

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()){
            System.out.println(rs.getString("street_name"));
          Street street = new Street( rs.getLong("street_code"), rs.getString("street_name"));
          result.add(street);
        }


        return result;
    }

}
