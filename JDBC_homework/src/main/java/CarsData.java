import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarsData {

    int id;
    String brand;
    String name;
    double engineVolume;
    int year;
    String color;
    String type;


}
