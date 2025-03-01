import java.util.ArrayList;

public class Locations {

    public static void main(String[] args) {

        ArrayList<Park> parkList = new ArrayList<Park>();
        parkList.add(new Park("Kungsträdgården", "59.331205689784255, 18.072005593950077"));
        parkList.add(new Park("Humlegården", "59.34004743659721, 18.073272770888394"));
        parkList.add(new Park("Vasaparken", "59.34025773013979, 18.042874668335177"));

        Layer<Park> parkLayer = new Layer<>(parkList);

        parkLayer.renderLayer();

        ArrayList<River> riverList = new ArrayList<River>();
        riverList.add(new River("Riddarfjärden", "59.31808035641888, 18.000918233918693",
                "59.32171213965138, 18.071464887453335"));

        Layer<River> riverLayer = new Layer<>(riverList);

        riverLayer.addElement(new River("Stocksundet", "59.387504227711325, 18.02995083964035",
                "59.387504227711325, 18.02995083964035"));

        riverLayer.renderLayer();

    }
}

enum Geometry {
    LINE, POINT, POLYGON
}

interface Mappable {

    static double[] stringToLatLon(String latLon) {
        String[] latLonArray = latLon.split(",");
        double lat = Double.parseDouble(latLonArray[0]);
        double lon = Double.parseDouble(latLonArray[1]);
        return new double[] { lat, lon };
    };

    public void render();

}

abstract class Point implements Mappable {
    Enum<Geometry> shape = Geometry.POINT;
    double lat;
    double lon;

    public Point(String coordinate) {
        double[] latLon = Mappable.stringToLatLon(coordinate);
        this.lat = latLon[0];
        this.lon = latLon[1];
    }

}

class Park extends Point {
    String name;

    public Park(String name, String coordinate) {
        super(coordinate);
        this.name = name;
    }

    @Override
    public void render() {
        System.out.println("%s as %s ([%f, %f])".formatted(name, shape, lat, lon));
    }
}

abstract class Line implements Mappable {
    Enum<Geometry> shape = Geometry.LINE;
    double[] start;
    double[] end;

    public Line(String start, String end) {
        this.start = Mappable.stringToLatLon(start);
        this.end = Mappable.stringToLatLon(end);
    }
}

class River extends Line {

    String name;

    public River(String name, String start, String end) {
        super(start, end);
        this.name = name;
    }

    @Override
    public void render() {
        System.out
                .println("%s as %s ([[%f, %f], [%f, %f]])".formatted(name, shape, start[0], start[1], end[0], end[1]));
    }
}

class Layer<T extends Mappable> {

    private ArrayList<T> layerItems = new ArrayList<>();

    public Layer(ArrayList<T> elements) {
        layerItems.addAll(elements);
    }

    public void addElement(T element) {
        layerItems.add(element);
    }

    public void addElements(ArrayList<T> elements) {
        layerItems.addAll(elements);
    }

    public void renderLayer() {
        for (T element : layerItems) {
            element.render();
        }
    }
}