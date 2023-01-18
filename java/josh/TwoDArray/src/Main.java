/**
 * @author tora
 *
 */

// 1.Write a program that will load a 2D array of size m x n with random integers (range: 1 - 25). The program
// will then sort and group the elements and store it in a jagged array. Store in first row all integers in the range
// of 1-5, store in second row all integers in the range of 6-10, store in third row all integers in the range 11-
// 15, and so on. The program will also determine and display the maximum non repeating element in each
// row of the jagged array. The maximum non repeating element should be displayed after the last element in
// each row. If there’s no maximum non repeating element or the row is empty, display an appropriate
// message. If there’s only one element in the given row, then that element is the maximum. The maximum
// value of m and n is 10 while the minimum is 3. The program should execute for as long as the user wants to
// continue. The 2D array must be displayed in tabular form.
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    /**
     * Generates 2D array of random integers
     *
     * @param m - number of rows
     * @param n - number of columns
     *
     * @return 2D array of random integers
     */
    public static int[][] generate2DArray(int m, int n) {
        int[][] array = new int[m][n];
        Random random = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = random.nextInt(25) + 1;
            }
        }
        return array;
    }

    /**
     * Gets the highest element in the array
     *
     * @param array - array to search
     */
    public static int getHighest(int[] array) {
        int highest = 0;
        /*
        for (int j : array) {
            if (j > highest) {
                highest = j;
            }
        }
        */
        for (int i = 0; i < array.length; i++) {
            if (array[i] > highest) {
                highest = array[i];
            }
        }
        return highest;
    }


    /**
     * Sorts the array in ascending order and returns it as a new array
     *
     * @param array - 2D array to be sorted
     *
     * @return - 1 dimensional array of sorted elements
     */
    public static int[] sort2DArray(int[][] array) {
        int[] sortedArray = new int[array.length * array[0].length];
        int index = 0;
        for (int[] ints : array) {
            for (int j = 0; j < array[0].length; j++) {
                sortedArray[index] = ints[j];
                index++;
            }
        }

        // sort the array
        for (int i = 0; i < sortedArray.length; i++) {
            for (int j = i + 1; j < sortedArray.length; j++) {
                if (sortedArray[i] > sortedArray[j]) {
                    int temp = sortedArray[i];
                    sortedArray[i] = sortedArray[j];
                    sortedArray[j] = temp;
                }
            }
        }
        return sortedArray;
    }

    /**
     * Creates a jagged array from a sorted array
     *
     * @param arr - the sorted array
     *
     * @return jagged array
     */
    public static int[][] createJagged(int[] arr) {
        int highest = getHighest(arr);
        int[][] jagged = new int[highest % 5 != 0 ? highest / 5 + 1: highest / 5][];

        // Store in first row all integers in the range
        // of 1-5, store in second row all integers in the range of 6-10, store in third row all integers in the range 11-
        // 15, and so on
        for (int i = 0; i < jagged.length; i++) {
            int count = 0;

            for (int j : arr) {
                if (j <= (i + 1) * 5 && j > i * 5) {
                    count++;
                }

                int[] array = new int[count];
                int index = 0;

                for (int k : arr) {
                    if (k <= (i + 1) * 5 && k > i * 5) {
                        if (index < array.length) {
                            array[index] = k;
                            index++;
                        }
                    }
                }

                jagged[i] = array;
            }
        }
        return jagged;
    }

    /**
     * Find the maximum non repeating element in a given array
     *
     * @param array - the array to search
     *
     * @return - 0 if there is no maximum non repeating element else the maximum non repeating element
     */
    public static int maxNoneRepeating(int[] array) {
        int max = 0;
        int count = 0;
        /*
        for (int k : array) {
            for (int i : array) {
                if (k == i) {
                    count++;
                }
            }
            if (count == 1) {
                if (k > max) {
                    max = k;
                }
            }
            count = 0;
        }
        */
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] == array[j]) {
                    count++;
                }
            }
            if (count == 1) {
                if (array[i] > max) {
                    max = array[i];
                }
            }
            count = 0;
        }
        return max;
    }

    public static int countRepeat(int[] arr, int num) {
        int count = 0;
        /*
        for (int i : arr) {
            if (i == num) {
                count++;
            }
        }
        */
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                count++;
            }
        }
        return count;
    }

    public static int maxRepeating(int[] arr) {
        int max = 0;
        int maxCount = 2;
        /*
        for (int i : arr) {
            count = countRepeat(arr, i);
            if (count > maxCount) {
                maxCount = count;
                max = i;
            }
        }
        */
        for (int i = 0; i < arr.length; i++) {
            int count = countRepeat(arr, arr[i]);
            if (count >= maxCount) {
                maxCount = count;
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * Displays 2D array in tabular form
     *
     * @param array - 2D array to be displayed
     */
    public static void display2DArray(int[][] array) {
        /*
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
        */
        for (int i = 0; i < array.length; i++) {
            if (array[i].length == 0) continue;
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }


    /**
     * Displays the jagged array and the max non repeating element
     *
     * @param jagged - the jagged array
     */
    public static void displayJagged(int[][] jagged) {
        /*
        for (int[] ints : jagged) {
            // skip empty rows
            if (ints.length == 0) continue;
            for (int j : ints) {
                System.out.print(j + "\t");
            }
            System.out.println(maxNoneRepeating(ints) == 0 ? "No distinct element" : "Max: " + maxNoneRepeating(ints));
        }
        */
        for (int i = 0; i < jagged.length; i++) {
            if (jagged[i].length == 0) continue;
            int maxRepeating = maxRepeating(jagged[i]);
            for (int j = 0; j < jagged[i].length; j++) {
                System.out.print(jagged[i][j] + "\t");
            }
            int count = countRepeat(jagged[i], maxRepeating);
            System.out.println(maxRepeating == 0 ? "No repeating element" : "Max: " + maxRepeating + " Count: " + count);
            // System.out.println(maxNoneRepeating(jagged[i]) == 0 ? "No distinct element" : "Max: " + maxNoneRepeating(jagged[i]));

        }
    }

    public static void main(String[] args) {
        char choice;
        do {
            Scanner scanner = new Scanner(System.in);
            int m, n;
            do {
                System.out.print("Enter m: ");
                m = scanner.nextInt();
                System.out.print("Enter n: ");
                n = scanner.nextInt();
                if (m < 3 || m > 10 || n < 3 || n > 10)
                    System.out.println("Invalid input. Please try again.");
            } while (m < 3 || m > 10 || n < 3 || n > 10);

            int[][] arr = generate2DArray(m, n);
            int[] sortedArray = sort2DArray(arr);
            System.out.println(Arrays.toString(sortedArray));
            int[][] jagged = createJagged(sortedArray);
            display2DArray(arr);
            System.out.println();
            displayJagged(jagged);
            System.out.println();
            System.out.print("Do you want to continue? (y/n): ");
            choice = scanner.next().charAt(0);
        } while (choice == 'y' || choice == 'Y');
    }


























    // j = 1    i = 0
    //    1  <= (0 + 1) * 5 && 1 > 0 * 5
    //    1  <=    5        &&    1 > 0
    // if(j <= (i + 1) * 5 && j > i * 5)
    //    count++
}