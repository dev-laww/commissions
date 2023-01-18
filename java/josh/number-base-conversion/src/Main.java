import java.util.Scanner;

public class Main {
    // Converts a number in base r to base 10
    public static double baseRToBase10(String number, int r) {
        // Parse the fractional part of the number
        String[] parts = number.split("\\.");
        String wholePart = parts[0];
        String fractionPart = parts.length > 1 ? parts[1] : "";

        // Convert the whole part of the number to base 10
        double whole = 0;
        for (int i = 0; i < wholePart.length(); i++) {
            int digit = Character.digit(wholePart.charAt(i), r);
            whole = whole * r + digit;
        }

        // Convert the fractional part of the number to base 10
        double fraction = 0;
        for (int i = 0; i < fractionPart.length(); i++) {
            int digit = Character.digit(fractionPart.charAt(i), r);
            fraction += digit * Math.pow(r, -(i + 1));
        }

        // Return the sum of the whole and fractional parts
        return whole + fraction;
    }

    // Converts a number in base 10 to base r
    public static String base10ToBaseR(double number, int r) {
        // Convert the whole part of the number to base r
        StringBuilder whole = new StringBuilder();
        int wholePart = (int) number;
        while (wholePart > 0) {
            int digit = wholePart % r;
            whole.insert(0, digitToChar(digit));
            wholePart /= r;
        }

        // Convert the fractional part of the number to base r
        StringBuilder fraction = new StringBuilder();
        double fractionPart = number - (int) number;
        for (int i = 0; i < 4; i++) {
            fractionPart *= r;
            int digit = (int) fractionPart;
            fraction.append(digitToChar(digit));
            fractionPart -= digit;
        }

        // Return the whole and fractional parts joined together
        return whole + "." + fraction;
    }

    // Converts a digit to the corresponding character (0-9, A-F)
    public static char digitToChar(int digit) {
        if (digit >= 0 && digit <= 9) {
            return (char) (digit + '0');
        } else {
            return (char) (digit - 10 + 'A');
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char choice;
        do {
            System.out.println("Number Base Conversion Program");
            System.out.println("\tA. Base r to Base 10");
            System.out.println("\tB. Base 10 to Base r");
            System.out.println("\tC. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.next().toLowerCase().charAt(0);

            switch (choice) {
                case 'a':
                    System.out.println("Enter base r (2-16): ");
                    // validate input
                    int r = scanner.nextInt();
                    while (r < 2 || r > 16 || r == 10) {
                        System.out.println("Invalid base. Please enter a value from 2 to 16 (except 10): ");
                        r = scanner.nextInt();
                    }
                    System.out.println("Enter the number: ");
                    String numberStr = scanner.next();
                    double decimalValue = baseRToBase10(numberStr, r);
                    // round off to 4 decimal places
                    decimalValue = Math.round(decimalValue * 10000.0) / 10000.0;
                    // display result
                    System.out.println("The number in base 10 is: " + decimalValue);
                    break;
                case 'b':
                    System.out.println("Enter base r (2-16): ");
                    // validate input
                    int base = scanner.nextInt();
                    while (base < 2 || base > 16 || base == 10) {
                        System.out.println("Invalid base. Please enter a value from 2 to 16 (except 10): ");
                        base = scanner.nextInt();
                    }
                    System.out.println("Enter the number: ");
                    double number = scanner.nextDouble();
                    String baseRValue = base10ToBaseR(number, base);
                    // display result
                    System.out.println("The number in base " + base + " is: " + baseRValue);
                    break;
                case 'c':
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");

            }

        } while (choice != 'c');
    }
}
