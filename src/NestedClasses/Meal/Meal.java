package Meal;

import java.util.ArrayList;

public class Meal {
    private final Item mainDish;
    private final Item sideDish;
    private final Item drink;
    private final Burger burger;

    public Meal() {
        mainDish = new Item("Pork", "main dish", 10.0);
        sideDish = new Item("Salad", "side dish", 5.0);
        drink = new Item("Coffee", "hot drink", 2.0);
        burger = new Burger("Cheeseburger", 12.0);
        burger.addToppings("lettuce", "tomato", "onion", "cheese");
    }

    public double getTotalPrice() {
        return mainDish.price + sideDish.price + drink.price + burger.getPrice();
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n%s%n%25s%.2f".formatted(mainDish, sideDish, drink, burger,
                "Total price: ", getTotalPrice());
    }

    private class Item {
        private final String name;
        private final String type;
        private final double price;

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        @Override
        public String toString() {
            return "%10s%15s %.2f".formatted(type, name, price);
        }
    }

    public class Burger extends Item {
        ArrayList<Item> toppings = new ArrayList<>();

        public Burger(String name, double price) {
            super(name, "burger", price);
        }

        public void addToppings(String... toppings) {
            for (String topping : toppings) {
                if (topping.equals("lettuce")) {
                    this.toppings.add(new Item("Lettuce", "topping", 0.5));
                } else if (topping.equals("tomato")) {
                    this.toppings.add(new Item("Tomato", "topping", 0.5));
                } else if (topping.equals("onion")) {
                    this.toppings.add(new Item("Onion", "topping", 0.5));
                } else if (topping.equals("cheese")) {
                    this.toppings.add(new Item("Cheese", "topping", 1.0));
                }
            }
        }

        public double getPrice() {
            double total = super.price;
            for (Item topping : toppings) {
                total += topping.price;
            }
            return total;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(super.toString() + "\n");
            for (Item topping : toppings) {
                sb.append("%s".formatted(topping)).append("\n");
            }
            sb.append("%25s%.2f".formatted("Burger price: ", getPrice()));
            return sb.toString();
        }
    }

}
