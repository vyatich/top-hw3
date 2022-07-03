package pack;

import java.util.Properties;

public class DBConnection {
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306";

    public static Properties getConnection(){
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        return properties;
    }
}
