package cars;

public class Query {
    String getAllCars = """
            SELECT manufacturers.manufacturer_name as Manufacturer,  autos.auto_name as Name, colors.color_name as Color,
            autos.engine, autos.year_of_manufacture as Year, autos.type_auto as Type
            FROM autos
            JOIN manufacturerAuto ON autos.id = manufacturerAuto.id_auto
            JOIN manufacturers ON manufacturerAuto.id_manufacturer = manufacturers.id
            JOIN colorAuto ON autos.id = colorAuto.id_auto
            JOIN colors ON colorAuto.id_color = colors.id
            ORDER BY Manufacturer""";
    String getAllManufacturer = """
            SELECT manufacturers.manufacturer_name AS Manufacturer FROM Manufacturers""";
    String getQuantityCarsOfManufacturer = """
            SELECT manufacturers.manufacturer_name AS Manufacturer, COUNT(autos.auto_name)
            FROM autos
            JOIN manufacturerauto ON autos.id = manufacturerauto.id_auto
            JOIN manufacturers ON manufacturerauto.id_manufacturer = manufacturers.id
            GROUP BY Manufacturer""";
    String getMaxCarsOfManufacturer = """
            SELECT  manufacturers.manufacturer_name as Manufacturer,  COUNT(autos.auto_name)
            FROM autos
            JOIN manufacturerauto ON autos.id = manufacturerauto.id_auto
            JOIN manufacturers ON manufacturerauto.id_manufacturer = manufacturers.id
            GROUP BY Manufacturer
            HAVING COUNT(manufacturers.manufacturer_name) =(
            	SELECT MAX(autoCount)
            FROM (
                SELECT manufacturers.manufacturer_name,  COUNT(autos.auto_name) autoCount
            	FROM autos
            	JOIN manufacturerauto ON autos.id = manufacturerauto.id_auto
            	JOIN manufacturers ON manufacturerauto.id_manufacturer = manufacturers.id
            	GROUP BY manufacturers.manufacturer_name) as count)""";
    String getMinCarsOfManufacturer = """
            SELECT  manufacturers.manufacturer_name as Manufacturer,  COUNT(autos.auto_name)
            FROM autos
            JOIN manufacturerauto ON autos.id = manufacturerauto.id_auto
            JOIN manufacturers ON manufacturerauto.id_manufacturer = manufacturers.id
            GROUP BY Manufacturer
            HAVING COUNT(manufacturers.manufacturer_name) =(
            	SELECT MIN(autoCount)
            FROM (
                SELECT manufacturers.manufacturer_name,  COUNT(autos.auto_name) autoCount
            	FROM autos
            	JOIN manufacturerauto ON autos.id = manufacturerauto.id_auto
            	JOIN manufacturers ON manufacturerauto.id_manufacturer = manufacturers.id
            	GROUP BY manufacturers.manufacturer_name) as count)""";
    String getYearOfTheCar = """
            SELECT manufacturers.manufacturer_name as Manufacturer,  autos.auto_name as Name, colors.color_name as Color,
                        autos.engine, autos.year_of_manufacture, autos.type_auto as Type
                        FROM autos
                        JOIN manufacturerAuto ON autos.id = manufacturerAuto.id_auto
                        JOIN manufacturers ON manufacturerAuto.id_manufacturer = manufacturers.id
                        JOIN colorAuto ON autos.id = colorAuto.id_auto
                        JOIN colors ON colorAuto.id_color = colors.id
            			WHERE autos.year_of_manufacture = '2019-01-01'
            			ORDER BY Manufacturer""";
    String getRangeYearOfTheCar = """
            SELECT manufacturers.manufacturer_name as Manufacturer,  autos.auto_name as Name, colors.color_name as Color,
                        autos.engine, autos.year_of_manufacture, autos.type_auto as Type
                        FROM autos
                        JOIN manufacturerAuto ON autos.id = manufacturerAuto.id_auto
                        JOIN manufacturers ON manufacturerAuto.id_manufacturer = manufacturers.id
                        JOIN colorAuto ON autos.id = colorAuto.id_auto
                        JOIN colors ON colorAuto.id_color = colors.id
            			WHERE autos.year_of_manufacture >= '2015-01-01' AND autos.year_of_manufacture <= '2017-01-01'
            			ORDER BY autos.year_of_manufacture""";
    String getNameCarOfTheManufacturer = """
            SELECT manufacturers.manufacturer_name as Manufacturer, autos.auto_name AS autoName,colors.color_name as Color,
                        autos.engine, autos.year_of_manufacture as Year, autos.type_auto as Type
                        FROM autos
                        JOIN manufacturerauto ON autos.id = manufacturerauto.id_auto
                        JOIN manufacturers ON manufacturerauto.id_manufacturer = manufacturers.id
            			JOIN colorAuto ON autos.id = colorAuto.id_auto
                        JOIN colors ON colorAuto.id_color = colors.id
            			WHERE manufacturers.manufacturer_name = 'LADA'
                        ORDER BY autoName""";
    String getColorCarOfTheManufacturer = """
            SELECT manufacturers.manufacturer_name as Manufacturer, autos.auto_name AS autoName,colors.color_name as Color,
                        autos.engine, autos.year_of_manufacture as Year, autos.type_auto as Type
                        FROM autos
                        JOIN manufacturerauto ON autos.id = manufacturerauto.id_auto
                        JOIN manufacturers ON manufacturerauto.id_manufacturer = manufacturers.id
            			JOIN colorAuto ON autos.id = colorAuto.id_auto
                        JOIN colors ON colorAuto.id_color = colors.id
            			WHERE colors.color_name = 'black'
                        ORDER BY Manufacturer""";
    String getEngineCarOfTheManufacturer = """
            SELECT manufacturers.manufacturer_name as Manufacturer, autos.auto_name AS autoName,colors.color_name as Color,
                        autos.engine, autos.year_of_manufacture as Year, autos.type_auto as Type
                        FROM autos
                        JOIN manufacturerauto ON autos.id = manufacturerauto.id_auto
                        JOIN manufacturers ON manufacturerauto.id_manufacturer = manufacturers.id
            			JOIN colorAuto ON autos.id = colorAuto.id_auto
                        JOIN colors ON colorAuto.id_color = colors.id
            			WHERE autos.engine >= 2.0 AND autos.engine <= 3.0
                        ORDER BY autos.engine""";
    String getTypeCarOfTheManufacturer = """
            SELECT manufacturers.manufacturer_name as Manufacturer, autos.auto_name AS autoName,colors.color_name as Color,
                        autos.engine, autos.year_of_manufacture as Year, autos.type_auto as Type
                        FROM autos
                        JOIN manufacturerauto ON autos.id = manufacturerauto.id_auto
                        JOIN manufacturers ON manufacturerauto.id_manufacturer = manufacturers.id
            			JOIN colorAuto ON autos.id = colorAuto.id_auto
                        JOIN colors ON colorAuto.id_color = colors.id
            			WHERE autos.type_auto = 'hatchback' OR autos.type_auto = 'station wagon'
                        ORDER BY autos.type_auto""";
}
