package pack;

import java.time.Year;
import java.util.Objects;

public class Car {
    private int id;
    private String brand;
    private String model;
    private Double engine_volume;
    private int year;
    private String color;
    private String type;

    public Car() {
    }

    public Car(int id, String brand, String model, Double engine_volume, int year, String color, String type) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.engine_volume = engine_volume;
        this.year = year;
        this.color = color;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getEngine_volume() {
        return engine_volume;
    }

    public void setEngine_volume(Double engine_volume) {
        this.engine_volume = engine_volume;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(engine_volume, car.engine_volume) && Objects.equals(year, car.year) && Objects.equals(color, car.color) && Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, engine_volume, year, color, type);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", engine_volume=" + engine_volume +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
