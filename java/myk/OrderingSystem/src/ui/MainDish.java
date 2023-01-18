/**
 * @authors: tora, hyuse
 */
package ui;

import utilities.Item;

import javax.swing.*;
import java.util.ArrayList;

public class MainDish {
    // encapsulated fields
    public static JFrame frame = new JFrame("KARINDERYA ORDERING SYSTEM");
    private final ArrayList<JSpinner> spinners = new ArrayList<>();
    private final ArrayList<JPanel> components = new ArrayList<>();
    public JPanel mainDishPanel;
    private JTextField search;
    private JPanel menudoComponent;
    private JPanel bbqComponent;
    private JPanel karekareComponent;
    private JPanel ginilingComponent;
    private JButton addButton;
    private JButton backButton;
    private JButton resetButton;
    private JPanel sweetNSourComponent;
    private JPanel pansitBihonComponent;
    private JPanel ginataangGulayComponent;
    private JPanel ginataangAlimasagComponent;
    private JPanel pakbetComponent;
    private JPanel tinolaComponent;
    private JPanel monggoComponent;
    private JPanel pansitComponent;
    private JSpinner ginilingSpinner;
    private JSpinner sweetNSourFishSpinner;
    private JSpinner pansitBihonSpinner;
    private JSpinner menudoSpinner;
    private JSpinner bbqSpinner;
    private JSpinner kareKareSpinner;
    private JSpinner tinolaSpinner;
    private JSpinner monggoSpinner;
    private JSpinner pansitSpinner;
    private JSpinner ginataangGulaySpinner;
    private JSpinner ginataangAlimasagSpinner;
    private JSpinner pakbetSpinner;
    private JButton orderPreviewButton;
    private JButton searchButton;
    // Arraylist of component and spinners
    {
        components.add(menudoComponent);
        components.add(bbqComponent);
        components.add(karekareComponent);
        components.add(ginilingComponent);
        components.add(sweetNSourComponent);
        components.add(pansitBihonComponent);
        components.add(ginataangGulayComponent);
        components.add(ginataangAlimasagComponent);
        components.add(pakbetComponent);
        components.add(tinolaComponent);
        components.add(monggoComponent);
        components.add(pansitComponent);

        spinners.add(menudoSpinner);
        spinners.add(bbqSpinner);
        spinners.add(kareKareSpinner);
        spinners.add(ginilingSpinner);
        spinners.add(sweetNSourFishSpinner);
        spinners.add(pansitBihonSpinner);
        spinners.add(ginataangGulaySpinner);
        spinners.add(ginataangAlimasagSpinner);
        spinners.add(pakbetSpinner);
        spinners.add(tinolaSpinner);
        spinners.add(monggoSpinner);
        spinners.add(pansitSpinner);
    }

    /**
     * MainDish constructor
     */
    public MainDish() {
        // set spinner values to 0
        spinners.forEach(spinner -> spinner.setModel(new SpinnerNumberModel(0, 0, 10, 1)));

        // display main menu
        backButton.addActionListener(e -> {
            KarinderyaSystem.frame.setVisible(true);
            frame.dispose();
        });


        // display order preview
        orderPreviewButton.addActionListener(e -> {
            OrderPreview.display();
            frame.dispose();
        });

        // set spinner values to 0
        resetButton.addActionListener(e -> spinners.forEach(spinner -> spinner.setValue(0)));

        // add items to cart
        addButton.addActionListener(e -> {
            // loop through components
            for (int i = 0; i < spinners.size(); i++) {
                // get spinner value if it is not 0
                if ((int) spinners.get(i).getValue() > 0) {
                    // create new item
                    Item item = new Item(
                            // get component name
                            ((JLabel) components.get(i).getComponent(8)).getText(),
                            // get component type
                            "Main Dish",
                            // get component price
                            Double.parseDouble(((JLabel) components.get(i).getComponent(3)).getText()),
                            // get spinner value for quantity
                            (int) spinners.get(i).getValue()
                    );

                    // check if item already exists in cart
                    boolean exists = false;
                    for (Item karinderyaItem : KarinderyaSystem.items) {
                        // if item exists, add quantity
                        if (karinderyaItem.getName().equals(item.getName())) {
                            karinderyaItem.setQuantity(item.getQuantity());
                            exists = true;
                            break;
                        }
                    }

                    // if item does not exist, add to cart
                    if (!exists) {
                        KarinderyaSystem.items.add(item);
                    }

                }
            }

            // reset spinner values
            spinners.forEach(spinner -> spinner.setValue(0));
            // show message
            JOptionPane.showMessageDialog(null, "Successfully added");
        });

        // search listener
        search.addActionListener(e -> setVisibleComponents(search.getText()));
        searchButton.addActionListener(e -> setVisibleComponents(search.getText()));
    }

    /**
     * Set visible components
     *
     * @param searchText search text
     */
    public void setVisibleComponents(String searchText) {

        switch (searchText.toLowerCase()) {
            case "barbeque" -> {
                // set only bbq component visible
                for (JPanel comp : components) {
                    comp.setVisible(comp == bbqComponent);
                }
            }
            case "kare-kare", "karekare" -> {
                for (JPanel comp : components) {
                    comp.setVisible(comp == karekareComponent);
                }
            }
            case "menudo" -> {
                for (JPanel comp : components) {
                    comp.setVisible(comp == menudoComponent);
                }
            }
            case "sweet and sour", "sweet&sour" -> {
                for (JPanel comp : components) {
                    comp.setVisible(comp == sweetNSourComponent);
                }
            }
            case "pansit bihon", "pansitbihon" -> {
                for (JPanel comp : components) {
                    comp.setVisible(comp == pansitBihonComponent);
                }
            }
            case "giniling" -> {
                for (JPanel comp : components) {
                    comp.setVisible(comp == ginilingComponent);
                }
            }
            case "tinola" -> {
                for (JPanel comp : components) {
                    comp.setVisible(comp == tinolaComponent);
                }
            }
            case "monggo" -> {
                for (JPanel comp : components) {
                    comp.setVisible(comp == monggoComponent);
                }
            }
            case "pansit" -> {
                for (JPanel comp : components) {
                    comp.setVisible(comp == pansitComponent);
                }
            }
            case "ginataang gulay", "ginataanggulay" -> {
                for (JPanel comp : components) {
                    comp.setVisible(comp == ginataangGulayComponent);
                }
            }
            case "ginataang alimasag", "ginataangalimasag" -> {
                for (JPanel comp : components) {
                    comp.setVisible(comp == ginataangAlimasagComponent);
                }
            }
            case "pakbet" -> {
                for (JPanel comp : components) {
                    comp.setVisible(comp == pakbetComponent);
                }
            }
            default -> {
                // if search text is empty, set all components visible
                if (searchText.equals("")) {
                    for (JPanel comp : components) comp.setVisible(true);
                    break;
                }
                // if search text is not empty, show not found message
                JOptionPane.showMessageDialog(null, "Item not found!");
            }
        }
    }

    /**
     * Display main dish menu
     */
    public void display() {
        frame.setContentPane(new MainDish().mainDishPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}


