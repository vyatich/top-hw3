package pack;

import java.util.Objects;

public class Car {

    private Integer id;
    private String procreator;
    private String model;
    private String engineVolume;
    private String releaseYear;
    private String color;
    private String bodytype;

    public Car(Integer id, String procreator, String model, String engineVolume, String releaseYear, String color, String bodytype) {
        this.id = id;
        this.procreator = procreator;
        this.model = model;
        this.engineVolume = engineVolume;
        this.releaseYear = releaseYear;
        this.color = color;
        this.bodytype = bodytype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcreator() {
        return procreator;
    }

    public void setProcreator(String procreator) {
        this.procreator = procreator;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(String engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBodytype() {
        return bodytype;
    }

    public void setBodytype(String bodytype) {
        this.bodytype = bodytype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(procreator, car.procreator) && Objects.equals(model, car.model) && Objects.equals(engineVolume, car.engineVolume) && Objects.equals(releaseYear, car.releaseYear) && Objects.equals(color, car.color) && Objects.equals(bodytype, car.bodytype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, procreator, model, engineVolume, releaseYear, color, bodytype);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", procreator='" + procreator + '\'' +
                ", model='" + model + '\'' +
                ", engineVolume='" + engineVolume + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", color='" + color + '\'' +
                ", bodytype='" + bodytype + '\'' +
                '}';
    }
}
