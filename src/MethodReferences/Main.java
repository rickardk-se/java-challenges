import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class Main {

    public static void main(String[] args) {
        String[] names = {
                "Anna", "erik", "Karin", "lars", "Sara",
                "Lisa", "mikael", "Johan", "lena", "bob"
        };

        Map<String, UnaryOperator<String>> operators = new LinkedHashMap<>();
        operators.put("lowercase", String::toLowerCase);
        operators.put("uppercase", String::toUpperCase);
        operators.put("initial", Main::initial);
        operators.put("lastname", name -> {
            String[] parts = name.split(" ");
            return name + " " + new StringBuilder(parts[0]).reverse();
        });
        operators.put("smiley", new Main()::smiley);
        operators.put("format", new Main()::format);

        applyOperators(names, operators);
    }

    public static String initial(String name) {
        char randomInitial = (char) ('A' + Math.random() * 26);
        return name + " " + randomInitial + ".";
    }

    public String smiley(String name) {
        String[] smileys = { "😀", "😄", "😊", "😎", "🥳", "😁", "😇", "🤓", "🙃", "😺" };
        int index = (int) (Math.random() * smileys.length);
        return name + " " + smileys[index];
    }

    public String format(String name) {
        String[] parts = name.split(" ");
        return String.format("%-8s %2s %-8s %-1s", parts[0], parts[1], parts[2], parts[3]);
    }

    public static void applyOperators(String[] names, Map<String, UnaryOperator<String>> operators) {
        for (Map.Entry<String, UnaryOperator<String>> entry : operators.entrySet()) {
            System.out.println("\nUsing operator: " + entry.getKey());
            Arrays.setAll(names, i -> entry.getValue().apply(names[i]));
            Arrays.asList(names).forEach(name -> System.out.println(name));
        }
    }
}
