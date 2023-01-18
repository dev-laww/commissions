/**
 * @author tora
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Item class for inventory items
 */
class Item {
    private final String name;
    private final String category;
    private float supplyPrice;
    private float price;
    private int stock;
    private int sold = 0;

    /**
     * Constructor for Item class
     *
     * @param name        - name of item
     * @param category    - category of item
     * @param supplyPrice - supply price of item
     * @param price       - price of item
     * @param stock       - stocks left
     */
    public Item(String name, String category, float supplyPrice, float price, int stock) {
        this.name = name;
        this.price = price;
        this.supplyPrice = supplyPrice;

        // Check if category is valid, if not, set to "other"
        this.category = isValidCategory(category) ? category : "Other";
        this.stock = stock;
    }

    /**
     * Checks if category is valid
     *
     * @param category - category to check
     * @return true if valid, false if not
     */
    private boolean isValidCategory(String category) {
        String[] categories = {"Fruit", "Vegetable", "Meat", "Dairy", "Bakery", "Frozen", "Canned", "Condiments", "Other"};
        for (String c : categories) {
            if (c.equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns name of item
     *
     * @return name of item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns category of item
     *
     * @return category of item
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Returns supply price of item
     *
     * @return supply price of item
     */
    public float getPrice() {
        return this.price;
    }

    /**
     * Returns price of item
     *
     * @return price of item
     */
    public float getSupplyPrice() {
        return this.supplyPrice;
    }

    /**
     * Returns stock of item
     *
     * @return stock of item
     */
    public int getStock() {
        return this.stock;
    }

    /**
     * Returns sold of item
     *
     * @return sold of item
     */
    public int getSold() {
        return this.sold;
    }

    /**
     * Computes the net profit of the item
     * (price - supplyPrice) * sold
     */
    public float getNetProfit() {
        return (this.price - this.supplyPrice) * this.sold;
    }

    /**
     * Sets the supplyPrice of the item
     *
     * @param price - supplyPrice to set
     */
    public void updateSupplyPrice(float price) {
        this.supplyPrice = price;
    }

    /**
     * Sets the price of the item
     *
     * @param price - price to set
     */
    public void updatePrice(float price) {
        this.price = price;
    }

    /**
     * Sets the stock of the item
     *
     * @param stock - stock to set
     */
    public void updateStock(int stock) {
        this.stock = stock;
        this.sold = 0;
    }

    /**
     * Sets the sold of the item
     *
     * @param quantity - sold to set
     */
    public void sell(int quantity) {
        this.stock -= quantity;
        this.sold += quantity;
    }

    /**
     * Returns a string representation of the item
     *
     * @return string representation of the item
     */
    @Override
    public String toString() {
        return String.format(
                "\nItem: %s\nCategory: %s\nPrice: %.2f\nStock: %d",
                this.name,
                this.category,
                this.price,
                this.stock
        );
    }
}

/**
 * Main class for managing inventory items
 */
public class Main {
    static Scanner sc = new Scanner(System.in);

    /**
     * Calculates the total expenses of the warehouse
     *
     * @return total expenses of the warehouse
     */
    public static float calculateExpenses() {
        System.out.println("Enter store rental fee: ");
        float rent = Float.parseFloat(sc.nextLine());
        System.out.println("Enter electric bill estimate: ");
        float electricBill = Float.parseFloat(sc.nextLine());
        System.out.println("Enter water bill estimate: ");
        float waterBill = Float.parseFloat(sc.nextLine());
        float staffSalary = 1500.99f;
        System.out.println("Enter transportation cost estimate: ");
        System.out.println("Calculating expenses...");
        float totalExpenses = rent + electricBill + waterBill + staffSalary;
        return totalExpenses + (totalExpenses * 0.1f);
    }

    /**
     * Adds an item to the inventory
     *
     * @param items - list of items
     * @return list of items
     */
    public static ArrayList<Item> addItem(ArrayList<Item> items) {
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        String[] categories = {"Fruit", "Vegetable", "Meat", "Dairy", "Bakery", "Frozen", "Canned", "Condiments", "Other"};

        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }
        System.out.print("Enter item category: ");
        String category = categories[Integer.parseInt(sc.nextLine()) - 1];

        System.out.print("Enter item supply price: ");
        float supplyPrice = Float.parseFloat(sc.nextLine());
        System.out.print("Enter item price: ");
        float price = Float.parseFloat(sc.nextLine());
        System.out.print("Enter item quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());
        ArrayList<Item> newItems = new ArrayList<>();
        Item item = new Item(name, category, supplyPrice, price, quantity);
        newItems.add(item);

        // check if arraylist is empty
        if (!items.isEmpty()) {
            newItems.addAll(items);
        }
        return newItems;
    }

    /**
     * Removes an item from the inventory
     *
     * @param items - list of items
     */
    public static void removeItem(ArrayList<Item> items) {
        printItems(items);
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                items.remove(i);
                System.out.println("Item removed!");
                return;
            }
        }
        System.out.println("Item not found!");
    }

    /**
     * Sells an item from the inventory
     *
     * @param items - list of items
     */
    public static void sellItem(ArrayList<Item> items) {
        printItems(items);
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        // find item in arraylist
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter quantity to sell: ");
                int quantity = Integer.parseInt(sc.nextLine());
                // check if there's enough stock
                if (quantity > item.getStock()) {
                    System.out.println("Not enough quantity to sell");
                    return;
                }
                item.sell(quantity);
                System.out.println("Item sold");
                return;
            }
        }
        // if not found
        System.out.println("Item not found");
    }

    /**
     * Gets the most profitable and sold item
     *
     * @param items - list of items
     * @return most profitable and sold item
     */
    public static ArrayList<Item> mostSold(ArrayList<Item> items) {
        ArrayList<Item> mostSold = new ArrayList<>();
        int max = 0;
        for (Item i : items) {
            if (i.getSold() > max) {
                max = i.getSold();
            }
        }

        int finalMax = max;
        items.forEach(i -> {
            if (i.getSold() == finalMax) {
                mostSold.add(i);
            }
        });
        return mostSold;
    }

    /**
     * Gets the least profitable and sold item
     *
     * @param items - list of items
     * @return least profitable and sold item
     */
    public static ArrayList<Item> leastSold(ArrayList<Item> items) {
        ArrayList<Item> leastSold = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (Item i : items) {
            if (i.getSold() < min) {
                min = i.getSold();
            }
        }

        int finalMin = min;
        items.forEach(it -> {
            if (it.getSold() == finalMin) {
                leastSold.add(it);
            }
        });
        return leastSold;
    }

    /**
     * Generates a report of gross income
     *
     * @param soldItems - list of sold items
     * @return gross income
     */
    public static float generateGrossIncome(ArrayList<Item> soldItems) {
        float cogs = 0;
        for (Item i : soldItems) {
            cogs += i.getSupplyPrice() * i.getSold();
        }

        float sales = 0;
        for (Item i : soldItems) {
            sales += i.getPrice() * i.getSold();
        }

        return sales - cogs;
    }

    /**
     * Generates the net income of the inventory
     *
     * @param soldItems - list of sold items
     * @return net income
     */
    public static float generateNetIncome(ArrayList<Item> soldItems) {
        float grossIncome = generateGrossIncome(soldItems);
        float expenses = calculateExpenses();
        return grossIncome - expenses;
    }

    /**
     * Prints the items in the inventory
     *
     * @param items - list of items
     */
    public static void printItems(ArrayList<Item> items) {
        System.out.printf("\n%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", "Name", "Category", "Supply Price", "Price", "Quantity", "Sold", "Net Profit");
        for (Item i : items) {
            System.out.printf("%-20s%-20s%-20.2f%-20.2f%-20s%-20s%-20.2f%n", i.getName(), i.getCategory(), i.getSupplyPrice(), i.getPrice(), i.getStock(), i.getSold(), i.getNetProfit());
        }
    }

    /**
     * Main method
     *
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<>();
        // you can edit this
        items.add(new Item("Apple", "Fruit", 0.5f, 1.5f, 100));
        items.add(new Item("Orange", "Fruit", 0.5f, 1.5f, 100));
        items.add(new Item("Banana", "Fruit", 0.5f, 1.5f, 100));
        items.add(new Item("Milk", "Dairy", 0.5f, 1.5f, 100));
        items.add(new Item("Eggs", "Dairy", 0.5f, 1.5f, 100));
        items.add(new Item("Bread", "Bakery", 0.5f, 1.5f, 100));
        items.add(new Item("Chicken", "Meat", 0.5f, 1.5f, 100));
        items.add(new Item("Beef", "Meat", 0.5f, 1.5f, 100));
        items.add(new Item("Pork", "Meat", 0.5f, 1.5f, 100));
        items.add(new Item("Potato", "Vegetable", 0.5f, 1.5f, 100));
        items.add(new Item("Tomato", "Vegetable", 0.5f, 1.5f, 100));
        items.add(new Item("Onion", "Vegetable", 0.5f, 1.5f, 100));
        items.add(new Item("Carrot", "Vegetable", 0.5f, 1.5f, 100));
        items.add(new Item("Cucumber", "Vegetable", 0.5f, 1.5f, 100));
        int option;

        do {
            System.out.println("\nInventory System");
            System.out.println("[1] Add item");
            System.out.println("[2] Remove item");
            System.out.println("[3] Sell item");
            System.out.println("[4] Most sold item");
            System.out.println("[5] Least sold item");
            System.out.println("[6] Generate gross income");
            System.out.println("[7] Generate net income");
            System.out.println("[8] View items");
            System.out.println("[0] Exit\n");

            while (true) {
                try {
                    System.out.print("Enter option: ");
                    option = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                }
            }

            switch (option) {
                case 1:
                    System.out.println("Add item into inventory");
                    items = addItem(items);
                    break;
                case 2:
                    System.out.println("Remove item from inventory");
                    removeItem(items);
                    break;
                case 3:
                    System.out.println("Sell item");
                    sellItem(items);
                    break;
                case 4:
                    System.out.println("Most sold item");
                    ArrayList<Item> mostSold = mostSold(items);
                    printItems(mostSold);
                    break;
                case 5:
                    System.out.println("Least sold item");
                    ArrayList<Item> leastSold = leastSold(items);
                    printItems(leastSold);
                    break;
                case 6:
                    System.out.println("Generate gross income");
                    float grossIncome = generateGrossIncome(items);
                    System.out.printf("Gross income: %.2f%n", grossIncome);
                    break;
                case 7:
                    System.out.println("Generate net income");
                    float netIncome = generateNetIncome(items);
                    System.out.printf("Net income: %.2f%n", netIncome);
                    break;
                case 8:
                    System.out.println("Print items");
                    printItems(items);
                    break;
                default:
                    if (option != 0)
                        System.out.println("Invalid option");

            }

        } while (option != 0);

    }
}