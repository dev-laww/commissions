import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int r, base;
        double number, decimalValue;
        String numberStr;
        char choice;

        while (true) {
            System.out.println("Number Base Conversion Program");
            System.out.println("\tA. Base r to Base 10");
            System.out.println("\tB. Base 10 to Base r");
            System.out.println("\tC. Quit");
            System.out.print("Enter your choice: ");
            choice = input.next().toLowerCase().charAt(0);

            if (choice == 'a' || choice == 'b') {
                System.out.print("Enter the number: ");
                numberStr = input.next();

                System.out.print("Enter the base (2-16): ");
                if (choice == 'a') {
                    r = input.nextInt();

                    // validate input
                    while (r < 2 || r > 16 || r == 10) {
                        System.out.println("Invalid base. Please enter a value from 2 to 16 (except 10): ");
                        r = input.nextInt();
                    }

                    // convert number from base r to base 10
                    decimalValue = 0;
                    for (int i = 0; i < numberStr.length(); i++) {
                        char ch = numberStr.charAt(i);
                        if (ch == '.') {
                            continue;
                        }
                        int value;
                        if (Character.isDigit(ch)) {
                            value = ch - '0';
                        } else {
                            value = ch - 'A' + 10;
                        }
                        decimalValue = decimalValue * r + value;
                    }

                    // round off to 4 decimal places
                    decimalValue = Math.round(decimalValue * 10000.0) / 10000.0;

                    // display result
                    System.out.println("The number in base 10 is: " + decimalValue);
                } else {
                    base = input.nextInt();

                    // validate input
                    while (base < 2 || base > 16 || base == 10) {
                        System.out.println("Invalid base. Please enter a value from 2 to 16 (except 10): ");
                        base = input.nextInt();
                    }

                    // convert number from base 10 to base r
                    number = Double.parseDouble(numberStr);
                    StringBuilder result = new StringBuilder();
                    while (number > 0) {
                        int remainder = (int) (number % base);
                        if (remainder < 10) {
                            result.insert(0, remainder);
                        } else {
                            result.insert(0, (char) (remainder - 10 + 'A'));
                        }
                        number = Math.floor(number / base);
                    }

                    // display result
                    System.out.println("The number in base " + base + " is: " + result);
                }
            } else if (choice == 'c') {
                System.out.println("Program terminated.");
                break;
            }
        }
    }
}