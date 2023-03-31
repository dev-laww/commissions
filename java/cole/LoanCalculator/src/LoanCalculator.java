import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanCalculator extends JFrame {
    JLabel salaryLabel = new JLabel("Basic Monthly Salary:");
    JTextField salaryField = new JTextField(10);
    JLabel monthsToPayLabel = new JLabel("Months To Pay:");
    JTextField monthsToPayField = new JTextField(10);
    JLabel loanAmountLabel = new JLabel("Loan Amount:");
    JTextField loanAmountField = new JTextField(10);
    JLabel interestLabel = new JLabel("Interest:");
    JTextField interestField = new JTextField(10);
    JLabel serviceChargeLabel = new JLabel("Service Charge:");
    JTextField serviceChargeField = new JTextField(10);
    JLabel takeHomeLoanLabel = new JLabel("Take Home Loan:");
    JTextField takeHomeLoanField = new JTextField(10);
    JLabel monthlyAmortizationLabel = new JLabel("Monthly Amortization:");
    JTextField monthlyAmortizationField = new JTextField(10);
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

    public static void action() {

    }

    public static void main(String[] args) {
        new LoanCalculator();
    }
}
