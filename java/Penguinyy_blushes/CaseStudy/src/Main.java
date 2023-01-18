import java.util.Scanner;

public class Main {
    static final int INITIAL_BALANCE = 10000; // initial balance for all accounts
    static int MAX_ATTEMPTS = 3; // maximum number of attempts to enter login credentials
    static Scanner input = new Scanner(System.in); // scanner to read input from the user

    public static void main(String[] args) {
        int numRecords;
        String[] names;
        String[] accountNumbers;
        String[] passwords;
        String[] pinCodes;
        double[] balances;

        // ask the user for the number of records
        System.out.print("Enter the number of records: ");
        while (true) {
            try {
                numRecords = input.nextInt();
                if (numRecords <= 0) {
                    System.out.print("Enter a positive number!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Input mismatch!");
                System.out.print("[C]ontinue or [S]top the App: ");
                String response = input.next();
                if (response.equalsIgnoreCase("c")) {
                    numRecords = 3;
                    break;
                } else {
                    System.out.println("End of Program");
                    return;
                }
            }
        }
        System.out.println("Adding Records");
        System.out.println("-----------------------------------------");


        names = new String[numRecords];
        accountNumbers = new String[numRecords];
        passwords = new String[numRecords];
        pinCodes = new String[numRecords];
        balances = new double[numRecords];

        // ask the user to input data for each record
        for (int i = 0; i < numRecords; i++) {
            System.out.println("Record        : " + (i + 1));
            System.out.print("Client Name   : ");
            names[i] = input.next();

            // generate a unique account number for this record
            accountNumbers[i] = generateAccountNumber(accountNumbers, i);
            System.out.println("Account Number: " + accountNumbers[i]);

            System.out.print("Password      : ");
            passwords[i] = input.next();

            System.out.print("PIN code      : ");
            pinCodes[i] = input.next();

            balances[i] = INITIAL_BALANCE;
            if (i != numRecords - 1) System.out.println("-----------------------------------------");
        }

        // main loop
        while (true) {
            System.out.println("-----------------------------------------");
            System.out.println("             MyBank Main Menu");
            System.out.println("-----------------------------------------");
            System.out.print("[L]og [E]xit Select Transaction: ");
            String response = input.next();

            if (!(response.equalsIgnoreCase("l") || response.equalsIgnoreCase("e"))) {
                System.out.println("-----------------------------------------");
                System.out.println("Invalid selection!");
                continue;
            }

            if (response.equalsIgnoreCase("e")) {
                System.out.println("-----------------------------------------");
                System.out.println("End of Program");
                break;
            }

            int index = -1;

            while (true) {
                System.out.print("Account No  : ");
                String accountNumber = input.next();

                System.out.print("Password    : ");
                String password = input.next();


                // try to find a record with the given account number and password
                boolean found = false;
                for (int i = 0; i < numRecords; i++) {
                    if (accountNumbers[i].equalsIgnoreCase(accountNumber) && passwords[i].equalsIgnoreCase(password)) {
                        index = i;
                        found = true;
                        break;
                    }
                }

                if (found) break;

                // no matching record found, ask the user to try again
                System.out.printf("%d Attempt(s) left%n", --MAX_ATTEMPTS);
                if (MAX_ATTEMPTS == 0) {
                    System.out.println(">>> Transaction Reported");
                    System.out.println("-----------------------------------------");
                    break;
                }

            }

            // check if a matching record was found
            if (index == -1) continue;

            System.out.println("-----------------------------------------");
            System.out.println("      MyBank Automated Teller Machine");
            System.out.println("-----------------------------------------");
            System.out.println("Hi " + names[index] + "!");
            System.out.println("Current Balance: " + balances[index]);
            System.out.println("[W]ithdraw, [D]eposit, [T]ransfer, [E]nd");

            // main menu loop
            while (true) {
                System.out.println("-----------------------------------------");
                System.out.print("Select Transaction: ");
                String cmd = input.next();
                switch (cmd.toLowerCase()) {
                    case "w":
                        // withdraw command
                        System.out.print("Enter amount to withdraw: ");
                        double amount;
                        try {
                            amount = input.nextDouble();
                        } catch (Exception e) {
                            System.out.println(">>> Invalid transaction");
                            continue;
                        }
                        if (amount > balances[index]) {
                            System.out.println(">>> Insufficient fund");
                            continue;
                        }
                        System.out.print("Enter PIN code: ");
                        String pinCode = input.next();
                        if (pinCodes[index].equalsIgnoreCase(pinCode)) {
                            balances[index] -= amount;
                            System.out.println("Current balance: " + balances[index]);
                        } else {
                            System.out.println(">>> Wrong PIN code");
                        }
                        break;
                    case "d":
                        // deposit command
                        System.out.print("Enter amount to deposit: ");
                        try {
                            amount = input.nextDouble();
                        } catch (Exception e) {
                            System.out.println(">>> Invalid transaction");
                            continue;
                        }
                        balances[index] += amount;
                        System.out.println("Current balance: " + balances[index]);
                        break;
                    case "t":
                        // transfer command
                        System.out.print("Account Number    : ");
                        String recipientAccountNumber = input.next();
                        int recipientIndex = -1;
                        for (int i = 0; i < numRecords; i++) {
                            if (accountNumbers[i].equalsIgnoreCase(recipientAccountNumber)) {
                                recipientIndex = i;
                                break;
                            }
                        }
                        if (recipientIndex == -1) {
                            System.out.println(">>> Rejected! Account does not exist");
                            continue;
                        }
                        System.out.print("Amount to transfer: ");
                        try {
                            amount = input.nextDouble();
                        } catch (Exception e) {
                            System.out.println(">>> Invalid transaction");
                            continue;
                        }
                        if (amount > balances[index]) {
                            System.out.println(">>> Insufficient fund");
                            continue;
                        }
                        System.out.print("Enter PIN code: ");
                        pinCode = input.next();
                        if (pinCodes[index].equalsIgnoreCase(pinCode)) {
                            balances[index] -= amount;
                            balances[recipientIndex] += amount;
                            System.out.println("Current balance: " + balances[index]);
                        } else {
                            System.out.println(">>> Wrong PIN code");
                        }
                        break;
                    case "e":
                        // end command
                        break;
                    default:
                        // invalid command
                        System.out.println(">>> Invalid transaction");
                }
                if (cmd.equalsIgnoreCase("e")) break;
            }
        }
    }

    // generates a unique account number for a record
    static String generateAccountNumber(String[] accountNumbers, int numAccounts) {
        StringBuilder accountNumber;
        do {
            accountNumber = new StringBuilder("ACC-");
            accountNumber.append("000-");
            accountNumber.append(numAccounts + 1);
        } while (isAccountNumberDuplicate(accountNumber.toString(), accountNumbers, numAccounts));
        return accountNumber.toString();
    }

    // returns true if the given account number is a duplicate
    static boolean isAccountNumberDuplicate(String accountNumber, String[] accountNumbers, int numAccounts) {
        for (int i = 0; i < numAccounts; i++) {
            if (accountNumbers[i].equalsIgnoreCase(accountNumber)) {
                return true;
            }
        }
        return false;
    }
}