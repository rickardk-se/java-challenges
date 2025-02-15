import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Store {
    private ArrayList<OrderItem> orders = new ArrayList<>();
    private final ArrayList<ProductForSale> products = new ArrayList<>();

    public void listProducts() {
        for (ProductForSale p : products) {
            System.out.println("-".repeat(30));
            p.showDetails();
        }
    }

    public void printOrders() {
        for (OrderItem o : orders) {
            System.out.printf("%nOrder %d%n", o.getOrderId());
            for (Map.Entry<ProductForSale, Integer> entry : o.getCart().entrySet()) {
                ProductForSale p = entry.getKey();
                int quantity = entry.getValue();
                p.printPricedItem(quantity);
            }
            o.printReceipt();
        }
    }

    public static void main(String[] args) {

        Store mystore = new Store();

        var coffee = new Coffee(1.5);
        var cappuccino = new Cappuccino(3.0);
        var espresso = new Espresso(2.5);

        mystore.products.add(coffee);
        mystore.products.add(cappuccino);
        mystore.products.add(espresso);

        mystore.listProducts();

        var order1 = new OrderItem();
        order1.addItem(coffee, 2);
        order1.addItem(cappuccino, 1);
        order1.addItem(espresso, 3);
        mystore.orders.add(order1);

        var order2 = new OrderItem();
        order2.addItem(coffee, 5);
        order2.addItem(cappuccino, 3);
        order2.addItem(espresso, 5);
        mystore.orders.add(order2);

        mystore.printOrders();

    }
}

abstract class ProductForSale {
    String type;
    double price;
    String description;

    ProductForSale(String type, double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public double getSalesPrice(int quantity) {
        return price * quantity;
    }

    public void printPricedItem(int quantity) {
        System.out.printf("%d items at %.1f euro each (type: %s, description: %s)%n", quantity, price, type,
                description);
    }

    abstract public void showDetails();
}

class Coffee extends ProductForSale {
    public Coffee(double price) {
        super("Coffee", price, "Regular coffee");
    }

    public void showDetails() {
        System.out.println("Bold. Rich. Smooth. Robust.");
        System.out.printf("The price of this coffee is %.1f euro%n", price);
        System.out.printf("%s%n", type);
    }
}

class Cappuccino extends ProductForSale {
    public Cappuccino(double price) {
        super("Cappuccino", price, "Fancy cappuccino");
    }

    public void showDetails() {
        System.out.println("Creamy. Frothy. Balanced. Smooth.");
        System.out.printf("The price of this coffee is %.1f euro%n", price);
        System.out.printf("%s%n", type);
    }
}

class Espresso extends ProductForSale {
    public Espresso(double price) {
        super("Espresso", price, "Strong espresso");
    }

    public void showDetails() {
        System.out.println("Intense. Velvety. Bold. Aromatic.");
        System.out.printf("The price of this coffee is %.1f euro%n", price);
        System.out.printf("%s%n", type);
    }
}

class OrderItem {
    private static int orders = 0;
    private final int orderId;
    private double total_cost;
    private final Map<ProductForSale, Integer> cart = new HashMap<>();

    public OrderItem() {
        this.orderId = ++orders;
    }

    public int getOrderId() {
        return orderId;
    }

    public void addItem(ProductForSale p, int q) {
        cart.put(p, cart.getOrDefault(p, 0) + q); // Update quantity
        total_cost += p.getSalesPrice(q);
    }

    public Map<ProductForSale, Integer> getCart() {
        return cart;
    }

    public void printReceipt() {
        System.out.printf("Total sales: %.2f euro.%n", total_cost);
    }
}