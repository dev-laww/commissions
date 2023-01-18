package ui;

import utilities.Item;

import javax.swing.*;
import java.util.ArrayList;

public class KarinderyaSystem {
    // encapsulated fields
    public static final JFrame frame = new JFrame("KARINDERYA ORDERING SYSTEM");
    private final MainDish mainDish = new MainDish();
    private final AddOn addon = new AddOn();
    private final Drinks drinks = new Drinks();
    public static ArrayList<Item> items = new ArrayList<>();
    public JPanel main;
    private JButton mainDishButton;
    private JButton addonsButton;
    private JButton drinksButton;
    private JButton receiptButon;

    /**
     * KarinderyaSystem constructor
     */
    public KarinderyaSystem() {
        // button listeners
        mainDishButton.addActionListener(e -> {
            mainDish.display();
            frame.setVisible(false);
        });

        addonsButton.addActionListener(e -> {
            addon.display();
            frame.setVisible(false);
        });

        drinksButton.addActionListener(e -> {
            drinks.display();
            frame.setVisible(false);
        });

        receiptButon.addActionListener(e -> {
            Receipt.display();
            frame.setVisible(false);
        });
    }

    /**
     * Starts the program
     */
    public void start() {
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setVisible(true);
    }
}