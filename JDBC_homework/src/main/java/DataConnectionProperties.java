import java.util.Properties;

public class DataConnectionProperties {

    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC";

    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("user", "goodrich");
        properties.setProperty("password", "goodrich");
        return properties;
    }
}
