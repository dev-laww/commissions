/**
 * @authors: tora, hyuse
 */
package utilities;

public class Item {
    // encapsulated fields
    private final String name;
    private final double price;
    private final String type;
    private int quantity;

    /**
     * Item constructor
     * @param name item name
     * @param type item type
     * @param price item price
     * @param quantity item quantity
     */
    public Item(String name,String type, double price, int quantity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return name + " " + price + " " + quantity;
    }
}
