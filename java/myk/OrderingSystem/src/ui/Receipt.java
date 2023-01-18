package ui;

import utilities.Item;

import javax.swing.*;
import java.util.ArrayList;

public class Receipt {
    private JTextPane receiptPane;
    private JPanel receiptPanel;

    /**
     * Displays the receipt.
     */
    public Receipt() {
        ArrayList<Item> items = KarinderyaSystem.items;
        int main = 0;
        int drink = 0;
        int addon = 0;
        double total = 0;

        // Loop through the items and add the price to the total
        for (Item item : items) {
            switch (item.getType()) {
                case "Main Dish" -> main++;
                case "Drinks" -> drink++;
                case "Add On" -> addon++;
            }

            total += item.getPrice() * item.getQuantity();
        }

        // Create the receipt
        StringBuilder receipt = new StringBuilder();

        receipt.append("KARINDERYA ORDERING SYSTEM\n");
        receipt.append("Orders:\n");

        // String format for the receipt makes it easier to read
        receipt.append(String.format("%20s%20s%20s%20s%n", "Name", "Quantity", "Price", "Total"));
        receipt.append("--------------------------------------------------------------------------------\n");
        // check if main count is not 0
        if (main > 0) {
            // Loop through the items and add the main dish items to the receipt
            receipt.append("\nMain Dish:\n");
            for (Item item : KarinderyaSystem.items) {
                if (item.getType().equals("Main Dish")) {
                    String name = item.getName();
                    double price = item.getPrice();
                    int quantity = item.getQuantity();
                    double subTotal = price * quantity;

                    receipt.append(String.format("%20s%20d%20.2f%20.2f%n", name, quantity, price, subTotal));
                }
            }
        }

        // check if addon count is not 0
        if (drink > 0) {
            // Loop through the items and add the drinks items to the receipt
            receipt.append("\nDrinks:\n");
            for (Item item : KarinderyaSystem.items) {
                if (item.getType().equals("Drinks")) {
                    String name = item.getName();
                    double price = item.getPrice();
                    int quantity = item.getQuantity();
                    double subTotal = price * quantity;

                    receipt.append(String.format("%20s%20d%20.2f%20.2f%n", name, quantity, price, subTotal));
                }
            }
        }


        // check if addon count is not 0
        if (addon > 0) {
            // Loop through the items and add the addon items to the receipt
            receipt.append("Add Ons:\n");
            for (Item item : KarinderyaSystem.items) {
                if (item.getType().equals("Add On")) {
                    String name = item.getName();
                    double price = item.getPrice();
                    int quantity = item.getQuantity();
                    double subTotal = price * quantity;

                    receipt.append(String.format("%20s%20d%20.2f%20.2f%n", name, quantity, price, subTotal));
                }
            }
        }

        receipt.append("--------------------------------------------------------------------------------\n");
        // Add the total to the receipt
        receipt.append(String.format("%20s%20s%20s%20s%n", "", "", "Total", total));
        receiptPane.setText(receipt.toString());
    }

    /**
     * Displays the receipt.
     */
    public static void display() {
        JFrame frame = new JFrame("Receipt");
        frame.setContentPane(new Receipt().receiptPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setVisible(true);
    }
}
