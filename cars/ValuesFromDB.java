package cars;

import java.time.LocalDate;

public class ValuesFromDB {
    private String manufacturer;
    private String autoName;
    private String color;
    private Double engine;
    private LocalDate year;
    private String type;

    public ValuesFromDB() {

    }

    //Вместо сотни конструкторов использовал паттерн Строитель
    public static class Builder {
        private final ValuesFromDB valuesFromDB;

        public Builder() {
            valuesFromDB = new ValuesFromDB();
        }

        public Builder manufacturer(String manufacturer) {
            valuesFromDB.manufacturer = manufacturer;
            return this;
        }

        public Builder autoName(String autoName) {
            valuesFromDB.autoName = autoName;
            return this;
        }

        public Builder color(String color) {
            valuesFromDB.color = color;
            return this;
        }

        public Builder engine(Double engine) {
            valuesFromDB.engine = engine;
            return this;
        }

        public Builder year(LocalDate year) {
            valuesFromDB.year = year;
            return this;
        }

        public Builder type(String type) {
            valuesFromDB.type = type;
            return this;
        }

        public ValuesFromDB build() {
            return valuesFromDB;
        }
    }

    @Override
    public String toString() {
        return manufacturer + " " + autoName + " " + color + " " + engine + " " + year + " " + type + "\n";
    }
}

