public class Account {
    private double balance;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void debit(double amount) {
        System.out.println(
                (amount > balance) ?
                        "Debit amount exceeded account balance." :
                        "subtracting " + amount + " from account balance"
        );

        if (amount < balance) balance -= amount;
    }
}
