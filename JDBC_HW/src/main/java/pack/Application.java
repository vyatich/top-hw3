package pack;

import java.sql.*;
import java.util.Scanner;

import static pack.CommandHandler.*;
import static pack.DBConnection.DATABASE_URL;

public class Application {
    public static void main(String[] args) {
        new DBCreate().createDB();
        Scanner in = new Scanner(System.in);
        try (Connection connection = DriverManager.getConnection(DATABASE_URL + "/carsdb", DBConnection.getConnection())) {
            System.out.println("Соединение с базой данных установлено.");
            boolean marker = true;
            while (marker){
                System.out.println("""
                        Введите запрос:
                        /car - для отображения списка авто
                        /brand - для отображения списка производителей
                        /count - для отображения кол-ва автомобилей
                        /q - для выхода""");
                String command = in.nextLine();
                if(command.equalsIgnoreCase("/car")){
                    getCarListFromDB(connection);
                } else if (command.equalsIgnoreCase("/brand")){
                    getCarBrandFromDB(connection, "brand");
                }else if (command.equalsIgnoreCase("/count")) {
                    getCountCarFromBrandFromDB(connection);
                }else if (command.equalsIgnoreCase("/q")){
                    connection.close();
                    marker = false;
                    System.out.println("Соединение с базой данных закрыто.");
                }else {
                    System.out.println("Попробуйте ещё раз!\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
