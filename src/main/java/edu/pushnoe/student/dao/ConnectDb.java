package edu.pushnoe.student.dao;

import edu.pushnoe.student.config.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectDb {

    private static ConnectDb _instance =null;
    private static Connection connection = null;
    private static File file = new File("dbconfig.properties");
    private static Properties prop = new Properties();
    //private Connection connection = null;

    /**
     *
     */
    private ConnectDb() {
        this.connection = getConnection(getFile(), getProp(), getConnection());
    }

    /**
     * @return
     */
    public static Connection getInstance(){
        if(getConnection() == null){
            System.out.println("Открываем новое соединение");
            connection = getConnection(getFile(), getProp(), getConnection());
        }
        return connection;
    }


    /**
     * @param file
     * @param prop
     * @param connection
     * @return
     */
    private static Connection getConnection(File file, Properties prop, Connection connection) {

        if (file.exists()) {
            try (FileInputStream stream = new FileInputStream(file.getName())) {
                prop.load(stream);
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                String dburl = prop.getProperty("dburl");
                try {
                    connection = DriverManager.getConnection(dburl, user, password);
                    return connection;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    public static Connection getConnections() throws SQLException {
       // Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(Config.getProperty(Config.DB_URL),
                    Config.getProperty(Config.DB_USER),Config.getProperty(Config.DB_PASSWORD));
        return connection;

    }



//============ GETTER AND SETTER ===========

    private static File getFile() {
        return file;
    }

    private static Properties getProp() {
        return prop;
    }

    private void setProp(Properties prop) {
        this.prop = prop;
    }

    private static Connection getConnection() {
        return connection;
    }

}
