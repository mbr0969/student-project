package edu.pushnoe.student.config;

import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

public class Config {

    public static final String DB_URL = "dburl";
    public static final String DB_USER = "user";
    public static final String DB_PASSWORD = "password";
    public static final String DB_LIMIT = "limit";

    private static Properties properties = new Properties();

    public synchronized  static String getProperty(String name){
        if (properties.isEmpty()){
            try(InputStream is = Config.class.getClassLoader()
                .getResourceAsStream("dbconfig.properties")) {



              //  System.out.println(convertStreamToString(is));

                properties.load(is);

            }catch (Exception ex){
                ex.printStackTrace();
                throw  new RuntimeException(ex);
            }
        }

        return properties.getProperty(name);

    }


    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
