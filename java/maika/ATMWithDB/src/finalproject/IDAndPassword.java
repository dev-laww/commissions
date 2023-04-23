/**
 * @author:  tora
 * @author:  niku
 */
package finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IDAndPassword {
    private final HashMap<String, String> ADMINS = new HashMap<>();
    private final ArrayList<Customer> customers = new ArrayList<>();

    {
        ADMINS.put("admin", "admin");
        Customer customer = new Customer("Maika", "Bustos, Bulacan", "ybiza2018@gmail.com", "09225049004","123456789123", "0000");
        customer.deposit(50000);
        customer.addTransaction(new HashMap<>(Map.of("Deposit", 1000.0)));
        customer.addTransaction(new HashMap<>(Map.of("Deposit", -1000.0)));
        customer.addTransaction(new HashMap<>(Map.of("Transfer", -1000.0)));

        Customer customer2 = new Customer("Oreo", "Baliwag, Bulacan", "oreo@gmail.com", "684652231","987654321123", "0000");
        customer2.deposit(50000);
        customer2.addTransaction(new HashMap<>(Map.of("Deposit", 1000.0)));
        customer2.addTransaction(new HashMap<>(Map.of("Transfer", -1000.0)));
        customers.add(customer);
        customers.add(customer2);
    }

    public HashMap<String, String> getTellerLoginInfo() {
        return this.ADMINS;
    }

    public ArrayList<Customer> getCustomerLoginInfo() {
        return this.customers;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void delete(String accountID) {
        for (Customer customer : customers) {
            if (customer.getAccountID().equals(accountID)) {
                customers.remove(customer);
                break;
            }
        }
    }
}
