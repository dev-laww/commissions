import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        float balance = 0, amount;
        int choice;
        String pin = "123456";
        String userinp;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("1. WITHDRAW");
            System.out.println("2. DEPOSIT");
            System.out.println("3. BALANCE");
            System.out.println("4. EXIT");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter your pin: ");
                    userinp = scanner.nextLine();
                    if (!pin.equals(userinp)) {
                        System.out.println("Wrong Pin!");
                        break;
                    }
                    System.out.print("Enter withdraw amount: ");
                    amount = Float.parseFloat(scanner.nextLine());
                    if (amount > balance)
                        System.out.println("Insufficient Amount");
                    else
                        balance -= amount;

                    break;
                case 2:
                    System.out.print("Enter your pin: ");
                    userinp = scanner.nextLine();
                    if (!pin.equals(userinp)) {
                        System.out.println("Wrong Pin!");
                        break;
                    }
                    System.out.print("Enter withdraw amount: ");
                    amount = Float.parseFloat(scanner.nextLine());
                    balance += amount;
                    break;
                case 3:
                    System.out.print("Enter your pin: ");
                    userinp = scanner.nextLine();
                    if (!pin.equals(userinp)) {
                        System.out.println("Wrong Pin!");
                        break;
                    }
                    System.out.println("Balance: " + balance);
                    break;
                case 4:
                    System.out.println("Thank you for using the program");
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (choice != 4);
    }
}
