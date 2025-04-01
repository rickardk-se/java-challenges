package Lambda;

import java.util.Arrays;
import java.util.ArrayList;

public class Names {
    public static void main(String[] args) {

        // first names in mixed case
        String[] names = {
                "Anna", "erik", "Karin", "lars", "Sara",
                "Lisa", "mikael", "Johan", "lena", "bob"
        };

        // Transform the names to upper case using lambda expression
        Arrays.setAll(names, i -> names[i].toUpperCase());
        System.out.println("Names in upper case:");
        Arrays.asList(names).forEach(name -> System.out.println(name));

        // Add random initial to names
        Arrays.setAll(names, i -> names[i] + " " + (char) ('A' + Math.random() * 26) + ".");
        System.out.println("\nNames with random initials:");
        for (String name : names) {
            System.out.println(name);
        }

        // Add last name that is reversed of first name
        Arrays.setAll(names, i -> names[i] + " " + new StringBuilder(names[i].split(" ")[0]).reverse());
        System.out.println("\nNames with reversed last name:");
        for (String name : names) {
            System.out.println(name);
        }

        ArrayList<String> filteredNames = new ArrayList<>(Arrays.asList(names));
        filteredNames.removeIf(name -> {
            String[] parts = name.split(" ");
            return parts.length == 3 && parts[0].equals(parts[2]);
        });
        System.out.println("\nFiltered names:");
        for (String name : filteredNames) {
            System.out.println(name);
        }

    }
}
