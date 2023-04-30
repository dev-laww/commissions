import mpfileiocollectionhealthclub.Account;
import mpfileiocollectionhealthclub.Person;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
//        Uncomment this to generate a new acct.txt file with accounts array if you want to start fresh
//        List<Account> accounts = new ArrayList<>();
//        Map<String, Person> personMap = new HashMap<>();
//        try {
//            FileInputStream fileIn = new FileInputStream("src/acct.txt");
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//
//            Account obj = (Account) in.readObject();
//            accounts.add(obj);
////            uncomment this to generate a new acct.txt file with accounts array
//            accounts.add(obj);
//
//            String[][] datas = {
//                    {"567", "R", "Q", "1"},
//                    {"456", "V", "C", "3"},
//                    {"123", "V", "S", "2"},
//                    {"890", "V", "C", "3"},
//                    {"786", "V", "C", "1"},
//                    {"057", "V", "S", "3"},
//                    {"945", "R", "Q", "2"},
//                    {"655", "R", "M", "3"},
//                    {"897", "R", "M", "0"},
//                    {"801", "V", "S", "2"}
//            };
//
//            for (String[] data : datas) {
//                Account a = new Account(data[0], data[1].charAt(0), data[2].charAt(0), Integer.parseInt(data[3]));
//                accounts.add(a);
//            }
//
//            fileIn.close();
//            in.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        List<Account> accounts = readAccounts();
        Map<String, Person> personMap = readPersonMap();

        Scanner scanner = new Scanner(System.in);

        int choice;

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
                addAccount(accounts, personMap);
                break;
            case 2:
                deleteAccount(accounts, personMap);
                break;
            case 3:
                displayAccount(accounts, personMap);
                break;
            case 4:
                editAccount(accounts, personMap);
                break;
            case 5:
                searchAccount(accounts, personMap);
                break;
            case 6:
                displayTotal(accounts);
                break;
            case 7:
                displayAll(accounts);
                break;
            case 8:
                System.out.println("Exiting...");

                try {
                    FileOutputStream fos = new FileOutputStream("src/acct.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);

                    oos.writeObject(accounts);

                    fos.close();
                    oos.close();

                    FileWriter fw = new FileWriter("src/mapMP3.txt");
                    BufferedWriter bw = new BufferedWriter(fw);

                    for (String key : personMap.keySet()) {
                        bw.write(key + " " + personMap.get(key).toString());
                        bw.newLine();
                    }

                    bw.close();
                    fw.close();
                } catch (Exception e) {
                    System.out.println("Error writing to file.");
                }
                return;
            default:
                System.out.println("Invalid choice");
        }

        main(args);
    }

    private static List<Account> readAccounts() {
        List<Account> accounts = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("src/acct.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            accounts = (List<Account>) ois.readObject();

            fis.close();
            ois.close();
        } catch (Exception e) {
            System.out.println("Error reading from file.");
        }

        return accounts;
    }

    private static Map<String, Person> readPersonMap() {
        Map<String, Person> personMap = new HashMap<>();

        try {
            FileReader fr = new FileReader("src/mapMP3.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                String id = tokens[0];
                String fname = tokens[1];
                String lname = tokens[2];
                String age = tokens[3];
                String address = tokens[4];

                Person person = new Person(fname, lname, age, address);
                personMap.put(id, person);
            }

            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println("Error reading from file.");
        }

        return personMap;
    }

    private static void addAccount(List<Account> accounts, Map<String, Person> personMap) {
        Scanner scanner = new Scanner(System.in);

        String id, firstName, lastName, address, memberTypeString, paymentTermString;
        char memberType, paymentTerm;
        int age, supplementaryMembers;

        System.out.print("Enter ID number: ");
        id = scanner.nextLine();

        if (personMap.containsKey(id)) {
            System.out.println("ID already exists.");
            addAccount(accounts, personMap);
            return;
        }

        if (id.length() != 3) {
            System.out.println("ID must be 3 digits.");
            addAccount(accounts, personMap);
            return;
        }

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

        System.out.print("Number of Supplementary Members: ");
        supplementaryMembers = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (supplementaryMembers < 0) {
            System.out.println("Invalid number of supplementary members.");
            return;
        }

        if (supplementaryMembers > 3 && memberType == 'R' || supplementaryMembers > 5 && memberType == 'V') {
            System.out.println("Invalid number of supplementary members.");
            return;
        }

        Account acc = new Account(id, memberType, paymentTerm, supplementaryMembers);
        Person p = new Person(firstName, lastName, String.valueOf(age), address);
        accounts.add(acc);
        personMap.put(id, p);
        System.out.println("Account added successfully.");
    }

    private static void deleteAccount(List<Account> accounts, Map<String, Person> personMap) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID number: ");
        String id = scanner.nextLine();

        boolean found = false;
        for (Account acc : accounts) {
            if (acc.id().equals(id)) {
                accounts.remove(acc);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Account not found.");
            return;
        }

        personMap.remove(id);
        System.out.println("Account deleted successfully.");
    }

    private static void displayAccount(List<Account> accounts, Map<String, Person> personMap) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID number: ");
        String id = scanner.nextLine();

        System.out.println("Account details:");
        for (Account account : accounts) {
            if (account.id().equals(id)) {
                System.out.printf("%s %.2f%n", account, account.calculateMembershipFee());
                System.out.println(personMap.get(id));
                return;
            }
        }

        System.out.println("Account not found.");
    }

    private static void editAccount(List<Account> accounts, Map<String, Person> personMap) {
        Scanner scanner = new Scanner(System.in);

        String id, firstName, lastName, address, memberTypeString, paymentTermString;
        char memberType, paymentTerm;
        int age, supplementaryMembers;

        System.out.print("Enter ID number: ");
        id = scanner.nextLine();

        Account account = null;
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

        Person person = personMap.get(id);
        System.out.println("Select detail to edit:");
        System.out.println("1. First name");
        System.out.println("2. Last name");
        System.out.println("3. Age");
        System.out.println("4. Address");
        System.out.println("5. Member type");
        System.out.println("6. Payment term");
        System.out.println("7. Number of supplementary members");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine(); // consume the newline character

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
                System.out.print("Enter new number of supplementary members: ");
                supplementaryMembers = scanner.nextInt();
                scanner.nextLine();
                if (supplementaryMembers < 0) {
                    System.out.println("Invalid number of supplementary members.");
                    return;
                }

                if (supplementaryMembers > 3 && account.memberType() == 'R' || supplementaryMembers > 5 && account.memberType() == 'V') {
                    System.out.println("Invalid number of supplementary members.");
                    return;
                }

                account.setSupMember(supplementaryMembers);
                break;
            case 8:
                return;
            default:
                System.out.println("Invalid choice.");
        }

        System.out.println("Account edited successfully.");
        editAccount(accounts, personMap);
    }

    private static void searchAccount(List<Account> accounts, Map<String, Person> personMap) {
        Scanner scanner = new Scanner(System.in);

        String id, lastName;
        int choice;

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

                for (Account acc : accounts) {
                    if (acc.id().equals(id)) {
                        System.out.println(acc);
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
                        for (Account acc : accounts) {
                            if (key.equals(acc.id())) {
                                System.out.println(acc);
                                System.out.println(personMap.get(key));
                            }
                        }
                    }
                }
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice.");
        }

        searchAccount(accounts, personMap);
    }

    private static void displayTotal(List<Account> accounts) {
        double totalCash = 0.0;
        double totalMonthly = 0.0;
        double totalQuarterly = 0.0;
        double totalSemiAnnually = 0.0;

        for (Account acc : accounts) {
            double membershipFee = acc.calculateMembershipFee();

            switch (acc.payTerm()) {
                case 'C':
                    totalCash += membershipFee;
                    break;
                case 'M':
                    totalMonthly += membershipFee;
                    break;
                case 'Q':
                    totalQuarterly += membershipFee;
                    break;
                case 'S':
                    totalSemiAnnually += membershipFee;
                    break;
                default:
                    System.out.println("Invalid payment term.");
                    return;
            }
        }

        System.out.println("Total cash payments: " + totalCash);
        System.out.println("Total monthly payments: " + totalMonthly);
        System.out.println("Total quarterly payments: " + totalQuarterly);
        System.out.println("Total semi-annually payments: " + totalSemiAnnually);
    }

    private static void displayAll(List<Account> accounts) {
        accounts.sort(new Comparator<Account>() {
            @Override
            public int compare(Account a1, Account a2) {
                return a1.id().compareTo(a2.id());
            }
        });

        System.out.println("ID\tType\tTerm\tSup Members\tMF");
        for (Account a : accounts) {
            System.out.printf("%s\t%c\t%c\t%d\t%.2f\n", a.id(), a.memberType(), a.payTerm(), a.supMember(), a.calculateMembershipFee());
        }
    }
}