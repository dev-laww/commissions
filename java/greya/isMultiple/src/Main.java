import java.util.Scanner;

public class Main {
//    public static boolean isMultiple(int a, int b) {
//        return a % b == 0;
//    }

    public static int determineDozen(int eggs) {
        return eggs / 12;
    }

    public static int showLessDozen(int eggs) {
        return eggs % 12;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Enter first integer: ");
//        int a = scanner.nextInt();
//        System.out.print("Enter second integer: ");
//        int b = scanner.nextInt();
//
//        System.out.println();
//        System.out.println(a + " is " + (isMultiple(a, b) ? "" : "not ") + "a multiple of " + b);

        System.out.print("Enter the number of eggs: ");
        int eggs = scanner.nextInt();
        System.out.println("\nNumber of dozens: " + determineDozen(eggs));
        System.out.println("Eggs less than a dozen: " + showLessDozen(eggs));
    }
}