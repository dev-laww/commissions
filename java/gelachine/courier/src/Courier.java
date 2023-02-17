import java.util.Scanner;

public class Courier {
    public static void main(String[] args) {
        anotherMethod();
    }

    public static void basicDetails() {
        String[] array = {
                "1) Name",
                "2) Address",
                "3) Parcel weight",
                "4) Parcel height",
                "5) Parcel length",
                "6) Items",
                "7) Branch Location",
        };

        for (String s : array) {
            System.out.println(s);
        }

        Scanner scanner = new Scanner(System.in);

        String[] details = new String[7];

        for (int i = 0; i < details.length; i++) {
            System.out.print("Enter " + array[i] + ": ");
            details[i] = scanner.nextLine();
        }

        double weight = Double.parseDouble(details[2]) * 150;
        double height = Double.parseDouble(details[3]) * 100;
        double length = Double.parseDouble(details[4]) * 100;
        double total = weight + height + length;
        double totalPayment = total * Double.parseDouble(details[5]);

        System.out.println("Name: " + details[0]);
        System.out.println("Address: " + details[1]);
        System.out.println("Branch Location: " + details[6]);
        System.out.println("Height: " + height);
        System.out.println("Length: " + length);
        System.out.println("Weight: " + weight);
        System.out.println("Total:" + total);
        System.out.println("Total payment: " + totalPayment);
    }

    public static void courierDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's the courier name?");
        String courierName = scanner.nextLine();
        System.out.println("What branch location?");
        String branchLocation = scanner.nextLine();

        System.out.println("Courier name: " + courierName);
        System.out.println("Branch location: " + branchLocation);
    }

    public static void anotherMethod() {
        basicDetails();
        courierDetails();
    }
}