import java.util.ArrayList;
import java.util.List;

public class QueryList<T extends Student & QueryItem> extends ArrayList<T> {

    public QueryList(List<T> items) {
        super(items);
    }

    public static <S extends QueryItem> List<S> getMatches(List<S> items, String field, String value) {
        List<S> matches = new ArrayList<>();
        for (S item : items) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }

    public List<T> getMatches(String field, String value) {
        List<T> matches = new ArrayList<>();
        for (T item : this) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }
}
