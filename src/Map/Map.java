import java.util.ArrayList;

public class Map {
    public static void main(String[] args) {

        ArrayList<Mappable> mappables = new ArrayList<>();

        mappables.add(new Building("Stadshuset", BuildingType.RESIDENTIAL));
        mappables.add(new Building("City kontoret", BuildingType.BUSINESS));
        mappables.add(new Building("Kungliga Tekniska Högskolan", BuildingType.SCHOOL));
        mappables.add(new UtilityLine("Fortum Markets", UtilityType.ELECTRICAL));
        mappables.add(new UtilityLine("Vattenfall", UtilityType.WATER));
        mappables.add(new UtilityLine("Stockholm Gas", UtilityType.GAS));

        for (var m : mappables) {
            Mappable.mapIt(m);
        }
    }
}

interface Mappable {

    static String JSON_PROPERTY = """
            "properties": {%s}""";

    static void mapIt(Mappable mappable) {
        System.out.println(mappable.toJSON());
    };

    String getLabel();

    Enum<Geometry> getShape();

    String getMarker();

    default String toJSON() {
        return String.format(JSON_PROPERTY, """
                "type": "%s", "label": "%s", "marker": "%s"
                """.formatted(getShape(), getLabel(), getMarker()));
    }

}

enum Geometry {
    LINE, POINT, POLYGON
}

enum BuildingType {
    BUSINESS, RESIDENTIAL, SCHOOL
}

enum UtilityType {
    ELECTRICAL, WATER, GAS
}

enum Color {
    RED, GREEN, BLUE
}

enum PointMarkers {
    CIRCLE, SQUARE, TRIANGLE, DIAMOND
}

enum LineMarkers {
    DASHED, SOLID, DOTTED
}

class Building implements Mappable {

    String name;
    Enum<BuildingType> type;

    public Building(String name, Enum<BuildingType> type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getLabel() {
        return "%s (%s)".formatted(name, type);
    }

    @Override
    public Enum<Geometry> getShape() {
        return Geometry.POLYGON;
    }

    @Override
    public String getMarker() {
        return switch (type) {
            case BuildingType.BUSINESS -> "%s %s".formatted(Color.RED, PointMarkers.SQUARE);
            case BuildingType.RESIDENTIAL -> "%s %s".formatted(Color.GREEN, PointMarkers.CIRCLE);
            case BuildingType.SCHOOL -> "%s %s".formatted(Color.BLUE, PointMarkers.TRIANGLE);
            default -> "unknown";
        };
    }

    @Override
    public String toJSON() {
        return String.format(JSON_PROPERTY, """
                "type": "%s", "label": "%s", "marker": "%s", "name": "%s", "type": "%s"\
                """.formatted(getShape(), getLabel(), getMarker(), name, type));

    }
}

class UtilityLine implements Mappable {

    String name;
    Enum<UtilityType> type;

    public UtilityLine(String name, Enum<UtilityType> type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getLabel() {
        return "%s (%s)".formatted(name, type);
    }

    @Override
    public Enum<Geometry> getShape() {
        return Geometry.LINE;
    }

    @Override
    public String getMarker() {
        return switch (type) {
            case UtilityType.ELECTRICAL -> "%s %s".formatted(Color.RED, LineMarkers.DASHED);
            case UtilityType.WATER -> "%s %s".formatted(Color.BLUE, LineMarkers.SOLID);
            case UtilityType.GAS -> "%s %s".formatted(Color.GREEN, LineMarkers.DOTTED);
            default -> "unknown";
        };
    }

    @Override
    public String toJSON() {
        return String.format(JSON_PROPERTY, """
                "type": "%s", "label": "%s", "marker": "%s", "name": "%s", "type": "%s"\
                """.formatted(getShape(), getLabel(), getMarker(), name, type));
    }
}