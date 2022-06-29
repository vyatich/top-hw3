package cars;

public class CarsApplication {
    public static void main(String[] args) {
        ConnectionToDBAndGetQuery cdb = new ConnectionToDBAndGetQuery(
                "jdbc:postgresql://127.0.0.1:5440/cars_db", "postgres", "test");
        cdb.getValuesFromDB_Part1();
//        cdb.getValuesFromDB_Part2(); //закомментировал вызов метода, т.к. разделил по блокам как в задании
//        cdb.getValuesFromDB_Part3(); //закомментировал вызов метода, т.к. разделил по блокам как в задании
    }
}
