import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        Queries queries = new Queries("jdbc:postgresql://localhost:5432/Cars", "postgres",  "1234");
        queries.getAllData();
        queries.getManufactures();
        queries.getManufacturesAndCount();
    }
}
