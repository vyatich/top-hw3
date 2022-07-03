package pack;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandHandler {

    public static void getCarListFromDB(Connection connection) {
        String getQuery = "SELECT * FROM cars";
        List<Car> carListFromDB = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(getQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
                carListFromDB.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(carListFromDB);
        System.out.println();
    }

    public static void getCarBrandFromDB(Connection connection, String columnName){
        String getQuery = "SELECT DISTINCT brand FROM cars";
        List<String> carBrandsFromDB = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(getQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String brand = resultSet.getString(columnName);
                carBrandsFromDB.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(carBrandsFromDB);
        System.out.println();
    }

    public static void getCountCarFromBrandFromDB(Connection connection){
        String getQuery = "SELECT brand, COUNT(brand) FROM cars GROUP BY brand";
        HashMap<String, Integer> values = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(getQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                values.put(resultSet.getString(1), resultSet.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(values);
        System.out.println();
    }
}
