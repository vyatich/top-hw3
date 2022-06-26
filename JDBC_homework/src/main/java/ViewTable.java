import com.github.freva.asciitable.AsciiTable;

import java.util.Arrays;
import java.util.List;

public class ViewTable {
    private static final String[] headerAll =
            new String[]{"id", "brand", "name", "engine_volume", "year", "color", "type"};
    private static final String[] headerSolo =
            new String[]{"brand"};


    public static String listToTable(List<CarsData> list) {
        System.out.println(list);
        int row = 0;
        int column = 0;
        String[][] array = new String[list.size()][7];
        for (CarsData a : list) {
            array[row][column++] = String.valueOf(a.getId());
            array[row][column++] = a.getBrand();
            array[row][column++] = a.getName();
            array[row][column++] = String.valueOf(a.getEngineVolume());
            array[row][column++] = String.valueOf(a.getYear());
            array[row][column++] = a.getColor();
            array[row][column] = a.getType();
            row++;
            column = 0;
        }
        System.out.println(Arrays.toString(array));
        return AsciiTable.getTable(headerAll, array);
    }


}
