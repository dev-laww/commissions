import javax.swing.*;

public class Ui {
    public static void main(String[] args) {
        int choice = Integer.parseInt(
                JOptionPane.showInputDialog("""
                        Menu:
                        [1] Meal A(P100.00)
                        [2] Meal B(P150.00)
                        Enter Order:""")
        );

        switch (choice) {
            case 1:
                int quantity = Integer.parseInt(
                        JOptionPane.showInputDialog("""
                                Enter Quantity:""")
                );
                int customer = Integer.parseInt(
                        JOptionPane.showInputDialog("""
                                Type of Customer:
                                [1] Regular
                                [2] Senior Citizen(20% Discount)
                                Enter Customer:""")
                );
                double total = quantity * 100.00;
                if (customer == 1) {
                    JOptionPane.showMessageDialog(null, "Your Total Order is: " + total);
                    double payment = Double.parseDouble(
                            JOptionPane.showInputDialog("""
                                    Enter Payment:""")
                    );
                    double change = payment - total;
                    JOptionPane.showMessageDialog(null, "Your change is: " + change);
                } else if (customer == 2) {
                    double total2 = total - (total * .20);
                    JOptionPane.showMessageDialog(null, "Your Total Order is: " + total2);
                    double payment = Double.parseDouble(
                            JOptionPane.showInputDialog("""
                                    Enter Payment:""")
                    );
                    double change = payment - total2;
                    JOptionPane.showMessageDialog(null, "Your change is: " + change);
                }
                break;
            case 2:
                int quantity2 = Integer.parseInt(
                        JOptionPane.showInputDialog("""
                                Enter Quantity:""")
                );
                int customer2 = Integer.parseInt(
                        JOptionPane.showInputDialog("""
                                Type of Customer:
                                [1] Regular
                                [2] Senior Citizen(20% Discount)
                                Enter Customer:""")
                );
                double total3 = quantity2 * 150.00;
                if (customer2 == 1) {
                    JOptionPane.showMessageDialog(null, "Your Total Order is: " + total3);
                    double payment = Double.parseDouble(
                            JOptionPane.showInputDialog("""
                                    Enter Payment:""")
                    );
                    double change = payment - total3;
                    JOptionPane.showMessageDialog(null, "Your change is: " + change);
                } else if (customer2 == 2) {
                    double total4 = total3 - (total3 * .20);
                    JOptionPane.showMessageDialog(null, "Your Total Order is: " + total4);
                    double payment = Double.parseDouble(
                            JOptionPane.showInputDialog("""
                                    Enter Payment:""")
                    );
                    double change = payment - total4;
                    JOptionPane.showMessageDialog(null, "Your change is: " + change);
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid Input");
        }
    }
}
