package pack;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");

        String selectProcreators = "SELECT DISTINCT procreator FROM public.cars";
        String selectProcsAmount = "SELECT procreator, count(procreator) AS amount FROM cars\n" +
                "group by procreator";

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/autodb", props)) {

            //Отображение содержимого таблицы БД
            System.out.println(getCarsFromDB(connection));
            System.out.println();

            //Вывод всех производителей
            getColumnFromTable(connection, selectProcreators);
            System.out.println();

            //Вывод производителей и количества их автомобилей
            getProcsAmount(connection, selectProcsAmount);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertNewCar(Connection connection,
                                     String procreator,
                                     String model,
                                     String engine_volume,
                                     String release_year,
                                     String color,
                                     String bodytype) {
        String insert = "INSERT INTO public.cars(procreator, model, engine_volume, release_year, color, bodytype) " +
                "VALUES (?, ?, ?, ? , ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insert);
        ) {
            statement.setObject(1, procreator);
            statement.setObject(2, model);
            statement.setObject(3, engine_volume);
            statement.setObject(4, release_year);
            statement.setObject(5, color);
            statement.setObject(6, bodytype);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Car> getCarsFromDB(Connection connection) {
        String getQuery = "SELECT * FROM public.cars ORDER BY id";
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(getQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(
                        resultSet.getInt(7),
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    private static void getColumnFromTable(Connection connection, String select){
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getProcsAmount(Connection connection, String select){
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%s %s \n", resultSet.getString(1), resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
