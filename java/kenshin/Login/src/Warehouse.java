import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Warehouse extends JFrame {

    Warehouse() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Warehouse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(300, 250);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Warehouse");
        label.setBounds(100, 50, 80, 25);
        panel.add(label);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(100, 100, 80, 25);
        panel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });

    }
}
