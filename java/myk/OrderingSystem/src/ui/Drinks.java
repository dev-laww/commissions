/**
 * @authors: tora, hyuse
 */
package ui;

import utilities.Item;

import javax.swing.*;
import java.util.ArrayList;

public class Drinks {
    // encapsulated fields
    public static JFrame frame = new JFrame("KARINDERYA ORDERING SYSTEM");
    private final ArrayList<JSpinner> spinners = new ArrayList<>();
    private final ArrayList<JPanel> components = new ArrayList<>();
    private JSpinner royalSpinner;
    private JSpinner cokeSpinner;
    private JSpinner waterSpinner;
    private JSpinner spriteSpinner;
    private JButton orderPreview;
    private JButton resetButton;
    private JButton backButton;
    private JButton addButton;
    private JButton searchButton;
    private JTextField search;
    private JPanel waterComponent;
    private JPanel royalComponent;
    private JPanel spriteComponent;
    private JPanel cokeComponent;
    private JPanel drinksPanel;

    // Array list of spinners and components
    {
        spinners.add(royalSpinner);
        spinners.add(cokeSpinner);
        spinners.add(waterSpinner);
        spinners.add(spriteSpinner);

        components.add(royalComponent);
        components.add(cokeComponent);
        components.add(waterComponent);
        components.add(spriteComponent);
    }


    /**
     * Drink constructor
     */
    public Drinks() {
        // set spinner values to 0
        spinners.forEach(spinner -> spinner.setModel(new SpinnerNumberModel(0, 0, 10, 1)));

        orderPreview.addActionListener(e -> {
            // display order preview
            OrderPreview.display();
            frame.dispose();
        });

        resetButton.addActionListener(e -> spinners.forEach(spinner -> spinner.setValue(0)));

        backButton.addActionListener(e -> {
            // display main menu
            KarinderyaSystem.frame.setVisible(true);
            frame.setVisible(false);
        });


        addButton.addActionListener(e -> {
            // add items to cart
            for (int i = 0; i < spinners.size(); i++) {
                // check if spinner value is not 0
                if ((int) spinners.get(i).getValue() > 0) {
                    // create new item
                    Item item = new Item(
                            // get component name
                            ((JLabel) components.get(i).getComponent(8)).getText(),
                            // get component type
                            "Drinks",
                            // get component price
                            Double.parseDouble(((JLabel) components.get(i).getComponent(3)).getText()),
                            // get spinner value for quantity
                            (int) spinners.get(i).getValue()
                    );

                    // check if item already exists in cart
                    boolean exists = false;
                    for (Item karinderyaItem : KarinderyaSystem.items) {
                        // if item exists, increase quantity
                        if (karinderyaItem.getName().equals(item.getName())) {
                            karinderyaItem.setQuantity(item.getQuantity());
                            exists = true;
                            break;
                        }
                    }
                    // if item does not exist in cart, add it
                    if (!exists) {
                        KarinderyaSystem.items.add(item);
                    }
                }
            }

            // reset spinners
            spinners.forEach(spinner -> spinner.setValue(0));
            // display success message
            JOptionPane.showMessageDialog(null, "Successfully added");
        });

        // search listener
        search.addActionListener(e -> setVisibleComponent(search.getText()));
        searchButton.addActionListener(e -> setVisibleComponent(search.getText()));
    }

    /**
     * Set visible component
     * @param searchText search text
     */
    private void setVisibleComponent(String searchText) {

        switch (searchText.toLowerCase()) {
            case "royal" -> {
                // set visibility of components
                for (JPanel comp : components) comp.setVisible(comp == royalComponent);
            }
            case "coke" -> {
                for (JPanel comp : components) comp.setVisible(comp == cokeComponent);
            }
            case "sprite" -> {
                for (JPanel comp : components) comp.setVisible(comp == spriteComponent);
            }
            case "water" -> {
                for (JPanel comp : components) comp.setVisible(comp == waterComponent);
            }
            // if search text is empty, show all components
            default -> {
                if (searchText.equals("")) {
                    for (JPanel comp : components) comp.setVisible(true);
                    break;
                }
                JOptionPane.showMessageDialog(null, "Item not found!");
            }
        }
    }

    public void display() {
        frame.setContentPane(new Drinks().drinksPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
