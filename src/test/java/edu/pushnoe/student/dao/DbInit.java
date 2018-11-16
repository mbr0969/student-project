package edu.pushnoe.student.dao;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DbInit {

    public static void startUp() throws Exception{
        URL url = DictionaryDaoImplTest.class.getClassLoader().getResource("student_order.sql");
        URL urlData = DictionaryDaoImplTest.class.getClassLoader().getResource("student_data.sql");

        List<String> str = Files.readAllLines(Paths.get(url.toURI()));
        List<String> strData = Files.readAllLines(Paths.get(urlData.toURI()));

        String sql = str.stream().collect(Collectors.joining());
        String sqlData = strData.stream().collect(Collectors.joining());

        try (Connection connection = ConnectDb.getConnections();
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(sql);
            stmt.executeUpdate(sqlData);

        }
    }
}
