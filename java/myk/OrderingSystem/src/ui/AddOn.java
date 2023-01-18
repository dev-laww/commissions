/**
 * @authors: tora, hyuse
 */
package ui;

import utilities.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddOn {
    // encapsulated fields
    public static JFrame frame = new JFrame("KARINDERYA ORDERING SYSTEM");
    private final ArrayList<JPanel> components = new ArrayList<>();
    private final ArrayList<JSpinner> spinners = new ArrayList<>();
    public JPanel addOnPanel;
    private JButton searchButton;
    private JTextField search;
    private JSpinner cassavaSpinner;
    private JSpinner palitawSpinner;
    private JSpinner gulamanSpinner;
    private JSpinner bukoPieSpinner;
    private JSpinner mangoFloatSpinner;
    private JSpinner turonSpinner;
    private JSpinner polvoronSpinner;
    private JSpinner eggPieSpinner;
    private JSpinner bukoPandanSpinner;
    private JSpinner haloHaloSpinner;
    private JSpinner majaSpinner;
    private JButton addButton;
    private JButton backBtn;
    private JButton orderPreviewBtn;
    private JButton resetBtn;
    private JPanel cassavaComponent;
    private JPanel gulamanComponent;
    private JPanel mangoFloatComponent;
    private JPanel turonComponent;
    private JPanel polvoronComponent;
    private JPanel eggPieComponent;
    private JPanel bukoPandanComponent;
    private JPanel haloHaloComponent;
    private JPanel majaComponent;
    private JPanel palitawComponent;
    private JPanel bukoPieComponent;

    // Array list of spinners and components
    // the components and spinners are stored in ArrayList to reduce code redundancy
    {
        components.add(cassavaComponent);
        components.add(gulamanComponent);
        components.add(mangoFloatComponent);
        components.add(turonComponent);
        components.add(polvoronComponent);
        components.add(eggPieComponent);
        components.add(bukoPandanComponent);
        components.add(haloHaloComponent);
        components.add(majaComponent);
        components.add(palitawComponent);
        components.add(bukoPieComponent);

        spinners.add(cassavaSpinner);
        spinners.add(gulamanSpinner);
        spinners.add(mangoFloatSpinner);
        spinners.add(turonSpinner);
        spinners.add(polvoronSpinner);
        spinners.add(eggPieSpinner);
        spinners.add(bukoPandanSpinner);
        spinners.add(haloHaloSpinner);
        spinners.add(majaSpinner);
        spinners.add(palitawSpinner);
        spinners.add(bukoPieSpinner);
    }

    /**
     * Constructor for AddOn class
     * Sets the value of the spinners to 0
     * Adds action listeners to the buttons
     */
    public AddOn() {
        spinners.forEach(spinner -> spinner.setModel(new SpinnerNumberModel(0, 0, 10, 1)));

        backBtn.addActionListener(e -> {
            // Displays the main menu
            KarinderyaSystem.frame.setVisible(true);
            frame.dispose();
        });

        // when reset button is clicked, the value of the spinners are set to 0
        resetBtn.addActionListener(e -> spinners.forEach(spinner -> spinner.setValue(0)));

        orderPreviewBtn.addActionListener(e -> {
            // when order preview button is clicked, the order preview frame is displayed
            OrderPreview.display();
            frame.dispose();
        });

        addButton.addActionListener(e -> {
            // Add items to the cart
            for (int i = 0; i < spinners.size(); i++) {
                // skip if spinner value is 0
                if ((int) spinners.get(i).getValue() > 0) {
                    // create new item
                    Item item = new Item(
                            // get the name of the item
                            ((JLabel) components.get(i).getComponent(7)).getText(),
                            // get the type of the item
                            "Add On",
                            // get the prive of the item
                            Double.parseDouble(((JLabel) components.get(i).getComponent(4)).getText()),
                            // quantity
                            (int) spinners.get(i).getValue()
                    );

                    // check if item already exists in the cart
                    boolean exists = false;
                    // loop through the cart
                    for (Item karinderyaItem : KarinderyaSystem.items) {
                        // if item already exists, increase the quantity
                        if (karinderyaItem.getName().equals(item.getName())) {
                            karinderyaItem.setQuantity(item.getQuantity());
                            exists = true;
                            break;
                        }
                    }

                    // if item does not exist in the cart, add it
                    if (!exists) {
                        KarinderyaSystem.items.add(item);
                    }
                }

            }

            // reset spinners
            spinners.forEach(spinner -> spinner.setValue(0));
            // show success message
            JOptionPane.showMessageDialog(null, "Successfully added");
        });

        // search button action listener
        searchButton.addActionListener(e -> setVisibleComponent(search.getText()));
        search.addActionListener(e -> setVisibleComponent(search.getText()));
    }


    /**
     * Sets the visibility of the components based on the search text
     * @param searchText the text to search
     */
    private void setVisibleComponent(String searchText) {

        switch (searchText.toLowerCase()) {
            case "cassava" -> {
                // if component is found, set the visibility of the component to true and the rest to false
                for (JPanel comp : components) comp.setVisible(comp == cassavaComponent);
            }
            case "gulaman" -> {
                for (JPanel comp : components) comp.setVisible(comp == gulamanComponent);
            }
            case "mango float" -> {
                for (JPanel comp : components) comp.setVisible(comp == mangoFloatComponent);
            }
            case "turon" -> {
                for (JPanel comp : components) comp.setVisible(comp == turonComponent);
            }
            case "polvoron" -> {
                for (JPanel comp : components) comp.setVisible(comp == polvoronComponent);
            }
            case "egg pie" -> {
                for (JPanel comp : components) comp.setVisible(comp == eggPieComponent);
            }
            case "buko pandan" -> {
                for (JPanel comp : components) comp.setVisible(comp == bukoPandanComponent);
            }
            case "halo halo" -> {
                for (JPanel comp : components) comp.setVisible(comp == haloHaloComponent);
            }
            case "maja" -> {
                for (JPanel comp : components) comp.setVisible(comp == majaComponent);
            }
            case "palitaw" -> {
                for (JPanel comp : components) comp.setVisible(comp == palitawComponent);
            }
            case "buko pie" -> {
                for (JPanel comp : components) comp.setVisible(comp == bukoPieComponent);
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

    // display the frame
    public void display() {
        frame.setContentPane(new AddOn().addOnPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
