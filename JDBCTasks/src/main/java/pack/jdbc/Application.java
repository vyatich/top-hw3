package pack.jdbc;

import java.sql.*;
import java.util.*;

import static pack.jdbc.DataBaseConnectionProperties.DATABASE_URL;

public class Application {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DataBaseConnectionProperties.getConnectionProps())) {
            System.out.println("Соединение с СУБД выполнено.");

            List<Car> carListFromDataBase = getCarListFromDataBase(connection);
            System.out.println("Отображение всего содержимого таблицы с автомобилями:");
            System.out.println(carListFromDataBase + "\n");

            String nameOfVolume = "nameOfCarManufacturer";
            String listOfCarManufacturer = getAllManufacturersCars(connection, nameOfVolume);
            System.out.println("Отображение всех производителей автомобилей:");
            System.out.println(listOfCarManufacturer + "\n");

            Map<String, Integer> listOfCarAndCountManufacturers = getNameAndCountManufacturers(connection);
            System.out.println("Отображение названия производителей и количество автомобилей каждого производителя:");
            System.out.println(listOfCarAndCountManufacturers + "\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Отключение от СУБД выполнено.");
    }

    private static List<Car> getCarListFromDataBase(Connection connection) {
        String getQuery = "SELECT * FROM autos";
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(getQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getFloat(4),
                        resultSet.getDate(5).toLocalDate(),
                        resultSet.getString(6),
                        resultSet.getString(7));
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    private static String getAllManufacturersCars(Connection connection, String value) {
        String getQuery = "SELECT DISTINCT " + value + " FROM autos";
        List<String> manufacturers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(getQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                manufacturers.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return manufacturers.toString();
    }

    private static Map<String, Integer> getNameAndCountManufacturers(Connection connection) {
        String getQuery =
                "SELECT nameOfCarManufacturer, COUNT(nameOfCarManufacturer) FROM autos GROUP BY nameOfCarManufacturer";
        Map<String, Integer> cars = new LinkedHashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(getQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.put(resultSet.getString(1),
                        resultSet.getInt(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }
}