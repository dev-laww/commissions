package project_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {
    private final HashMap<String, String> ADMINS = new HashMap<>();
    private final ArrayList<Customer> customers = new ArrayList<>();

    {
        ADMINS.put("admin", "admin");

        Customer customer = new Customer("Default Customer", "email", "132456789", "123456789123", "0000");
        customer.deposit(50000);
        customer.addTransaction(new HashMap<>(Map.of("Deposit", 1000.0)));
        customer.addTransaction(new HashMap<>(Map.of("Deposit", -1000.0)));
        customer.addTransaction(new HashMap<>(Map.of("Transfer", -1000.0)));

        Customer customer2 = new Customer("Default Customer2", "email2", "684652231", "987654321123", "0000");
        customer2.deposit(50000);
        customer2.addTransaction(new HashMap<>(Map.of("Deposit", 1000.0)));
        customer2.addTransaction(new HashMap<>(Map.of("Transfer", -1000.0)));
        customers.add(customer);
        customers.add(customer2);
    }

    public HashMap<String, String> getAdmins() {
        return this.ADMINS;
    }

    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public Object[][] getCustomerData() {
        Object[][] data = new Object[customers.size()][3];
        for (int i = 0; i < customers.size(); i++) {
            data[i][0] = customers.get(i).getName();
            data[i][1] = customers.get(i).getAccountID();
            data[i][2] = customers.get(i).getBalance();
        }
        return data;
    }

    public Object[][] getLockedAccounts() {
        Object[][] temp = new Object[customers.size()][3];
        int counter = 0;
        for (Customer customer : customers) {
            if (customer.isLocked()) {
                temp[counter][0] = customer.getName();
                temp[counter][1] = customer.getAccountID();
                temp[counter][2] = customer.getBalance();
                counter++;
            }
        }

        Object[][] data = new Object[counter][3];
        for (int i = 0; i < counter; i++) {
            data[i][0] = temp[i][0];
            data[i][1] = temp[i][1];
            data[i][2] = temp[i][2];
        }

        if (counter == 0) {
            data = new Object[1][3];
            data[0][0] = "No Locked Accounts";
            data[0][1] = "";
            data[0][2] = "";
        }

        return data;
    }

    public boolean accountExists(String accountID) {
        for (Customer customer : customers) {
            if (customer.getAccountID().equals(accountID)) {
                return false;
            }
        }
        return true;
    }

    public Customer getCustomer(String accountID) {
        for (Customer customer : customers) {
            if (customer.getAccountID().equals(accountID)) {
                return customer;
            }
        }
        return null;
    }

    public void removeCustomer(String accountID) {
        for (Customer customer : customers) {
            if (customer.getAccountID().equals(accountID)) {
                customers.remove(customer);
                break;
            }
        }
    }
}
