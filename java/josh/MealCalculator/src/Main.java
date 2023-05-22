import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame {
    private JRadioButton mealA;
    private JRadioButton mealB;
    private JRadioButton mealC;
    private JRadioButton regularSizeRadioButton;
    private JRadioButton upsizeRadioButton;
    private JCheckBox extraRiceCheckBox;
    private JCheckBox saladCheckBox;
    private JCheckBox soupCheckBox;
    private JCheckBox dessertCheckBox;
    private JButton calculateButton;
    private JLabel grossCostLabel;
    private JLabel netCostLabel;
    private JTextField quantityTextField;
    private static final double VAT_RATE = 0.08;
    private Map<String, Double[]> mealPrices = new HashMap<>();

    public Main() {
        setTitle("Meal Cost Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mealPrices.put("A", new Double[]{78.75, 102.50});
        mealPrices.put("B", new Double[]{68.75, 85.70});
        mealPrices.put("C", new Double[]{70.25, 95.60});

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Meal Type
        JLabel mealTypeLabel = new JLabel("Meal Type:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(mealTypeLabel, gbc);

        mealA = new JRadioButton("A");
        mealB = new JRadioButton("B");
        mealC = new JRadioButton("C");

        JPanel mealTypePanel = new JPanel();
        mealTypePanel.add(mealA);
        mealTypePanel.add(mealB);
        mealTypePanel.add(mealC);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(mealTypePanel, gbc);

        // Size
        JLabel sizeLabel = new JLabel("Size:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        mainPanel.add(sizeLabel, gbc);

        regularSizeRadioButton = new JRadioButton("Regular (Default)");
        upsizeRadioButton = new JRadioButton("Upsize");
        ButtonGroup sizeButtonGroup = new ButtonGroup();
        sizeButtonGroup.add(regularSizeRadioButton);
        sizeButtonGroup.add(upsizeRadioButton);

        JPanel sizePanel = new JPanel();
        sizePanel.add(regularSizeRadioButton);
        sizePanel.add(upsizeRadioButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(sizePanel, gbc);

        // Side Orders
        JLabel sideOrdersLabel = new JLabel("Side Orders:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(sideOrdersLabel, gbc);

        extraRiceCheckBox = new JCheckBox("Extra Rice");
        saladCheckBox = new JCheckBox("Salad");
        soupCheckBox = new JCheckBox("Soup");
        dessertCheckBox = new JCheckBox("Dessert");

        JPanel sideOrdersPanel = new JPanel();
        sideOrdersPanel.setLayout(new GridLayout(2, 2, 5, 5));
        sideOrdersPanel.add(extraRiceCheckBox);
        sideOrdersPanel.add(saladCheckBox);
        sideOrdersPanel.add(soupCheckBox);
        sideOrdersPanel.add(dessertCheckBox);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(sideOrdersPanel, gbc);

        // Quantity
        JLabel quantityLabel = new JLabel("Quantity:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(quantityLabel, gbc);

        quantityTextField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(quantityTextField, gbc);

        // Calculate Button
        calculateButton = new JButton("Calculate");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(calculateButton, gbc);

        // Gross Cost
        grossCostLabel = new JLabel("Gross Cost: ");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(grossCostLabel, gbc);

        // Net Cost
        netCostLabel = new JLabel("Net Cost: ");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(netCostLabel, gbc);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCost();
            }
        });

        add(mainPanel);
        pack();
        setVisible(true);
    }

    private void calculateCost() {
        ArrayList<String> selectedMealTypes = new ArrayList<>();

        if (mealA.isSelected()) {
            selectedMealTypes.add("A");
        }
        if (mealB.isSelected()) {
            selectedMealTypes.add("B");
        }
        if (mealC.isSelected()) {
            selectedMealTypes.add("C");
        }

        double totalGrossCost = 0.0;
        double totalNetCost;

        for (String mealCode : selectedMealTypes) {
            double mealCost = getMealCost(mealCode);
            double sizeMultiplier = (regularSizeRadioButton.isSelected()) ? 1.0 : 1.3;
            double totalCost = mealCost * sizeMultiplier;

            totalGrossCost += totalCost;
        }

        double sideOrdersCost = getSideOrdersCost();
        totalGrossCost += sideOrdersCost;

        if (totalGrossCost > 2000) {
            totalGrossCost *= 0.88;
        }

        totalNetCost = totalGrossCost + (totalGrossCost * VAT_RATE);


        grossCostLabel.setText("Gross Cost: " + String.format("%.2f", totalGrossCost));
        netCostLabel.setText("Net Cost: " + String.format("%.2f", totalNetCost));
    }

    private double getMealCost(String mealCode) {
        if (mealPrices.containsKey(mealCode)) {
            Double[] prices = mealPrices.get(mealCode);
            return prices[0];
        }
        return 0.0;
    }

    private double getSideOrdersCost() {
        double sideOrdersCost = 0.0;
        if (extraRiceCheckBox.isSelected()) {
            sideOrdersCost += 20.0;
        }
        if (saladCheckBox.isSelected()) {
            sideOrdersCost += 60.0;
        }
        if (soupCheckBox.isSelected()) {
            sideOrdersCost += 55.0;
        }
        if (dessertCheckBox.isSelected()) {
            sideOrdersCost += 85.0;
        }
        return sideOrdersCost * Integer.parseInt(quantityTextField.getText());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
