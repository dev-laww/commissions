import java.util.Scanner;
/*
*
* Create a Java program to check if the input(X) is a Fibonacci number.
* Create a loop that will ask the user to try again. and accept an input of Yes and No, or Y and N, or YES or NO.
*
* Constraint
* 200 <= X <= 36000
*
* */
public class Fibo {
    public static void main(String[] args) {
        String choice;

        do {
            int n, firstTerm, secondTerm;

            int[] fibonacciSeries = new int[100];
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a number: ");
            n = sc.nextInt();
            sc.nextLine();

            // check if number is a fibonacci number
            boolean isFibo = Math.sqrt(5 * n * n + 4) % 1 == 0 || Math.sqrt(5 * n * n - 4) % 1 == 0;

            firstTerm = 0;
            secondTerm = 1;
            for (int i = 0; i < n; i++) {
                if (firstTerm > n) {
                    break;
                }
                fibonacciSeries[i] = firstTerm;
                int nextTerm = firstTerm + secondTerm;
                firstTerm = secondTerm;
                secondTerm = nextTerm;
            }

            for (int i = fibonacciSeries.length - 1; i > 0; i--) {
                if (fibonacciSeries[i] != 0) System.out.print(fibonacciSeries[i] + " -> ");
            }
            System.out.println(0);

            System.out.printf(isFibo ? "%d is fibonacci\n" : "%d is not fibonacci\n", n);
            System.out.println("Try again? (y/n) ");
            choice = sc.nextLine();
        } while (choice.equals("y") || choice.equals("yes"));

        System.out.println("Thank you for using the program!");
    }
}
