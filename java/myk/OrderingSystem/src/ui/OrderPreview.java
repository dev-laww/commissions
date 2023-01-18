/**
 * @authors: tora, hyuse
 */
package ui;

import utilities.Item;

import javax.swing.*;
import java.util.ArrayList;

public class OrderPreview {
    // encapsulated fields
    private JTextPane receiptPane;
    private JPanel orderPreviewPanel;
    private JButton backButton;
    public static JFrame frame = new JFrame("Receipt");

    /**
     * OrderPreview constructor
     */
    public OrderPreview() {
        // back button
        backButton.addActionListener(e -> {
            KarinderyaSystem.frame.setVisible(true);
            frame.dispose();
        });

        // set KarinderyaSystem.items items for easy access
        ArrayList<Item> items = KarinderyaSystem.items;
        int main = 0;
        int drink = 0;
        int addon = 0;

        // iterate through items
        for (Item item : items) {
            // define item type and count
            switch (item.getType()) {
                case "Main Dish" -> main++;
                case "Drinks" -> drink++;
                case "Add On" -> addon++;
            }
        }

        // Create receipt
        StringBuilder receipt = new StringBuilder();

        receipt.append("KARINDERYA ORDERING SYSTEM\n");
        receipt.append("Orders:\n");

        receipt.append(String.format("%20s%20s%20s%n", "Name", "Quantity", "Price"));
        receipt.append("------------------------------------------------------------\n");
        if (main > 0) {
            receipt.append("\nMain Dish:\n");
            for (Item item : KarinderyaSystem.items) {
                if (item.getType().equals("Main Dish")) {
                    String name = item.getName();
                    double price = item.getPrice();
                    int quantity = item.getQuantity();

                    receipt.append(String.format("%20s%20d%20.2f%n", name, quantity, price));
                }
            }
        }

        if (drink > 0) {
            receipt.append("\nDrinks:\n");
            for (Item item : KarinderyaSystem.items) {
                if (item.getType().equals("Drinks")) {
                    String name = item.getName();
                    double price = item.getPrice();
                    int quantity = item.getQuantity();

                    receipt.append(String.format("%20s%20d%20.2f%n", name, quantity, price));
                }
            }
        }

        if (addon > 0) {
            receipt.append("Add Ons:\n");
            for (Item item : KarinderyaSystem.items) {
                if (item.getType().equals("Add On")) {
                    String name = item.getName();
                    double price = item.getPrice();
                    int quantity = item.getQuantity();

                    receipt.append(String.format("%20s%20d%20.2f%n", name, quantity, price));
                }
            }
        }

        receipt.append("------------------------------------------------------------\n");
        receiptPane.setText(receipt.toString());
    }

    /**
     * Displays the OrderPreview frame
     */
    public static void display() {
        frame.setContentPane(new OrderPreview().orderPreviewPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setVisible(true);
    }
}
