import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    private JTextField textField;
    private double firstNumber;
    private String operator;

    public Calculator() {
        // Set up the frame
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set up the text field
        textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setPreferredSize(new Dimension(200, 25));
        add(textField, BorderLayout.NORTH);

        // Set up the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(50, 50));
            buttonPanel.add(button);
            button.addActionListener(e -> buttonPressed(label));
        }
        add(buttonPanel, BorderLayout.CENTER);

        // Show the frame
        setVisible(true);
    }

    private void buttonPressed(String label) {
        if ("0123456789.".contains(label)) {
            // A number or decimal point was pressed
            textField.setText(textField.getText() + label);
            return;
        }

        if ("+-*/".contains(label)) {
            // An operator was pressed
            firstNumber = Double.parseDouble(textField.getText());
            operator = label;
            textField.setText("");
            return;
        }

        if ("=".equals(label)) {
            // The equals button was pressed
            double secondNumber = Double.parseDouble(textField.getText());
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    result = firstNumber / secondNumber;
                    break;
            }
            textField.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
