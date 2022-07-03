package pack.jdbc;

import java.time.LocalDate;
import java.util.Objects;

public class Car {
    private int id;
    private String nameOfCarManufacturer;
    private String nameOfCar;

    private Float engineVolume;
    private LocalDate yearOfIssue;
    private String carColor;
    private String carType;

    public Car(int id, String nameOfCarManufacturer, String nameOfCar, Float engineVolume, LocalDate yearOfIssue, String carColor, String carType) {
        this.id = id;
        this.nameOfCarManufacturer = nameOfCarManufacturer;
        this.nameOfCar = nameOfCar;
        this.engineVolume = engineVolume;
        this.yearOfIssue = yearOfIssue;
        this.carColor = carColor;
        this.carType = carType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Objects.equals(nameOfCarManufacturer, car.nameOfCarManufacturer) &&
                Objects.equals(nameOfCar, car.nameOfCar) &&
                Objects.equals(engineVolume, car.engineVolume) &&
                Objects.equals(yearOfIssue, car.yearOfIssue) &&
                Objects.equals(carColor, car.carColor) &&
                Objects.equals(carType, car.carType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfCarManufacturer, nameOfCar, engineVolume, yearOfIssue, carColor, carType);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nCar{");
        sb.append("id='").append(id).append('\'');
        sb.append(", nameOfCarManufacturer=").append(nameOfCarManufacturer);
        sb.append(", nameOfCar=").append(nameOfCar);
        sb.append(", engineVolume=").append(engineVolume);
        sb.append(", yearOfIssue=").append(yearOfIssue);
        sb.append(", carColor=").append(carColor);
        sb.append(", carType=").append(carType);
        sb.append('}');
        return sb.toString();
    }
}
