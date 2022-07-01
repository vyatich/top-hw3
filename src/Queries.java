import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Queries {
    private final String CONNECTION;
    public final Properties properties = new Properties();

    public Queries(String CONNECTION, String user, String password) throws SQLException {
        this.CONNECTION = CONNECTION;
        properties.setProperty("user", user);
        properties.setProperty("password", password);
    }

    public void getAllData() {
        try (Connection connection = DriverManager.getConnection(CONNECTION, properties)) {
            String getAll = "SELECT * FROM cars";
            PreparedStatement pr = connection.prepareStatement(getAll);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Car carResult1 = new Car(resultSet.getInt(1), resultSet.getString(2) + " ",
                        resultSet.getString(3) + " ", resultSet.getDouble(4),
                        resultSet.getInt(5), resultSet.getString(6) + " ",
                        resultSet.getString(7));
                System.out.println(getResults(carResult1));
            }
            System.out.println("------------------------------------");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void getManufactures() {
        try (Connection connection = DriverManager.getConnection(CONNECTION, properties)) {
            String getManufacture = "SELECT DISTINCT manufacture FROM cars";
            PreparedStatement pr2 = connection.prepareStatement(getManufacture);
            ResultSet resultSet1 = pr2.executeQuery();
            while (resultSet1.next()) {
                System.out.println(resultSet1.getString(1));
            }
            System.out.println("------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getManufacturesAndCount() {
        try (Connection connection = DriverManager.getConnection(CONNECTION, properties)) {
            String getManufactureCount = "SELECT manufacture, COUNT(manufacture) FROM cars GROUP BY manufacture";
            PreparedStatement pr3 = connection.prepareStatement(getManufactureCount);
            ResultSet resultSet3 = pr3.executeQuery();
            while (resultSet3.next()) {
                System.out.println(resultSet3.getString(1));
                System.out.println(resultSet3.getInt(2));
            }
            System.out.println("------------------------------------");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Car> getResults(Car car) {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(car);
        return cars;
    }
}

