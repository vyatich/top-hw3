package cars;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class ConnectionToDBAndGetQuery extends Query {
    private final String PATHCONNECTION;
    static Properties connectionValues = new Properties();

    List<Object> resultList = new ArrayList<>();
    int count = 0;
    Scanner scan = new Scanner(System.in);

    public ConnectionToDBAndGetQuery(String PATHCONNECTION, String userValue, String passwordValue) {
        this.PATHCONNECTION = PATHCONNECTION;
        String user = "user";
        connectionValues.setProperty(user, userValue);
        String password = "password";
        connectionValues.setProperty(password, passwordValue);
    }

    //Разделил по блокам и выделил по методам, как в задании
    protected void getValuesFromDB_Part1() {
        try (Connection connection = DriverManager.getConnection(PATHCONNECTION, connectionValues)) {
            System.out.println("""
                    Choose a selection option:\s
                    all - all content,\s
                    allManufacturer - all car manufacturers,\s
                    allCars - manufacturer names and quantity cars of each manufacturer,\s
                    q - close connection
                    """);
            switch (scan.next()) {
                case "all" -> {
                    PreparedStatement allCars = connection.prepareStatement(getAllCars);
                    ResultSet resultSetAllCars = allCars.executeQuery();
                    sixValues(resultList, count, resultSetAllCars);
                }
                case "allManufacturer" -> {
                    PreparedStatement allManufacturer = connection.prepareStatement(getAllManufacturer);
                    ResultSet resultSetAllManufacturer = allManufacturer.executeQuery();
                    oneValue(resultList, count, resultSetAllManufacturer);
                }
                case "allCars" -> {
                    PreparedStatement quantityCarsOfManufacturer = connection.prepareStatement(getQuantityCarsOfManufacturer);
                    ResultSet resultSetQuantityCarsOfManufacturer = quantityCarsOfManufacturer.executeQuery();
                    twoValues(resultList, count, resultSetQuantityCarsOfManufacturer);
                }
                case "q" -> connection.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(resultList.toString().replaceAll("[\\[\\]]", "").replaceAll(", ", " ").replaceAll("null", ""));
        scan.close();
    }

    //Разделил по блокам и выделил по методам, как в задании
    protected void getValuesFromDB_Part2() {
        try (Connection connection = DriverManager.getConnection(PATHCONNECTION, connectionValues)) {
            System.out.println("""
                    Choose a selection option:\s
                    max - max car of the manufacturers ,\s
                    min - min car of the manufacturers,\s
                    year - all year-to-year cars,\s
                    range - all cars with a year of manufacture within the specified range
                    """);
            switch (scan.next()) {
                case "max" -> {
                    PreparedStatement maxCarsOfManufacturer = connection.prepareStatement(getMaxCarsOfManufacturer);
                    ResultSet resultSetMaxCarsOfManufacturer = maxCarsOfManufacturer.executeQuery();
                    twoValues(resultList, count, resultSetMaxCarsOfManufacturer);
                }
                case "min" -> {
                    PreparedStatement minCarsOfManufacturer = connection.prepareStatement(getMinCarsOfManufacturer);
                    ResultSet resultSetMinCarsOfManufacturer = minCarsOfManufacturer.executeQuery();
                    twoValues(resultList, count, resultSetMinCarsOfManufacturer);
                }
                case "year" -> {
                    PreparedStatement yearOfTheCar = connection.prepareStatement(getYearOfTheCar);
                    ResultSet resultSetYearOfTheCar = yearOfTheCar.executeQuery();
                    sixValues(resultList, count, resultSetYearOfTheCar);
                }
                case "range" -> {
                    PreparedStatement rangeYearOfTheCar = connection.prepareStatement(getRangeYearOfTheCar);
                    ResultSet resultSetRangeYearOfTheCar = rangeYearOfTheCar.executeQuery();
                    sixValues(resultList, count, resultSetRangeYearOfTheCar);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(resultList.toString().replaceAll("[\\[\\]]", "").replaceAll(", ", " ").replaceAll("null", ""));
        scan.close();
    }

    //По идее, тут нужно было выносить переменную для фильтров, но уже "глаз замылился".
    protected void getValuesFromDB_Part3() {
        try (Connection connection = DriverManager.getConnection(PATHCONNECTION, connectionValues)) {
            System.out.println("""
                    Choose a selection option:\s
                    all - all cars of a particular manufacturer ,\s
                    color - filter for displaying cars by the specified color,\s
                    engine - filter by engine volume,\s
                    type - filter by car type
                    """);
            switch (scan.next()) {
                case "all" -> {
                    PreparedStatement nameCarOfTheManufacturer = connection.prepareStatement(getNameCarOfTheManufacturer);
                    ResultSet resultSetNameCarOfTheManufacturer = nameCarOfTheManufacturer.executeQuery();
                    sixValues(resultList, count, resultSetNameCarOfTheManufacturer);
                }
                case "color" -> {
                    PreparedStatement colorCarOfTheManufacturer = connection.prepareStatement(getColorCarOfTheManufacturer);
                    ResultSet resultSetColorCarOfTheManufacturer = colorCarOfTheManufacturer.executeQuery();
                    sixValues(resultList, count, resultSetColorCarOfTheManufacturer);
                }
                case "engine" -> {
                    PreparedStatement engineCarOfTheManufacturer = connection.prepareStatement(getEngineCarOfTheManufacturer);
                    ResultSet resultSetEngineCarOfTheManufacturer = engineCarOfTheManufacturer.executeQuery();
                    sixValues(resultList, count, resultSetEngineCarOfTheManufacturer);
                }
                case "type" -> {
                    PreparedStatement typeCarOfTheManufacturer = connection.prepareStatement(getTypeCarOfTheManufacturer);
                    ResultSet resultSetTypeCarOfTheManufacturer = typeCarOfTheManufacturer.executeQuery();
                    sixValues(resultList, count, resultSetTypeCarOfTheManufacturer);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(resultList.toString().replaceAll("[\\[\\]]", "").replaceAll(", ", " ").replaceAll("null", ""));
        scan.close();
    }


    //Т.к. запросы могут возвращать неопределенное количество параметров разделил по методам каждые случаи
    private void oneValue(List<Object> resultList, int count, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            ValuesFromDB valuesFromDB = new ValuesFromDB.Builder()
                    .manufacturer(resultSet.getString(1))
                    .build();
            resultList.add(++count);
            resultList.add(valuesFromDB);
        }
    }
    private void twoValues(List<Object> resultList, int count, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            ValuesFromDB valuesFromDB = new ValuesFromDB.Builder()
                    .manufacturer(resultSet.getString(1))
                    .autoName(String.valueOf(resultSet.getInt(2)))
                    .build();
            resultList.add(++count);
            resultList.add(valuesFromDB);
        }
    }
    private void sixValues(List<Object> resultList, int count, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            ValuesFromDB valuesFromDB = new ValuesFromDB.Builder()
                    .manufacturer(resultSet.getString(1))
                    .autoName(resultSet.getString(2))
                    .color(resultSet.getString(3))
                    .engine(Double.valueOf(resultSet.getString(4)))
                    .year(LocalDate.parse(resultSet.getString(5)))
                    .type(resultSet.getString(6))
                    .build();
            resultList.add(++count);
            resultList.add(valuesFromDB);
        }
    }
}