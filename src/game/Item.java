package game;

public class Item {
    private String name;
    private int price;
    private int weight; // Weight in pounds
    private double pricePerPound; // Price per pound

    // Constructor with name, price, weight, and price per pound
    public Item(String name, int price, int weight, double pricePerPound) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.pricePerPound = pricePerPound;
    }

    // Getter method for the name
    public String getName() {
        return name;
    }

    // Getter method for the price
    public int getPrice() {
        return price;
    }

    // Getter method for the weight
    public int getWeight() {
        return weight;
    }

    // Getter method for the price per pound
    public double getPricePerPound() {
        return pricePerPound;
    }
}
