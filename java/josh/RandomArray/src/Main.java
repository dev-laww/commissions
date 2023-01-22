/**
 * @author:  tora
 */

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int MAX_SIZE = 50;
    private static final int MIN_RANDOM = 10;
    private static final int MAX_RANDOM = 30;
    private static final int MIN_X = 3;
    private static final int MAX_X = 15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean run = true;
        while (run) {
            System.out.print("Enter array size: ");
            int n = scanner.nextInt();
            if (n > MAX_SIZE) {
                System.out.println("Array size too large, maximum is " + MAX_SIZE);
                continue;
            }
            int[] A = new int[n];
            loadRandom(A, random);
            System.out.print("Enter x between " + MIN_X + " and " + MAX_X + ": ");
            int x = scanner.nextInt();

            if (x < MIN_X || x > MAX_X) {
                System.out.println("Invalid input");
                continue;
            }

            System.out.println();

            int[] B = new int[n];
            int[] C = new int[n];

            processArrays(A, B, C, x);
            printArray("A", A);
            printArray("B", B);
            printArray("C", C);
            System.out.println();
            printStatistics(A, B, C);
            System.out.println();
            System.out.print("Do you want to continue? (y/n) ");
            run = scanner.next().equalsIgnoreCase("y");
        }
        scanner.close();
    }

    /**
     * Loads the array with random numbers between MIN_RANDOM and MAX_RANDOM
     *
     * @param A the array to load
     * @param random the random number generator
     */
    private static void loadRandom(int[] A, Random random) {
        for (int i = 0; i < A.length; i++) {
            A[i] = random.nextInt(MAX_RANDOM - MIN_RANDOM + 1) + MIN_RANDOM;
        }
    }

    /**
     * Processes the arrays A, B and C according to the directions
     *
     * @param A the array A
     * @param B the array
     * @param C the array C
     * @param x the value of x
     */
    private static void processArrays(int[] A, int[] B, int[] C, int x) {
        int cIndex = C.length - 1;
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i] - x;
            if (B[i] >= x) {
                C[cIndex--] = B[i];
            }
        }
    }

    /**
     * Prints the statistics of the arrays.
     *
     * @param A the array A
     * @param B the array B
     * @param C the array C
     */
    private static void printStatistics(int[] A, int[] B, int[] C) {
        System.out.println("Sum of array A: " + sumArray(A));
        System.out.printf("Average of array A: %.2f%n", averageArray(A));
        System.out.println("Sum of array B: " + sumArray(B));
        System.out.printf("Average of array B: %.2f%n", averageArray(B));
        System.out.println("Sum of array C: " + sumArray(C));
        System.out.printf("Average of array C: %.2f%n", averageArray(C));
    }

    /**
     * Returns the sum of all elements in the array
     *
     * @param arr the array
     * @return the sum of all elements in the array
     */
    private static int sumArray(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

    /**
     * Returns the average of the array
     *
     * @param arr the array
     * @return the average of the array
     */
    private static double averageArray(int[] arr) {
        return (double) sumArray(arr) / arr.length;
    }


    /**
     * Checks if the array is empty
     *
     * @param arr the array to check
     * @return true if the array is empty, false otherwise
     */
    private static boolean allZeroes(int[] arr) {
        for (int i : arr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Prints the array with the given name in the following format:
     * <p>
     * Array name: 1 2 3 4 5
     * <p>
     *
     * @param name the name of the array
     * @param arr  the array to print
     */
    private static void printArray(String name, int[] arr) {
        System.out.print(name + ": ");
        // If all elements are 0, print array is empty
        if (allZeroes(arr)) {
            System.out.println("Array is empty");
            return;
        }
        for (int i : arr) {
            // check if the array name is C, if so, only print non-zero elements
            if (name.equals("C")) {
                System.out.print(i == 0 ? "" : i + " ");
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
    }
}