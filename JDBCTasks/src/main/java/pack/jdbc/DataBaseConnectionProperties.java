package pack.jdbc;

import java.util.Properties;

public class DataBaseConnectionProperties {
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/autosdb";
    public static Properties getConnectionProps() {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");
        return props;
    }
}
