import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CarsDAO {

    private static final Logger logger = Logger.getLogger(CarsDAO.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
    private static final String title = "Print command\n" +
            "/all - to show all cars\n" +
            "/allBrands - to show all brands\n" +
            "/count - to show counts cars of all brands\n" +
            "/max - to show brand with the max amount cars\n" +
            "/min - to show brand with the min amount cars\n" +
            "/brand - to show all cars of a specific manufacturer\n" +
            "/color - to show all cars with spec color\n" +
            "/volume - to show all cars with spec volume\n" +
            "/type - to show all cars with spec type\n" +
            "/close - to close connection";

    public void startApplication() {
        try (Connection connection = DriverManager.getConnection(DataConnectionProperties.DATABASE_URL,
                DataConnectionProperties.getProperties())) {
            System.out.println("Connection successful");
            System.out.println(title);
            while (!connection.isClosed()) {
                switch (scanner.nextLine()) {
                    case "/all" -> System.out.println(getQueryResult(connection));
                    case "/allBrands" -> System.out.println(getQueryResult(connection, "brand"));
                    case "/max" -> System.out.println(getQueryResultLimit(connection, "max"));
                    case "/min" -> System.out.println(getQueryResultLimit(connection, "min"));
                    case "/brand" -> {
                        System.out.println("Write a brand name");
                        String brandName = scanner.nextLine();
                        System.out.println(getQueryResult(connection, "brand", brandName));
                    }
                    case "/color" -> {
                        System.out.println("Write the color");
                        String color = scanner.nextLine();
                        System.out.println(getQueryResult(connection, "color", color));
                    }
                    case "/volume" -> {
                        System.out.println("Write the volume");
                        double volume = scanner.nextDouble();
                        System.out.println(getQueryResult(connection, "engine_volume",
                                volume));
                    }
                    case "/type" -> {
                        System.out.println("Write the type");
                        String type = scanner.nextLine();
                        System.out.println(getQueryResult(connection, "type", type));
                    }
                    case "/delete" -> {
                        System.out.println("Enter id row for delete");
                        int id = scanner.nextInt();
                        deleteRowById(connection, id);
                    }
                    case "/close" -> connection.close();
                    default -> System.out.println("Write correct request");
                }
                System.out.println(title);
            }
        } catch (SQLException e) {
            logger.info(String.format("SQL Exception, look data settings ---> %s", e));
        }
    }

    private static String getQueryResult(Connection connection, String columnName, Object value) {
        String query = "SELECT * FROM cars WHERE " + columnName + " = ?";
        List<CarsData> carsDataList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CarsData carsData = new CarsData(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDouble(4),
                        resultSet.getInt(5), resultSet.getString(6),
                        resultSet.getString(7));
                carsDataList.add(carsData);
            }
        } catch (SQLException e) {
            logger.info(String.format("SQL Exception, look data settings ---> %s", e));
        }
        return ViewTable.listToTable(carsDataList);
    }

    private static String getQueryResult(Connection connection) {
        String query = "SELECT * FROM cars";
        List<CarsData> carsDataList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                CarsData carsData = new CarsData(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDouble(4),
                        resultSet.getInt(5), resultSet.getString(6),
                        resultSet.getString(7));
                carsDataList.add(carsData);
            }
        } catch (SQLException e) {
            logger.info(String.format("SQL Exception, look data settings ---> %s", e));
        }
        return ViewTable.listToTable(carsDataList);
    }

    private static String getQueryResult(Connection connection, String value) {
        String query = "SELECT DISTINCT " + value + " FROM cars";
        List<String> carsDataList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                carsDataList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            logger.info(String.format("SQL Exception, look data settings ---> %s", e));
        }
        return carsDataList.toString();
    }

    private static String getQueryResultLimit(Connection connection, String value) {
        StringBuilder lim = new StringBuilder();
        if (value.equalsIgnoreCase("max")) {
            lim.append("DESC");
        } else if (value.equalsIgnoreCase("min")) {
            lim.append("ASC");
        }
        String result = lim.toString();
        String query = "SELECT brand, COUNT(brand) FROM cars " +
                "GROUP BY brand\n" +
                "ORDER BY COUNT(brand) " + result + " limit 1";
        HashMap<String, Integer> values = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                values.put(resultSet.getString(1), resultSet.getInt(2));
            }
        } catch (SQLException e) {
            logger.info(String.format("SQL Exception, look data settings ---> %s", e));
        }
        return values.toString();
    }

    private static void deleteRowById(Connection connection ,int value) {
        String query = "DELETE FROM cars WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, value);
            preparedStatement.executeQuery();
            System.out.println("Success delete id " + value + " from table");
        } catch (SQLException e) {
            logger.info(String.format("SQL Exception, look data settings ---> %s", e));
        }
    }
}
