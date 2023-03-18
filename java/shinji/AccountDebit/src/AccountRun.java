import java.util.Scanner;

public class AccountRun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account account1 = new Account();
        Account account2 = new Account();

        account1.setBalance(150.00);
        account2.setBalance(500.53);

        System.out.printf("account1 balance: $%.2f account2 balance: $%.2f\n", account1.getBalance(), account2.getBalance());

        System.out.print("Enter withdrawal amount for account1: ");
        double amount1 = scanner.nextDouble();
        account1.debit(amount1);
        System.out.printf("account balance: $%.2f account2 balance: $%.2f\n", account1.getBalance(), account2.getBalance());

        System.out.print("Enter withdrawal amount for account2: ");
        double amount2 = scanner.nextDouble();
        account2.debit(amount2);
        System.out.printf("account balance: $%.2f account2 balance: $%.2f\n", account1.getBalance(), account2.getBalance());
    }
}
