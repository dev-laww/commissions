import mpfileiocollectionhealthclub.Account;
import mpfileiocollectionhealthclub.Person;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        Map<String, Person> personMap = new HashMap<>();
        try {
            FileInputStream fileIn = new FileInputStream("src/acct.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            Account obj = (Account) in.readObject();


            fileIn = new FileInputStream("src/mapMP3.txt");
            accounts.add(obj);

            String[][] datas = {
                    {"567", "R", "Q", "1"},
                    {"456", "V", "C", "3"},
                    {"123", "V", "S", "2"},
                    {"890", "V", "C", "3"},
                    {"786", "V", "C", "1"},
                    {"057", "V", "S", "3"},
                    {"945", "R", "Q", "2"},
                    {"655", "R", "M", "3"},
                    {"897", "R", "M", "0"},
                    {"801", "V", "S", "2"}
            };

            for (String[] data : datas) {
                Account a = new Account(data[0], data[1].charAt(0), data[2].charAt(0), Integer.parseInt(data[3]));
                accounts.add(a);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");

                Person p = new Person(data[1], data[2], data[3], data[4]);

                personMap.put(data[0], p);
            }

            fileIn.close();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        int choice;
        String id;
        Account account;
        Person person;
        String firstName;
        String lastName;
        int age;
        String address;
        String memberTypeString;
        String paymentTermString;
        char memberType;
        char paymentTerm;
        int numParkingSpaces;


        System.out.println("Menu:");
        System.out.println("1. Add an account");
        System.out.println("2. Delete an account");
        System.out.println("3. Display an account");
        System.out.println("4. Edit an account");
        System.out.println("5. Search an account");
        System.out.println("6. Display total by payment term");
        System.out.println("7. Display all accounts");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");

        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter ID number: ");
                id = scanner.nextLine();

                System.out.print("Enter first name: ");
                firstName = scanner.nextLine();

                System.out.print("Enter last name: ");
                lastName = scanner.nextLine();

                System.out.print("Enter age: ");
                age = scanner.nextInt();
                scanner.nextLine(); // consume the newline character

                System.out.print("Enter address: ");
                address = scanner.nextLine();

                System.out.print("Enter primary member type (R or V): ");
                memberTypeString = scanner.nextLine();
                if (!memberTypeString.equalsIgnoreCase("R") && !memberTypeString.equalsIgnoreCase("V")) {
                    System.out.println("Invalid member type. Please choose either R or V.");
                    return;
                }
                memberType = memberTypeString.toUpperCase().charAt(0);

                System.out.print("Enter payment term (C, M, Q, or S): ");
                paymentTermString = scanner.nextLine();
                if (!paymentTermString.equalsIgnoreCase("C") && !paymentTermString.equalsIgnoreCase("M")
                        && !paymentTermString.equalsIgnoreCase("Q") && !paymentTermString.equalsIgnoreCase("S")) {
                    System.out.println("Invalid payment term. Please choose either C, M, Q, or S.");
                    return;
                }
                paymentTerm = paymentTermString.toUpperCase().charAt(0);

                System.out.print("Number of Parking Spaces: ");
                numParkingSpaces = scanner.nextInt();

                Account acc = new Account(id, memberType, paymentTerm, numParkingSpaces);
                Person p = new Person(firstName, lastName, String.valueOf(age), address);
                accounts.add(acc);
                personMap.put(id, p);
                System.out.println("Account added successfully.");
                break;
            case 2:
                System.out.print("Enter ID number: ");
                id = scanner.nextLine();

                for (Account acc1 : accounts) {
                    if (acc1.id().equals(id)) {
                        accounts.remove(acc1);
                        personMap.remove(id);
                        System.out.println("Account deleted successfully.");
                        return;
                    }
                }

                System.out.println("Account not found.");

                break;
            case 3:
                System.out.print("Enter ID number: ");
                id = scanner.nextLine();

                System.out.println("Account details:");
                for (Account account1 : accounts) {
                    if (account1.id().equals(id)) {
                        System.out.println(account1);
                        System.out.println(personMap.get(id));
                        return;
                    }
                }
                break;
            case 4:
                System.out.print("Enter ID number: ");
                id = scanner.nextLine();

                account = null;
                for (Account acc1 : accounts) {
                    if (acc1.id().equals(id)) {
                        account = acc1;
                        break;
                    }
                }

                if (account == null) {
                    System.out.println("Account not found.");
                    return;
                }

                person = personMap.get(id);
                System.out.println("Select detail to edit:");
                System.out.println("1. First name");
                System.out.println("2. Last name");
                System.out.println("3. Age");
                System.out.println("4. Address");
                System.out.println("5. Member type");
                System.out.println("6. Payment term");
                System.out.println("7. Number of parking spaces");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter new first name: ");
                        firstName = scanner.nextLine();
                        person.setFname(firstName);
                        break;
                    case 2:
                        System.out.print("Enter new last name: ");
                        lastName = scanner.nextLine();
                        person.setLname(lastName);
                        break;
                    case 3:
                        System.out.print("Enter new age: ");
                        age = scanner.nextInt();
                        scanner.nextLine();
                        person.setAge(String.valueOf(age));
                        break;
                    case 4:
                        System.out.print("Enter new address: ");
                        address = scanner.nextLine();
                        person.setAddress(address);
                        break;
                    case 5:
                        System.out.print("Enter new member type (R or V): ");
                        memberTypeString = scanner.nextLine();
                        if (!memberTypeString.equalsIgnoreCase("R") && !memberTypeString.equalsIgnoreCase("V")) {
                            System.out.println("Invalid member type. Please choose either R or V.");
                            return;
                        }
                        memberType = memberTypeString.toUpperCase().charAt(0);
                        account.setMemberType(memberType);
                        break;
                    case 6:
                        System.out.print("Enter new payment term (C, M, Q, or S): ");
                        paymentTermString = scanner.nextLine();
                        if (!paymentTermString.equalsIgnoreCase("C") && !paymentTermString.equalsIgnoreCase("M")
                                && !paymentTermString.equalsIgnoreCase("Q") && !paymentTermString.equalsIgnoreCase("S")) {
                            System.out.println("Invalid payment term. Please choose either C, M, Q, or S.");
                            return;
                        }
                        paymentTerm = paymentTermString.toUpperCase().charAt(0);
                        account.setPayTerm(paymentTerm);
                        break;
                    case 7:
                        System.out.print("Enter new number of parking spaces: ");
                        numParkingSpaces = scanner.nextInt();
                        scanner.nextLine();
                        account.setSupMember(numParkingSpaces);
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("Invalid choice");
                }

                break;
            case 5:
                System.out.println("Search by:");
                System.out.println("1. ID number");
                System.out.println("2. Lastname name");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID number: ");
                        id = scanner.nextLine();

                        for (Account acc1 : accounts) {
                            if (acc1.id().equals(id)) {
                                System.out.println(acc1);
                                System.out.println(personMap.get(id));
                                return;
                            }
                        }
                        System.out.println("Account not found.");
                        break;
                    case 2:
                        System.out.println("Enter last name: ");
                        lastName = scanner.nextLine();

                        for (String key : personMap.keySet()) {
                            if (personMap.get(key).lname().equals(lastName)) {
                                for (Account acc1 : accounts) {
                                    if (acc1.id().equals(key)) {
                                        System.out.println(acc1);
                                        System.out.println(personMap.get(key));
                                    }
                                }
                            }
                        }

                }
                break;
            case 6:

                break;
            case 7:
                break;
            case 8:

                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}