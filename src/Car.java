import java.util.Objects;

public class Car {
    private int Id;
    private String model;
    private String manufacture;
    private double engineCapacity;
    private int ageOfRelease;
    private String color;
    private String type;

    public Car(int id, String model, String manufacture, double engineCapacity, int ageOfRelease, String color, String type) {
        Id = id;
        this.model = model;
        this.manufacture = manufacture;
        this.engineCapacity = engineCapacity;
        this.ageOfRelease = ageOfRelease;
        this.color = color;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Id == car.Id && Double.compare(car.engineCapacity, engineCapacity) == 0 && ageOfRelease == car.ageOfRelease && Objects.equals(model, car.model) && Objects.equals(manufacture, car.manufacture) && Objects.equals(color, car.color) && Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, model, manufacture, engineCapacity, ageOfRelease, color, type);
    }

    @Override
    public String toString() {
        return "Id=" + Id +
                ", model='" + model + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", ageOfRelease=" + ageOfRelease +
                ", color='" + color + '\'' +
                ", type='" + type + '\'';
    }
}
