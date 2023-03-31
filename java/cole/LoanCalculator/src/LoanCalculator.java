import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanCalculator extends JFrame {
    JLabel salaryLabel = new JLabel("Basic Monthly Salary:");
    static JTextField salaryField = new JTextField(10);
    JLabel monthsToPayLabel = new JLabel("Months To Pay:");
    static JTextField monthsToPayField = new JTextField(10);
    JLabel loanAmountLabel = new JLabel("Loan Amount:");
    static JTextField loanAmountField = new JTextField(10);
    JLabel interestLabel = new JLabel("Interest:");
    static JTextField interestField = new JTextField(10);
    JLabel serviceChargeLabel = new JLabel("Service Charge:");
    static JTextField serviceChargeField = new JTextField(10);
    JLabel takeHomeLoanLabel = new JLabel("Take Home Loan:");
    static JTextField takeHomeLoanField = new JTextField(10);
    JLabel monthlyAmortizationLabel = new JLabel("Monthly Amortization:");
    static JTextField monthlyAmortizationField = new JTextField(10);
    JButton computeButton = new JButton("Compute");

    public LoanCalculator() {
        super("Salary Loan System");

        loanAmountField.setEditable(false);
        interestField.setEditable(false);
        serviceChargeField.setEditable(false);
        takeHomeLoanField.setEditable(false);
        monthlyAmortizationField.setEditable(false);


        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(salaryLabel);
        panel.add(salaryField);
        panel.add(monthsToPayLabel);
        panel.add(monthsToPayField);
        panel.add(loanAmountLabel);
        panel.add(loanAmountField);
        panel.add(interestLabel);
        panel.add(interestField);
        panel.add(serviceChargeLabel);
        panel.add(serviceChargeField);
        panel.add(takeHomeLoanLabel);
        panel.add(takeHomeLoanField);
        panel.add(monthlyAmortizationLabel);
        panel.add(monthlyAmortizationField);
        panel.add(new JLabel(""));
        panel.add(computeButton);

        add(panel);

        // Set frame properties
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        salaryField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        monthsToPayField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });
    }

    public void action() {
        if (salaryField.getText().isEmpty() || monthsToPayField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill up all fields.");
            return;
        }

        double monthlySalary = Double.parseDouble(salaryField.getText());
        int monthsToPay = Integer.parseInt(monthsToPayField.getText());
        double loanableAmount = computeLoanableAmount(monthlySalary);
        double interest = computeInterest(monthsToPay, loanableAmount);

        if (interest == 0) {
            return;
        }

        double serviceCharge = loanableAmount * 0.02;
        double takeHomeLoan = computeTakeHomeLoan(loanableAmount, serviceCharge, interest);
        double monthlyAmortization = computeMonthlyAmortization(takeHomeLoan, monthsToPay);

        loanAmountField.setText(String.format("%,.2f", loanableAmount));
        interestField.setText(String.format("%,.2f", interest));
        serviceChargeField.setText(String.format("%,.2f", serviceCharge));
        takeHomeLoanField.setText(String.format("%,.2f", takeHomeLoan));
        monthlyAmortizationField.setText(String.format("%,.2f", monthlyAmortization));
    }

    public static double computeLoanableAmount(double monthlySalary) {
        return monthlySalary * 2.5;
    }

    public static double computeInterest(int monthsToPay, double loanAmount) {
        double interestRate = 0;
        switch (monthsToPay) {
            case 1, 2, 3, 4, 5 -> interestRate = 0.0062;
            case 6, 7, 8, 9, 10 -> interestRate = 0.0065;
            case 11, 12, 13, 14, 15 -> interestRate = 0.0068;
            case 16, 17, 18, 19, 20 -> interestRate = 0.0075;
            case 21, 22, 23, 24, 25 -> interestRate = 0.008;
            default -> JOptionPane.showMessageDialog(null, "Invalid months to pay.");
        }

        return loanAmount * interestRate * monthsToPay;
    }

    public static double computeTakeHomeLoan(double loanAmount, double serviceCharge, double interest) {
        return loanAmount - serviceCharge - interest;
    }

    public static double computeMonthlyAmortization(double takeHomeLoan, int monthsToPay) {
        return takeHomeLoan / monthsToPay;
    }

    public static void main(String[] args) {
        new LoanCalculator();
    }
}
