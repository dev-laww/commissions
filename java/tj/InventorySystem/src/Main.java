import java.util.ArrayList;
import java.util.Scanner;

class Item {
    private final String name;
    private final String category;
    private float supplyPrice;
    private float price;
    private int stock;
    private int sold = 0;

    public Item(String name, String category, float supplyPrice, float price, int stock) {
        this.name = name;
        this.price = price;
        this.supplyPrice = supplyPrice;
        this.category = isValidCategory(category) ? category : "Other";
        this.stock = stock;
    }

    private boolean isValidCategory(String category) {
        String[] categories = {"Fruit", "Vegetable", "Meat", "Dairy", "Bakery", "Frozen", "Canned", "Condiments",  "Other"};
        for (String c : categories) {
            if (c.equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public float getPrice() {
        return this.price;
    }

    public float getSupplyPrice() {
        return this.supplyPrice;
    }

    public int getStock() {
        return this.stock;
    }

    public int getSold() {
        return this.sold;
    }

    public float getNetProfit() {
        return (this.price - this.supplyPrice) * this.sold;
    }

    public void updateSupplyPrice(float price) {
        this.supplyPrice = price;
    }

    public void updatePrice(float price) {
        this.price = price;
    }

    public void updateStock(int stock) {
        this.stock = stock;
        this.sold = 0;
    }

    public void sell(int quantity) {
        this.stock -= quantity;
        this.sold += quantity;
    }

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

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static float calculateExpenses() {
        System.out.println("Enter store rental fee: ");
        float rent = Float.parseFloat(sc.nextLine());
        System.out.println("Enter electric bill estimate: ");
        float electricBill = Float.parseFloat(sc.nextLine());
        System.out.println("Enter water bill estimate: ");
        float waterBill = Float.parseFloat(sc.nextLine());
        float staffSalary = 1500.99f;
        System.out.println("Enter transportation cost estimate: ");
        float transportCost = Float.parseFloat(sc.nextLine());
        System.out.println("Calculating expenses...");
        float totalExpenses = rent + electricBill + waterBill + staffSalary + transportCost;
        return totalExpenses + (totalExpenses * 0.1f);
    }

    public static ArrayList<Item> addItem(ArrayList<Item> items) {
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        String[] categories = {"Fruit", "Vegetable", "Meat", "Dairy", "Bakery", "Frozen", "Canned", "Condiments",  "Other"};

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

    public static void sellItem(ArrayList<Item> items) {
        printItems(items);
        System.out.println("Enter item name: ");
        String name = sc.nextLine();
        // find item in arraylist
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                System.out.println("Enter quantity to sell: ");
                int quantity = Integer.parseInt(sc.nextLine());
                if (quantity > item.getStock()) {
                    System.out.println("Not enough quantity to sell");
                    return;
                }
                item.sell(quantity);
                System.out.println("Item sold successfully");
                return;
            }
        }
        System.out.println("Item not found");
    }

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

    public static float generateNetIncome(ArrayList<Item> soldItems) {
        float grossIncome = generateGrossIncome(soldItems);
        float expenses = calculateExpenses();
        return grossIncome - expenses;
    }

    public static void printItems(ArrayList<Item> items) {
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n", "Name", "Category", "Supply Price", "Price", "Quantity", "Sold", "Net Profit");
        for (Item i : items) {
            System.out.printf("%-20s%-20s%-20.2f%-20.2f%-20s%-20s%-20.2f%n", i.getName(), i.getCategory(), i.getSupplyPrice(), i.getPrice(), i.getStock(), i.getSold(), i.getNetProfit());
        }
    }

    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<>();
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
            System.out.println("Welcome to the store!");
            System.out.println("[1] Add item");
            System.out.println("[2] Sell item");
            System.out.println("[3] Most sold item");
            System.out.println("[4] Least sold item");
            System.out.println("[5] Generate gross income");
            System.out.println("[6] Generate net income");
            System.out.println("[7] Print items");
            System.out.println("[0] Exit");
            System.out.print("Enter option: ");
            option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Add item into inventory");
                    items = addItem(items);
                    break;
                case 2:
                    System.out.println("Sell item");
                    sellItem(items);
                    break;
                case 3:
                    System.out.println("Most sold item");
                    ArrayList<Item> mostSold = mostSold(items);
                    printItems(mostSold);
                    break;
                case 4:
                    System.out.println("Least sold item");
                    ArrayList<Item> leastSold = leastSold(items);
                    printItems(leastSold);
                    break;
                case 5:
                    System.out.println("Generate gross income");
                    float grossIncome = generateGrossIncome(items);
                    System.out.printf("Gross income: %.2f%n", grossIncome);
                    break;
                case 6:
                    System.out.println("Generate net income");
                    float netIncome = generateNetIncome(items);
                    System.out.printf("Net income: %.2f%n", netIncome);
                    break;
                case 7:
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