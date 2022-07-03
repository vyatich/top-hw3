package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static pack.DBConnection.DATABASE_URL;

public class DBCreate {

    public void createDB() {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DBConnection.getConnection())) {
            String createDB = "CREATE DATABASE if NOT EXISTS carsdb;";
            PreparedStatement preparedStatement = connection.prepareStatement(createDB);
            preparedStatement.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (Connection connection1 = DriverManager.getConnection(DATABASE_URL + "/carsdb", DBConnection.getConnection())){
            String dropTable = "DROP TABLE if EXISTS cars;";
            PreparedStatement preparedStatement = connection1.prepareStatement(dropTable);
            preparedStatement.executeUpdate();

            String createTable = "CREATE TABLE if NOT EXISTS cars (id INT PRIMARY KEY AUTO_INCREMENT, brand VARCHAR(15)," +
                    "model VARCHAR(20), engine_volume DOUBLE, year YEAR, color VARCHAR(20), type VARCHAR (20));";
            preparedStatement = connection1.prepareStatement(createTable);
            preparedStatement.executeUpdate();

            String insertTable = "INSERT INTO cars (brand, model, engine_volume, year, color, type) VALUES" +
                    "('toyota', 'rav4', 2, 2019, 'blue', 'SUV')," +
                    "('ford', 'focus', 1.8, 2008, 'orange', 'sedan')," +
                    "('BMW', 'm3', 3.2, 2015, 'black', 'wagon')," +
                    "('ZAZ', 'ushastiy', 0.7, 1967, 'white', 'hatchback')," +
                    "('ZAZ', 'ushastiy', 0.7, 1967, 'white', 'hatchback');";
            preparedStatement = connection1.prepareStatement(insertTable);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
